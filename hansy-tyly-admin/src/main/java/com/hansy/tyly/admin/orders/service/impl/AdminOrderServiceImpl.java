package com.hansy.tyly.admin.orders.service.impl;

import com.hansy.tyly.admin.dao.mapper.*;
import com.hansy.tyly.admin.dao.model.*;
import com.hansy.tyly.admin.orders.service.IAdminOrderService;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.*;
import com.hansy.tyly.admin.utils.enumClass.CodeEnum;
import com.hansy.tyly.admin.utils.enumClass.CodeNameEnum;
import com.hansy.tyly.core.helper.NPageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

@Service
@Transactional
public class AdminOrderServiceImpl implements IAdminOrderService {
    @Autowired
    private OrderInfosMapper orderInfosMapper;
    @Autowired
    private TBusOrderMapper busOrderMapper;
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;
    @Autowired
    private TBusOrderChangeMapper busOrderChangeMapper;
    @Autowired
    private TBusRefundMapper refundMapper;
    @Autowired
    private TUserNewsMapper userNewsMapper;
    @Autowired
    private TUserBaseInfoMapper userBaseInfoMapper;
    @Autowired
    private TBusOrderDetailMapper tBusOrderDetailMapper;
    @Autowired
    private TPubDistributorInfoMapper distributorInfoMapper;
    @Autowired
    private TPubMerInfoMapper merInfoMapper;
    @Autowired
    private TBusOrderChangeMapper tbusOrderChangeMapper;




