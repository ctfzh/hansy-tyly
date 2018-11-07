package com.hansy.tyly.admin.settlement.service.impl;

import com.hansy.tyly.admin.dao.mapper.SysUserMapper;
import com.hansy.tyly.admin.dao.mapper.TBusOrderMapper;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.dealers.dao.mapper.DistributorInfoMapper;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.settlement.dao.mapper.TFinaDistributorOrderMapper;
import com.hansy.tyly.admin.settlement.dao.mapper.TFinaDistributorSettMapper;
import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorOrder;
import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorSett;
import com.hansy.tyly.admin.settlement.service.DistributorOrderService;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DistributorOrderServiceImpl implements DistributorOrderService {

    @Autowired
    private TBusOrderMapper orderMapper;

    @Autowired
    private TFinaDistributorSettMapper distributorSettMapper;

    @Autowired
    private TFinaDistributorOrderMapper distributorOrderMapper;

    @Autowired
    private DistributorInfoMapper distributorInfoMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<TBusOrder> listOrder(Map<String, Object> params) {
        params.put("orderType", SysCodeConstant.ORDER_TYPE_PLAT);
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SUCCESS);
        NPageHelper.startPage(params);
        return orderMapper.listByOrderType(params);
    }

    @Override
    public List<TFinaDistributorSett> listSettlements(Map<String, Object> map) {
        NPageHelper.startPage(map);
        return distributorSettMapper.listSettlements(map);
    }

    @Override
    public List<TBusOrder> listSettlementOrders(Map<String, Object> map) {
        if (map.get("settNo") == null){
            throw new ParameterException("结算编号为空");
        }
        NPageHelper.startPage(map);
        return distributorOrderMapper.listSettlementOrder(map);
    }

    @Transactional
    @Override
    public boolean settle(List<String> orderNos) throws ParameterException {
        if (!ValidUtil.valid(orderNos)) {
            throw new ParameterException("orderNos为空");
        }
        // 根据订单编号集合获取订单集合
        List<TBusOrder> orders = orderMapper.listByOrderNos(orderNos);
        if (!ValidUtil.valid(orders)) {
            throw new ParameterException("订单不存在");
        }

//         获取结算人信息
        String loginId = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser user = userMapper.selectByLoginId(loginId);
        if (user == null) {
            throw new ParameterException("当前用户未登录或登录信息已失效");
        }

        // 结算订单集合和结算信息插入
        TFinaDistributorSett distributorSett = new TFinaDistributorSett();
        distributorSett.setSettNo(RandomUtil.numberKey(14));
        List<TFinaDistributorOrder> distributorOrders = new ArrayList<>();
        BigDecimal amountCount = BigDecimal.ZERO;
        String distributorNo = null;
        for (TBusOrder order : orders) {
            // 判断是否是同一个经销商的订单
            if (distributorNo == null){
                distributorNo = order.getBuyPerson();
            } else if (!distributorNo.equals(order.getBuyPerson())){
                throw new ParameterException("请选择同一个经销商的订单");
            }
            TFinaDistributorOrder distributorOrder = new TFinaDistributorOrder();
            distributorOrder.setSettNo(distributorSett.getSettNo());
            distributorOrder.setOrderNo(order.getOrderNo());

            distributorOrders.add(distributorOrder);
            amountCount = amountCount.add(order.getTotalAmt());
        }
        // 获取结算时间
        Date settDate = new Date();
        // 获取经销商信息
        DistributorInfo distributorInfo = distributorInfoMapper.selectByPrimaryKey(distributorNo);
        if (distributorInfo == null) {
            throw new ParameterException("订单中的经销商编号有误");
        }
        distributorSett.setDistributorNo(distributorInfo.getDistributorNo());
        distributorSett.setDistributorName(distributorInfo.getDistributorName());
        distributorSett.setDistributorPerson(distributorInfo.getDistributorContact());
        distributorSett.setPersonPhone(distributorInfo.getDistributorContactPhone());
        distributorSett.setSettTotalAmt(amountCount);
        distributorSett.setSettDate(settDate);
        distributorSett.setStaffNo(user.getUserId());
        // 修改已成功的订单状态为已结算
        Map<String, Object> map = new HashMap<>();
        map.put("orderNos",orderNos);
        map.put("oldTransStatus",SysCodeConstant.TRANS_STATUS_SUCCESS);
        map.put("newTransStatus",SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        map.put("updateDate",settDate);
        if (orderMapper.updateOrdersTransStatus(map) <= 0){
            throw new ParameterException("所有订单的状态都不是\"已成功\"");
        }

//         添加结算记录
        boolean flag = distributorSettMapper.insertSelective(distributorSett) > 0;

        // 批量添加结算和订单的关联信息
        flag = flag && distributorOrderMapper.insertBatchs(distributorOrders) > 0;
        if (!flag) {
            throw new ParameterException("更新失败");
        }
        return flag;
    }


}