    @Override
    public List<OrderInfos> getOrdersList(Map<String, Object> params) throws Exception {
        // 判断是否是商户订单
        NPageHelper.startPage(params);
        List<OrderInfos> list=orderInfosMapper.getOrdersListByParams(params);
        return list;
    }
    @Override
    public List<Map<String, Object>> doOrderList(List<OrderInfos> list) throws Exception {
        List<Map<String,Object>> mapList=new ArrayList<>();
        list.forEach(node->{
            Map<String,Object> map=new HashMap<>();
            map.put("orderNo",node.getOrder().getOrderNo());
            List<Map<String,Object>> templist=orderInfosMapper.getOrdersDetailListByParams(map);
            List<Map<String,Object>> reflist= orderInfosMapper.getRefundGoodsByParams(map);
            List<Map<String,Object>> nureflist= orderInfosMapper.getUnRefundGoodsByParams(map);
            Map<String,Object> changeDate=orderInfosMapper.getChangeDate(map);
            Map<String,Object> tmp=new HashMap<>();
            tmp.put("order",node.getOrder());
            String platform=sysUserRoleMenuService.getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode());
            if(platform.equals(node.getOrder().getOrderType())){//经销商下的单
                tmp.put("disName",distributorInfoMapper.selectByPrimaryKey(node.getOrder().getBuyPerson()).getDistributorName());
                tmp.put("disNo",node.getOrder().getBuyPerson());
            }else{
                TPubDistributorInfo distributorInfo= distributorInfoMapper.selectByPrimaryKey(node.getOrder().getSellPerson());
                String disName="";
                if(null!=distributorInfo){
                    disName=distributorInfo.getDistributorName();
                }
                tmp.put("disName",disName);

                TPubMerInfo merInfo=merInfoMapper.selectByPrimaryKey(node.getOrder().getBuyPerson());
                String merName="";
                if(null!=merInfo){
                    merName=merInfo.getMerName();
                }
                tmp.put("merName",merName);
                tmp.put("merNo",node.getOrder().getBuyPerson());

                tmp.put("disNo",node.getOrder().getSellPerson());
            }
            tmp.put("orderDetail",templist);
            tmp.put("refund",reflist);
            tmp.put("nureflist",nureflist);
            tmp.put("changeDate",changeDate);
            mapList.add(tmp);
        });
        return mapList;
    }




    @Override
    public Map<String, Object> getOrderDetailInfo(String orderNo) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("orderNo",orderNo);
        return doOrderList(getOrdersList(map)).get(0);
    }

    @Override
    public void editOrder(TBusOrder orderInfo) throws Exception {
        orderInfo.setUpdateDate(new Date());
        busOrderMapper.updateByPrimaryKeySelective(orderInfo);
    }

    public void status(String oldStatus,String changeStatus)throws Exception{

        //原来为待发货  现在却要取消
        if(oldStatus.equals(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.unSend.getName(),CodeEnum.editOrderStatus.getCode())) &&
                changeStatus.equals(sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.orderFailed.getName(),CodeEnum.editOrderStatus.getCode()))){
            throw new RuntimeException("待收货的订单不能取消");

            //原来为待退款 现在却要进行发货操作
        }else if(oldStatus.equals(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.unFund.getName(),CodeEnum.editOrderStatus.getCode())) &&
                changeStatus.equals(sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.unGet.getName(),CodeEnum.editOrderStatus.getCode()))
                ) {
            throw new RuntimeException("待退款的订单不能进行发货操作");
        }
    }



    //修改订单状态的方法
    public void editOrderStatus(String orderNo, String changeStatus) throws Exception{
        Map<String,Object> pmap=new HashMap<>();
        pmap.put("orderNo",orderNo);
        pmap.put("changeStatus",changeStatus);
        int count =busOrderMapper.updateStatusByPrimaryKey(pmap);
        if(count!=1){
            throw new RuntimeException("操作失败");
        }

    }



    //处理订单（发货和退款）
    @Override
    public void processOrders(String orderNo, String changeStatus) throws Exception {
        TBusOrder order=busOrderMapper.selectByPrimaryKey(orderNo);
        String oldStatus=order.getTransStatus();
        status(oldStatus,changeStatus);
        //先判断 改订单是否存在 以及 是否属于平台
        if(null!=order && order.getOrderType().equals(
                sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.platform.getName(), CodeEnum.userType.getCode()))
                ){
            //存到变更表
            TBusOrderChange change=new TBusOrderChange();
            change.setOrderNo(orderNo);
            change.setChangeStatus(changeStatus);
            change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(changeStatus));
            change.setChangeDate(new Date());
            busOrderChangeMapper.insertSelective(change);
            //修改订单状态
           /* order.setTransStatus(changeStatus);
            busOrderMapper.updateByPrimaryKeySelective(order);*/
            editOrderStatus(orderNo,changeStatus);



            //增加一条信息 到  用户信息 表里面  推送到微信
            TUserNews userNews=new TUserNews();
            userNews.setUserNo(order.getBuyPerson());
            userNews.setUserType(sysUserRoleMenuService.getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));
            userNews.setNewsDate(new Date());
            userNews.setIsRead(Context.READ_FALSE);
            String name=userBaseInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getUserName();
            if(StringUtils.isEmpty(name)){
                name="";
            }
            String content= MessageFormat.format(PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
                    name,
                    order.getOrderNo(),
                    sysUserRoleMenuService.getCodeNameByValue(changeStatus)
            );
            userNews.setNewsContent(content);
            String title= MessageFormat.format(PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.ORDER_TITL),
                    name,
                    sysUserRoleMenuService.getCodeNameByValue(changeStatus),
                    order.getOrderNo()
            );
            userNews.setNewsTitle(title);
            userNews.setTableKey(UUIDUtils.getUuid());
            userNewsMapper.insertSelective(userNews);

                //如果状态为退款成功并且之前的状态为交易成功
            if(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.orderSeccess.getName(),CodeEnum.editOrderStatus.getCode()).equals(
                    order.getTransStatus()
            ) &&oldStatus.equals(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.orderSeccess.getName(),CodeEnum.editOrderStatus.getCode()))){

                TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
                TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
                detailCriteria.andOrderNoEqualTo(orderNo);
                List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);

                detailList.forEach(node->{
                // 还要根据 订单详情减少商品销量
                /*TGoodsBaseInfo goodsBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(node.getGoodsNo());
                goodsBaseInfo.setGoodsSaleNum(node.getGoodsNum()-goodsBaseInfo.getGoodsSaleNum());
                goodsBaseInfoMapper.updateByPrimaryKeySelective(goodsBaseInfo);*/
                });
            }

        }else{
            throw new RuntimeException("该订单不存在或不属于该平台");
        }

    }

    @Override
    public List<OrderInfos> getRefundOrders(Map<String, Object> params) throws Exception {
        params.put("orderType",sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()));
        NPageHelper.startPage(params);

        List<OrderInfos> list=orderInfosMapper.getRefundListByParams(params);
        if(null!= list && list.size()>0){
            for (OrderInfos orderInfos : list) {
                TBusOrder order=orderInfos.getOrder();
                TPubDistributorInfo distributorInfo= distributorInfoMapper.selectByPrimaryKey(order.getBuyPerson());
                order.setBuyPerson(distributorInfo.getDistributorName());
            }
        }


        return list;
    }

    @Override
    public List<OrderInfos> getRefundOrderInfo(Integer tableKey) throws Exception {
        List<OrderInfos> list=orderInfosMapper.getRefundInfoByKey(tableKey);
        list.forEach(node->{
            node.getOrder().setBuyPerson(
                    distributorInfoMapper.selectByPrimaryKey(
                            node.getOrder().getBuyPerson()).getDistributorName()
            );
        });


        return list;
    }

    @Override
    public void insertOrderNews(String orderNo,String userNo,String userType,String content,String title) throws Exception {
        //增加一条信息 到  用户信息 表里面  推送到微信
        TUserNews userNews = new TUserNews();
        userNews.setUserNo(userNo);
        userNews.setUserType(userType);
        userNews.setNewsType(sysUserRoleMenuService.getCodeValueByName(CodeNameEnum.xitongNews.getName(),CodeEnum.newsType.getCode()));
        userNews.setNewsDate(new Date());
        userNews.setIsRead(Context.READ_FALSE);
        userNews.setNewsContent(content);
        userNews.setNewsTitle(title);
        userNews.setOrderNo(orderNo);
        userNews.setTableKey(UUIDUtils.getUuid());
        userNewsMapper.insertSelective(userNews);
    }

    @Override
    public void approveRefund(Integer tableKey, String apprStatus) throws Exception {

        TBusRefund refund=refundMapper.selectByPrimaryKey(tableKey);

        TBusOrder order= busOrderMapper.selectByPrimaryKey(refund.getOrderNo());
        if(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(order.getOrderType())){
            throw new RuntimeException("该订单属于经销商");
        }
        refund.setApprStatus(apprStatus);
        refund.setApprDate(new Date());
        refundMapper.updateByPrimaryKeySelective(refund);
        //
        String name= distributorInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getDistributorName();
        String sellname= "平台";
        if(StringUtils.isEmpty(name)){
            name="";
        }
        //插入信息记录
        try {
            insertOrderNews(order.getOrderNo(),order.getBuyPerson(),
                    sysUserRoleMenuService.getCodeValueByName(CodeNameEnum.Merchant.getName(),CodeEnum.userType.getCode()),
                    MessageFormat.format(
                            PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
                            name,
                            sellname,
                            sysUserRoleMenuService.getCodeNameByValue(apprStatus),
                            order.getOrderNo()),

                    MessageFormat.format(
                            PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.ORDER_TITL),
                            sysUserRoleMenuService.getCodeNameByValue(apprStatus))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

       String orderRefund= sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.orderRefund.getName(),CodeEnum.editOrderStatus.getCode());

        //如果审批状态为通过那么修改对应的订单状态为待退款
        if(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.apprSuccess.getName(),CodeEnum.apprStatus.getCode()).equals(
                        apprStatus)
                ){
            /*order.setOrderNo(refund.getOrderNo());
            order.setUpdateDate(new Date());
            order.setTransStatus(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.orderRefund.getName(),CodeEnum.editOrderStatus.getCode()));
            busOrderMapper.updateByPrimaryKeySelective(order);*/
            int goodsNum=0;

            List<TBusRefund> refundsNum=refundMapper.getGoodsNumByOrderNo(refund.getOrderNo());
            TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
            TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
            detailCriteria.andOrderNoEqualTo(refund.getOrderNo());
            List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);

            for (TBusOrderDetail detail:detailList) {
                goodsNum+=detail.getGoodsNum();
            }

            for (TBusRefund ref:refundsNum) {
                goodsNum-=ref.getGoodsNum();
            }



            //待退款时 以前的状态  审批通过的 就 变成 已退款
            if(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.unFund.getName(),CodeEnum.editOrderStatus.getCode()).equals(order.getTransStatus())
                    || goodsNum ==0
                    ){
                order.setTransStatus(orderRefund);
                //修改订单状态
                editOrderStatus(refund.getOrderNo(),orderRefund);

                //增加一条变动记录
                TBusOrderChange change=new TBusOrderChange();
                change.setOrderNo(refund.getOrderNo());
                change.setChangeStatus(order.getTransStatus());
                change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(order.getTransStatus()));
                change.setChangeDate(new Date());
                busOrderChangeMapper.insertSelective(change);
            }
        }
        if(!orderRefund.equals(order.getTransStatus())){
            //查询待退款的时间
            TBusOrderChangeExample exampleChange=new TBusOrderChangeExample();
            TBusOrderChangeExample.Criteria criteriaChange=exampleChange.createCriteria();
            /*criteriaChange.andChangeStatusEqualTo(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.unFund.getName(),CodeEnum.editOrderStatus.getCode()));*/
            criteriaChange.andOrderNoEqualTo(refund.getOrderNo());
            exampleChange.setOrderByClause("change_date desc");
            List<TBusOrderChange> changeList=tbusOrderChangeMapper.selectByExample(exampleChange);
            //原来的订单状态
            String orderStatus= "";
            if(null!=changeList && changeList.size()>2){
                //待退款之前的状态
                orderStatus=changeList.get(1).getChangeStatus();
            }

            if(StringUtils.isEmpty(orderStatus)){
                orderStatus=sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.unSend.getName(),CodeEnum.editOrderStatus.getCode());
            }
            order.setTransStatus(orderStatus);

            //修改订单状态
            //editOrderStatus(refund.getOrderNo(),orderStatus);
            TBusOrder newOrder=new TBusOrder();
            newOrder.setUpdateDate(new Date());
            newOrder.setOrderNo(refund.getOrderNo());
            newOrder.setTransStatus(orderStatus);
            busOrderMapper.updateByPrimaryKeySelective(newOrder);

            //增加一条变动记录
            TBusOrderChange change=new TBusOrderChange();
            change.setOrderNo(refund.getOrderNo());
            change.setChangeStatus(order.getTransStatus());
            change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(order.getTransStatus()));
            change.setChangeDate(new Date());
            tbusOrderChangeMapper.insertSelective(change);
        }



    }


}
