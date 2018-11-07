package com.hansy.tyly.common.orders.service.impl;

import java.text.MessageFormat;
import java.util.*;


import com.hansy.tyly.common.orders.dao.mapper.*;
import com.hansy.tyly.common.orders.dao.model.*;
import com.hansy.tyly.common.orders.pojo.*;
import com.hansy.tyly.common.orders.pojo.OrderInfo;
import com.hansy.tyly.common.utils.*;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.dealers.goods.dao.mapper.TBusShoppCartMapper;
import com.hansy.tyly.dealers.goods.dao.mapper.TGoodsBaseInfoMapper;
import com.hansy.tyly.dealers.goods.dao.mapper.TGoodsDistributorMapper;
import com.hansy.tyly.dealers.goods.dao.mapper.TGoodsFilesMapper;
import com.hansy.tyly.dealers.goods.dao.model.*;
import com.hansy.tyly.dealers.login.Dao.mapper.TUserRecAddrMapper;
import com.hansy.tyly.dealers.login.Dao.model.TUserRecAddr;
import com.hansy.tyly.dealers.login.Dao.model.TUserRecAddrExample;
import com.hansy.tyly.dealers.manage.domain.TPubMerInfo;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorInfoMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubMerInfoMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TuserBaseInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.orders.dao.mapper.TBusOrderChangeMapper;
import com.hansy.tyly.common.orders.dao.mapper.TBusOrderDetailMapper;
import com.hansy.tyly.common.orders.dao.mapper.TBusOrderMapper;
import com.hansy.tyly.common.orders.dao.mapper.TBusRefundMapper;
import com.hansy.tyly.common.orders.dao.model.TBusOrder;
import com.hansy.tyly.common.orders.dao.model.TBusOrderChange;
import com.hansy.tyly.common.orders.dao.model.TBusOrderDetail;
import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.common.orders.service.OrderService;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	// 订单信息Mapper
	@Autowired
	private TBusOrderMapper tbusOrderMapper;

	// 订单详情Mapper
	@Autowired
	private TBusOrderDetailMapper tBusOrderDetailMapper;

	// 订单交易变动Mapper
	@Autowired
	private TBusOrderChangeMapper tbusOrderChangeMapper;

	// 退款信息Mapper
	@Autowired
	private TBusRefundMapper refundMapper;

    @Autowired
    private TUserNewsMapper userNewsMapper;
	@Autowired
	public SysUserRoleMenuService sysUserRoleMenuService;
	@Autowired
	private TGoodsDistributorMapper distributorMapper;
	@Autowired
	private OrderInfosMapper orderInfosMapper;

	@Autowired
	private TGoodsBaseInfoMapper goodsBaseInfoMapper;
	@Autowired
    private TUserRecAddrMapper addrMapper;
	@Autowired
	private TuserBaseInfoMapper  tuserBaseInfoMapper;
	@Autowired
	private TBusShoppCartMapper shoppCartMapper;
	@Autowired
	private TGoodsFilesMapper goodsFilesMapper;
	@Autowired
	private TpubDistributorInfoMapper distributorInfoMapper;
	@Autowired
	private TpubMerInfoMapper merInfoMapper;
	@Autowired
	private WechatMsgUtil wechatMsgUtil;







	@Override
	public String getCodeValueByName(String str, String code)throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("codeTypeId", code);
		map.put("status", CodeEnum.getStatusByCode(code));
        List<SysCodeInfo> list= sysUserRoleMenuService.queryCodeInfo(map);
        for (SysCodeInfo node:list) {
            if(node.getCodeInfoName().equals(str)){
                return node.getCodeInfoValue();
            }
        }
        return "";
	}



	/**
	 * 提交订单接口(通过orderType 判断是商户提交 还是 经销商提交)
	 * @throws Exception
	 */
	@Override
	public String createOrder(OrderMessage orderInfo,String formId,
                              String page) throws Exception {

		// 获取订单相关信息然后保存
		TBusOrder tBusOrder = orderInfo.getOrder();
		// 获取订单码值
		String orderNo =NumberUtil.orderNo(10,4,4);;
		String userType="";
		String sellName= "";
		String buyName="";
		if(getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()).equals(
				tBusOrder.getOrderType())){
			userType=tBusOrder.getOrderType();
			sellName= "平台";
			tBusOrder.setPayType(getCodeValueByName(CodeNameEnum.outLinePay.getName(), CodeEnum.payType.getCode()));
			buyName= distributorInfoMapper.selectByPrimaryKey(tBusOrder.getBuyPerson()).getDistributorName();
		}else if(getCodeValueByName(
				CodeNameEnum.outLineOrder.getName(),CodeEnum.orderType.getCode()).equals(tBusOrder.getOrderType())){

			tBusOrder.setPayType(getCodeValueByName(CodeNameEnum.outLinePay.getName(), CodeEnum.payType.getCode()));
		}else{
			sellName= distributorInfoMapper.selectByPrimaryKey(tBusOrder.getSellPerson()).getDistributorName();
			userType= getCodeValueByName(
					CodeNameEnum.dealers.getName(),CodeEnum.orderType.getCode());
			buyName= merInfoMapper.selectByPrimaryKey(tBusOrder.getBuyPerson()).getMerName();
		}
		tBusOrder.setOrderNo(orderNo);
		//支付方式
		//tBusOrder.setPayType(getCodeValueByName(CodeNameEnum.onLinePay.getName(), CodeEnum.payType.getCode()));
        String status=getCodeValueByName(CodeNameEnum.unPay.getName(), CodeEnum.editOrderStatus.getCode());
		// 订单交易状态
		if(StringUtils.isEmpty(tBusOrder.getTransStatus())){
			tBusOrder.setTransStatus(status);
		}

		//交易时间
		if(null==tBusOrder.getTransDate()){
			tBusOrder.setTransDate(new Date());
		}
		tBusOrder.setUpdateDate(new Date());
		tbusOrderMapper.insertSelective(tBusOrder);
        //存到变更表
        TBusOrderChange change = new TBusOrderChange();
        change.setChangeStatus(status);
        change.setOrderNo(orderNo);
        change.setChangeDesc(CodeNameEnum.unPay.getName());
        change.setChangeDate(tBusOrder.getUpdateDate());
        tbusOrderChangeMapper.insertSelective(change);


		/*
		 * 向订单明细表t_bus_order_detail插入数据
		 */
		List<TBusOrderDetail> OrderDetail = orderInfo.getDetail();
		List<TGoodsBaseInfo> goodsList=new ArrayList<>();
		for (TBusOrderDetail tbusOrderDetail : OrderDetail) {
			tbusOrderDetail.setOrderNo(orderNo);
			tbusOrderDetail.setTransDate(tBusOrder.getUpdateDate());
			tBusOrderDetailMapper.insertSelective(tbusOrderDetail);
			//创建订单后 删除 购物车
			TBusShoppCartExample example=new TBusShoppCartExample();
			TBusShoppCartExample.Criteria criteria=example.createCriteria();
			criteria.andCustNoEqualTo(tBusOrder.getBuyPerson());
			criteria.andGoodsNoEqualTo(tbusOrderDetail.getGoodsNo());
			shoppCartMapper.deleteByExample(example);

			goodsList.add(goodsBaseInfoMapper.selectByPrimaryKey(tbusOrderDetail.getGoodsNo()));

			if(getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(
					tBusOrder.getOrderType())){
				//减库存
				TGoodsDistributorExample teexample=new TGoodsDistributorExample();
				TGoodsDistributorExample.Criteria criteria1=teexample.createCriteria();
				criteria1.andGoodsNoEqualTo(tbusOrderDetail.getGoodsNo());
				criteria1.andDistributorNoEqualTo(tBusOrder.getSellPerson());
				List<TGoodsDistributor> list=distributorMapper.selectByExample(teexample);
				if(null!=list && list.size()>0){
                    TGoodsDistributor distributor=list.get(0);
                    distributor.setGoodsStock(distributor.getGoodsStock()-tbusOrderDetail.getGoodsNum());
                    distributorMapper.updateByPrimaryKeySelective(distributor);
                }
			}

			// 还要根据 订单详情增加商品销量
			TGoodsBaseInfo goodsBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(tbusOrderDetail.getGoodsNo());
			goodsBaseInfo.setGoodsSaleNum(tbusOrderDetail.getGoodsNum()+goodsBaseInfo.getGoodsSaleNum());
			goodsBaseInfoMapper.updateByPrimaryKeySelective(goodsBaseInfo);

		}
		if(getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(
				tBusOrder.getOrderType())){
            //商户推送模板消息
            sendMessage(tBusOrder,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(tBusOrder.getBuyPerson()),null,null);
		}else if(getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()).equals(
                tBusOrder.getOrderType())){
            //商户推送模板消息
            sendMessage(tBusOrder,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(tBusOrder.getBuyPerson()),formId,page);
        }


		if(null!=orderInfo.getFiles() && !orderInfo.getFiles().isEmpty()){
			//保存商品附件
			List<TGoodsFiles> goodsFilesList=orderInfo.getFiles();
			if(null!=goodsFilesList && goodsFilesList.size()>0){
				goodsFilesList.forEach(node -> {
					node.setGoodsNo(tBusOrder.getOrderNo());
					node.setUploadDate(new Date());
					//设置文件状态
					try {
						node.setFileStatus(getCodeValueByName(
                                CodeNameEnum.using.getName(),
                                CodeEnum.goodsFileStatus.getCode()));
						node.setFileType(getCodeValueByName(
								CodeNameEnum.orderFiles.getName(),
								CodeEnum.fileType.getCode()));
					} catch (Exception e) {
						e.printStackTrace();
					}

					goodsFilesMapper.insertSelective(node);


				});
			}
		}


		if(!getCodeValueByName(
				CodeNameEnum.outLineOrder.getName(),CodeEnum.orderType.getCode()).equals(
				tBusOrder.getOrderType())){
			//插入信息记录
			insertOrderNews(tBusOrder.getOrderNo(),tBusOrder.getSellPerson(),userType,
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
							sellName,
							buyName,
							sysUserRoleMenuService.getCodeNameByValue(status),
							tBusOrder.getOrderNo()
					),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.
									ORDER_TITL),
							sysUserRoleMenuService.getCodeNameByValue(status))

			);
		}




		return orderNo;
	}

	@Override
	public void insertOrderNews(String orderNo,String userNo,String userType,String content,String title) throws Exception {
		//增加一条信息 到  用户信息 表里面  推送到微信
		TUserNews userNews = new TUserNews();
		userNews.setUserNo(userNo);
		userNews.setUserType(userType);
		userNews.setNewsType(getCodeValueByName(CodeNameEnum.xitongNews.getName(),CodeEnum.newsType.getCode()));
		userNews.setNewsDate(new Date());
		userNews.setNewsContent(content);
		userNews.setIsRead(Context.READ_FALSE);
		userNews.setNewsTitle(title);
		userNews.setOrderNo(orderNo);
		userNews.setTableKey(UUIDUtils.getUuid());
		userNewsMapper.insertSelective(userNews);


	}

	@Override
	public void editOrder(OrderMessage orderInfo,String formId,
                          String page) throws Exception {

		// 获取订单相关信息然后保存
		TBusOrder tBusOrder = orderInfo.getOrder();
		TBusOrder oldOrder=tbusOrderMapper.selectByPrimaryKey(tBusOrder.getOrderNo());
		//修改金额之前 先判断订单状态是否为代付款
		if(!getCodeValueByName(
				CodeNameEnum.unPay.getName(),CodeEnum.editOrderStatus.getCode()).equals(
						oldOrder.getTransStatus())){
			throw new RuntimeException("该订单状态不能修改金额");
		}


		tBusOrder.setUpdateDate(new Date());
		tbusOrderMapper.updateByPrimaryKeySelective(tBusOrder);
		TBusOrder order=tbusOrderMapper.selectByPrimaryKey(tBusOrder.getOrderNo());

		//增加一条信息 到  用户信息 表里面  推送到微信
		String name= merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName();
		if(StringUtils.isEmpty(name)){
			name="";
		}
		//插入信息记录
		insertOrderNews(tBusOrder.getOrderNo(),
				order.getBuyPerson(),
				getCodeValueByName(CodeNameEnum.Merchant.getName(), CodeEnum.userType.getCode()),
				"您好"+name+",您的订单金额变更为"+tBusOrder.getTotalAmt(),
				"订单金额变更"
		);


		/*
		 * 向订单明细表t_bus_order_detail插入数据
		 */
		List<TBusOrderDetail> orderDetail = orderInfo.getDetail();
		List<TGoodsBaseInfo> goodsList=new ArrayList<>();
		if(null!=orderDetail && orderDetail.size()>0){
			orderDetail.forEach(node->{
                goodsList.add(goodsBaseInfoMapper.selectByPrimaryKey(node.getGoodsNo()));

				TBusOrderDetailExample example=new TBusOrderDetailExample();
				TBusOrderDetailExample.Criteria criteria=example.createCriteria();
				criteria.andGoodsNoEqualTo(node.getGoodsNo());
				criteria.andOrderNoEqualTo(tBusOrder.getOrderNo());
				List<TBusOrderDetail> list=tBusOrderDetailMapper.selectByExample(example);
				if(null!= list && list.size()>0){
					if(null!=node.getGoodsNum()){
						list.get(0).setGoodsNum(node.getGoodsNum());
					}

					list.get(0).setTransDate(new Date());
					if(null!=node.getGoodsAmt()){
						list.get(0).setGoodsAmt(node.getGoodsAmt());
					}
					tBusOrderDetailMapper.updateByPrimaryKeySelective(list.get(0));
				}else{
					node.setTransDate(new Date());
					tBusOrderDetailMapper.insertSelective(node);
				}

			});

		}
		oldOrder.setTotalAmt(tBusOrder.getTotalAmt());
		if(StringUtils.isNotBlank(formId)){
			//商户推送模板消息
			sendMessage(oldOrder,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(oldOrder.getBuyPerson()),formId,page);
		}


	}

	@Override
	public void deleteAddrInfo(String tableKey) throws Exception {
		TUserRecAddr addr=new TUserRecAddr();
		addr.setTableKey(tableKey);
		addr.setStatus(Context.ADDR_STATUS_DELETE);
		addrMapper.updateByPrimaryKeySelective(addr);
	}

	@Override
	public TUserRecAddr getOneAddrInfo(String tableKey) throws Exception {
		return addrMapper.selectByPrimaryKey(tableKey);
	}




    /**
	 * 查看订单列表接口
	 */
	@Override
	public List<OrderInfo> getOrderList(Map<String, Object> inMap) throws Exception{



		// 判断是否是商户订单
		NPageHelper.startPage(inMap);



        List<OrderInfo> list=orderInfosMapper.getOrdersListByParams(inMap);

		return list;

	}
	//
	@Override
	public List<Map<String,Object>> doOrderList(List<OrderInfo> list) throws Exception{
		List<Map<String,Object>> mapList=new ArrayList<>();
		list.forEach(node->{
			Map<String,Object> map=new HashMap<>();
			map.put("orderNo",node.gettBusOrder().getOrderNo());
			List<Map<String,Object>> templist=orderInfosMapper.getOrdersDetailListByParams(map);
			List<Map<String,Object>> reflist= orderInfosMapper.getRefundGoodsByParams(map);
			List<Map<String,Object>> nureflist= orderInfosMapper.getUnRefundGoodsByParams(map);
			Map<String,Object> changeDate=orderInfosMapper.getChangeDate(map);
			Map<String,Object> tmp=new HashMap<>();
			tmp.put("order",node.gettBusOrder());

			try {
				String orderType=getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode());
				if(!orderType.equals(node.gettBusOrder().getOrderType())){
					TpubDistributorInfo distributorInfo=distributorInfoMapper.selectByPrimaryKey(node.gettBusOrder().getSellPerson());
					if(null!= distributorInfo && StringUtils.isNotBlank(distributorInfo.getDistributorNo())){
						tmp.put("disName",distributorInfo.getDistributorName());
					}

					TpubMerInfo merInfo=merInfoMapper.selectByPrimaryKey(node.gettBusOrder().getBuyPerson());
					if(null!= merInfo && StringUtils.isNotBlank(merInfo.getMerNo())){
						tmp.put("merName",merInfo.getMerName());
					}
				}

				String outLineOrder=getCodeValueByName(CodeNameEnum.outLineOrder.getName(),CodeEnum.orderType.getCode());
				if(outLineOrder.equals(node.gettBusOrder().getOrderType())){
					TGoodsFilesExample filesExample=new TGoodsFilesExample();
					TGoodsFilesExample.Criteria filesExampleCriteria=filesExample.createCriteria();
					filesExampleCriteria.andGoodsNoEqualTo(node.gettBusOrder().getOrderNo());
					filesExampleCriteria.andFileTypeEqualTo(getCodeValueByName(
							CodeNameEnum.orderFiles.getName(),
							CodeEnum.fileType.getCode()));
					List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(filesExample);
					if(null!=filesList && !filesList.isEmpty()){
						List<String> paths=new ArrayList<>();
						filesList.forEach(tGoodsFiles -> {
							paths.add(tGoodsFiles.getFilePath());
						});
						tmp.put("files",paths);
					}


				}

			} catch (Exception e) {
				e.printStackTrace();
			}




			tmp.put("order",node.gettBusOrder());
			tmp.put("orderDetail",templist);
			tmp.put("refund",reflist);
			tmp.put("nureflist",nureflist);

			tmp.put("changeDate",changeDate);
			mapList.add(tmp);
		});




		return mapList;

	}


	/**
	 * 查看订单详情接口
	 */
	@Override
	public Map<String,Object> getOrderDetail(String orderNo) throws Exception{
		Map<String,Object> inMap=new HashMap();
		inMap.put("orderNo", orderNo);
		List<Map<String,Object>> list=doOrderList(getOrderList(inMap));

		// 先用订单号和待审核状态去查询是否有记录
		//待审核状态
		String status=getCodeValueByName(CodeNameEnum.waitAppr.getName(),CodeEnum.apprStatus.getCode());
		TBusRefundExample refundExample=new TBusRefundExample();
		TBusRefundExample.Criteria refc=refundExample.createCriteria();
		refc.andOrderNoEqualTo(orderNo);
		refc.andApprStatusEqualTo(status);
		Integer  refCount=refundMapper.selectCountByExample(refundExample);
		Map<String,Object> result=new HashMap();
		result.putAll(list.get(0));
		result.put("refCount",refCount);
		return result;
	}

	/**
	 * 商户/经销商退款申请
	 * @throws Exception
	 */
	@Override
	public void merchantRefund(RefundList refundList,String formId,
							   String page) throws Exception{
		//待审核状态
		String status=getCodeValueByName(CodeNameEnum.waitAppr.getName(),CodeEnum.apprStatus.getCode());
		String unFund=getCodeValueByName(
				CodeNameEnum.unFund.getName(),CodeEnum.editOrderStatus.getCode());

		List<TGoodsBaseInfo> goodsBaseInfos=new ArrayList<>();
		TBusOrder busOrder=tbusOrderMapper.selectByPrimaryKey(refundList.getRefundList().get(0).getOrderNo());

		TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
		TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
		detailCriteria.andOrderNoEqualTo(busOrder.getOrderNo());
		List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);

		int refGoodsNum=0;
		for (TBusOrderDetail detail:detailList) {
			refGoodsNum+=detail.getGoodsNum();
		}
		for (TBusRefund refund:refundList.getRefundList()) {
			refGoodsNum-=refund.getGoodsNum();
		}


		String refOrderStatus="";
		//全退
		if(refGoodsNum == 0){
			refOrderStatus=getCodeValueByName(
					CodeNameEnum.unFund.getName(), CodeEnum.editOrderStatus.getCode());

		}else{//部分退款
			refOrderStatus=getCodeValueByName(
					CodeNameEnum.partFund.getName(), CodeEnum.editOrderStatus.getCode());

		}

		refundList.getRefundList().forEach(refund -> {
			/*//设置订单状态为待退款
			TBusOrder order=new TBusOrder();
			order.setUpdateDate(new Date());
			order.setOrderNo(refund.getOrderNo());
			order.setTransStatus(unFund);
			tbusOrderMapper.updateByPrimaryKeySelective(order);*/


			goodsBaseInfos.add(goodsBaseInfoMapper.selectByPrimaryKey(refund.getGoodsNo()));

			TBusRefundExample example=new TBusRefundExample();
			TBusRefundExample.Criteria criteria=example.createCriteria();
			criteria.andOrderNoEqualTo(refund.getOrderNo());
			criteria.andGoodsNoEqualTo(refund.getGoodsNo());
			criteria.andApprStatusEqualTo(status);
			List<TBusRefund>  list=refundMapper.selectByExample(example);
			if(null!=list && list.size()>0){
				list.get(0).setGoodsNum(list.get(0).getGoodsNum()+refund.getGoodsNum());
				refundMapper.updateByPrimaryKeySelective(list.get(0));
			}else{
				refund.setApplyDate(new Date());
				refund.setApprStatus(status);
				refundMapper.insertSelective(refund);
			}




		});



		//修改订单状态
		editOrderStatus(refundList.getRefundList().get(0).getOrderNo(),refOrderStatus);
		System.out.print("申请退款时间 ="+new Date());
		busOrder.setTransStatus(refOrderStatus);
		//存到变更表
		TBusOrderChange change = new TBusOrderChange();
		change.setChangeStatus(refOrderStatus);
		change.setOrderNo(busOrder.getOrderNo());
		change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(refOrderStatus));
		change.setChangeDate(new Date());
		tbusOrderChangeMapper.insertSelective(change);
		System.out.print("变更时间 ="+new Date());

		if(busOrder.getOrderType().equals(
				getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()))) {
			//经销商推送模板消息
			sendMessage(busOrder, goodsBaseInfos, tuserBaseInfoMapper.selectByPrimaryKey(busOrder.getBuyPerson()), formId, page);
		}else {
			//商户推送模板消息
			sendMessage(busOrder, goodsBaseInfos, tuserBaseInfoMapper.selectByPrimaryKey(busOrder.getBuyPerson()), null, null);
		}



	}


	//审批退款
	@Override
	public void approveRefund(String orderNo, String apprStatus,String disNo,
							  String formId, String page
							  ) throws Exception {
	    //待退款状态
        String unfund=getCodeValueByName(
                CodeNameEnum.unFund.getName(),CodeEnum.editOrderStatus.getCode());
		TBusOrder order= tbusOrderMapper.selectByPrimaryKey(orderNo);



		//审批通过
		if(getCodeValueByName(
				CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(order.getOrderType())){

			if(apprStatus.equals(getCodeValueByName(
					CodeNameEnum.apprSuccess.getName(), CodeEnum.apprStatus.getCode()))){
				int goodsNum=0;

				List<TBusRefund> refundsNum=refundMapper.getGoodsNumByOrderNo(orderNo);
				TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
				TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
				detailCriteria.andOrderNoEqualTo(orderNo);
				List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);

				for (TBusOrderDetail detail:detailList) {
					goodsNum+=detail.getGoodsNum();
				}

				for (TBusRefund ref:refundsNum) {
					goodsNum-=ref.getGoodsNum();
				}






				//待退款时 以前的状态  审批通过的 就 变成 已退款
				if(unfund.equals(order.getTransStatus()) || goodsNum == 0){
					//修改订单状态
					editOrderStatus(orderNo,getCodeValueByName(
							CodeNameEnum.orderRefund.getName(), CodeEnum.editOrderStatus.getCode()));
					order.setTransStatus(getCodeValueByName(
							CodeNameEnum.orderRefund.getName(), CodeEnum.editOrderStatus.getCode()));
					//增加一条变动记录
					TBusOrderChange change=new TBusOrderChange();
					change.setOrderNo(orderNo);
					change.setChangeStatus(order.getTransStatus());
					change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(order.getTransStatus()));
					change.setChangeDate(new Date());
					tbusOrderChangeMapper.insertSelective(change);
				}
			}
			//审批未通过 或者部分退款通过审批的时候
			if(!getCodeValueByName(
					CodeNameEnum.orderRefund.getName(),CodeEnum.editOrderStatus.getCode()).equals(
							order.getTransStatus())){
				//查询待退款的时间
				TBusOrderChangeExample exampleChange=new TBusOrderChangeExample();
				TBusOrderChangeExample.Criteria criteriaChange=exampleChange.createCriteria();
				/*criteriaChange.andChangeStatusEqualTo(unfund);*/
				criteriaChange.andOrderNoEqualTo(orderNo);
				//exampleChange.setOrderByClause("change_date desc");
				exampleChange.setOrderByClause("table_key desc");
				List<TBusOrderChange> changeList=tbusOrderChangeMapper.selectByExample(exampleChange);
				/*List<TBusOrderChange> cList=new ArrayList<>();*/
				//原来的订单状态
				String orderStatus= "";
				if(null!=changeList && changeList.size()>0){
					//待退款之前的状态
					orderStatus=changeList.get(1).getChangeStatus();
				}


				if(StringUtils.isEmpty(orderStatus)){
					orderStatus=getCodeValueByName(
							CodeNameEnum.unSend.getName(),CodeEnum.editOrderStatus.getCode());
				}

				//修改订单状态
				/*editOrderStatus(orderNo,orderStatus);*/
				order.setTransStatus(orderStatus);
				TBusOrder newOrder=new TBusOrder();
				newOrder.setUpdateDate(new Date());
				newOrder.setOrderNo(orderNo);
				newOrder.setTransStatus(orderStatus);
				tbusOrderMapper.updateByPrimaryKeySelective(newOrder);
				System.out.print("审批退款时间 ="+newOrder.getUpdateDate());
				//增加一条变动记录
				TBusOrderChange change=new TBusOrderChange();
				change.setOrderNo(orderNo);
				change.setChangeStatus(orderStatus);
				change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(orderStatus));
				change.setChangeDate(new Date());
				tbusOrderChangeMapper.insertSelective(change);
				System.out.print("变更时间="+change.getChangeDate());

			}



			String name= merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName();
			String sellname= distributorInfoMapper.selectByPrimaryKey(order.getSellPerson()).getDistributorName();
			if(StringUtils.isEmpty(name)){
				name="";
			}
			//插入信息记录
			try {
				insertOrderNews(order.getOrderNo(),order.getBuyPerson(),
						getCodeValueByName(CodeNameEnum.Merchant.getName(),CodeEnum.userType.getCode()),
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



			TBusRefundExample refexample=new TBusRefundExample();
			TBusRefundExample.Criteria refcriteria=refexample.createCriteria();
			refcriteria.andOrderNoEqualTo(orderNo);

			refcriteria.andApprStatusEqualTo(getCodeValueByName(
                    CodeNameEnum.waitAppr.getName(),CodeEnum.apprStatus.getCode()));
			List<TBusRefund>  reflist=refundMapper.selectByExample(refexample);

			List<TGoodsBaseInfo> goodsList=new ArrayList<>();
			reflist.forEach((TBusRefund refund) -> {

			    goodsList.add(goodsBaseInfoMapper.selectByPrimaryKey(refund.getGoodsNo()));
				refund.setApprStatus(apprStatus);
				refund.setApprDate(new Date());
				refundMapper.updateByPrimaryKeySelective(refund);

                //如果审批状态为通过那么修改对应的订单状态为已退款
                String code= null;
                try {
                    code = getCodeValueByName(
                            CodeNameEnum.apprSuccess.getName(), CodeEnum.apprStatus.getCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }




                if(code.equals(apprStatus)){


                    //还原库存
                    TGoodsDistributorExample example=new TGoodsDistributorExample();
                    TGoodsDistributorExample.Criteria criteria=example.createCriteria();
                    criteria.andGoodsNoEqualTo(refund.getGoodsNo());
                    criteria.andDistributorNoEqualTo(disNo);
                    List<TGoodsDistributor> list=distributorMapper.selectByExample(example);
                    TGoodsDistributor distributor=list.get(0);
                    distributor.setGoodsStock(distributor.getGoodsStock()+refund.getGoodsNum());
                    distributorMapper.updateByPrimaryKeySelective(distributor);



                }

            });
            //商户推送模板消息
            sendMessage(order,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(order.getBuyPerson()),null,null);
			//经销商推送模板消息
			sendMessage(order,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(order.getSellPerson()),formId,page);
		}

	}

	@Override
	public List<RefundListMessage> getRefundOrders(Map<String, Object> params) throws Exception {
		NPageHelper.startPage(params);

		List<RefundListMessage> list=orderInfosMapper.getRefundListByParams(params);
		list.forEach(refundListMessage -> {
			TBusOrder order=tbusOrderMapper.selectByPrimaryKey(
					refundListMessage.getRefund().getOrderNo());
			refundListMessage.setOrder(order);
			refundListMessage.setMerName(
					merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName());

		});


		return list;
	}


    public void sendMessage(TBusOrder order,List<TGoodsBaseInfo> goods,
                            TuserBaseInfo userBaseInfo,String formId,
                            String page){
        OrderNewDemo orderNewDemo=new OrderNewDemo();
        orderNewDemo.setOrder(order);
        orderNewDemo.setGoods(goods);
        orderNewDemo.setUserBaseInfo(userBaseInfo);
      //购买
		try{
			if(getCodeValueByName(
					CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()).equals(order.getOrderType())){
				orderNewDemo.setSaleOrBuy("0");
			}else{
				//售卖
				orderNewDemo.setSaleOrBuy("1");
			}
		}catch (Exception e){
			e.printStackTrace();
		}

        if(StringUtils.isNotBlank(formId)){
            orderNewDemo.setPage(page);
            orderNewDemo.setFormId(formId);
            wechatMsgUtil.getPostInfo1(orderNewDemo);
        }else{
            wechatMsgUtil.getPostInfo(orderNewDemo);
        }
        

    }



    /**
     * 经销商回复平台
     * @param orderNo
     * @param changeStatus
     * @param disNo
     * @throws Exception
     */
    @Override
    public void processOrdersForPlatform(String orderNo, String changeStatus,
										 String disNo,String formId,String page) throws Exception {
        TBusOrder order=tbusOrderMapper.selectByPrimaryKey(orderNo);



        String oldStatus=order.getTransStatus();
        //先判断 改订单是否存在 以及 是否属于平台
        if(null!=order && order.getOrderType().equals(
                getCodeValueByName(
                        CodeNameEnum.platform.getName(), CodeEnum.userType.getCode())) &&
                order.getBuyPerson().equals(disNo)
                ) {
            //存到变更表
            TBusOrderChange change = new TBusOrderChange();
            change.setChangeStatus(changeStatus);
            change.setOrderNo(orderNo);
            change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(changeStatus));
            change.setChangeDate(new Date());
            tbusOrderChangeMapper.insertSelective(change);
			//修改订单状态
			editOrderStatus(orderNo,changeStatus);

			//增加一条信息 到  用户信息 表里面  推送到微信
			//增加一条信息 到  用户信息 表里面  推送到微信
			String name= "平台";
			//插入信息记录
			String buyName= distributorInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getDistributorName();
			insertOrderNews(order.getOrderNo(),order.getSellPerson(),
					getCodeValueByName(CodeNameEnum.platform.getName(), CodeEnum.userType.getCode()),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
							name,
							buyName,
							sysUserRoleMenuService.getCodeNameByValue(changeStatus),
							order.getOrderNo()
							),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.ORDER_TITL),

							sysUserRoleMenuService.getCodeNameByValue(changeStatus))
			);

			TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
			TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
			detailCriteria.andOrderNoEqualTo(orderNo);
			List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);
			List<TGoodsBaseInfo> goodsList=new ArrayList<>();
			if(null!=detailList && detailList.size()>0){
				detailList.forEach(node->{
					goodsList.add(goodsBaseInfoMapper.selectByPrimaryKey(node.getGoodsNo()));
				});
			}
			order.setTransStatus(changeStatus);
			//经销商推送模板消息
			sendMessage(order,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(order.getBuyPerson()),formId,page);
        }else {
            throw new RuntimeException("该订单不存在或不属于该平台");
        }
    }
    /**
     * 商户回复经销商
     * @param orderNo
     * @param changeStatus
     * @param merNo
     * @throws Exception
     */
    @Override
    public void processOrdersForDelear(String orderNo, String changeStatus, String merNo) throws Exception {


        TBusOrder order=tbusOrderMapper.selectByPrimaryKey(orderNo);
        //先判断 改订单是否存在 以及 是否属于平台
        if(null!=order &&
                order.getBuyPerson().equals(merNo)
                ) {
			//修改订单状态
			editOrderStatus(orderNo,changeStatus);

            //存到变更表
            TBusOrderChange change = new TBusOrderChange();
            change.setChangeStatus(changeStatus);
            change.setOrderNo(orderNo);
            change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(changeStatus));
            change.setChangeDate(new Date());
            tbusOrderChangeMapper.insertSelective(change);


            //增加一条信息 到  用户信息 表里面  推送到微信
			String name= distributorInfoMapper.selectByPrimaryKey(order.getSellPerson()).getDistributorName();
			String buyname= merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName();
			if(StringUtils.isEmpty(name)){
				name="";
			}
			//插入信息记录
			//尊敬的{0},用户{1}向您提交了一份{2}订单{3}
			insertOrderNews(order.getOrderNo(),order.getSellPerson(),
					getCodeValueByName(CodeNameEnum.dealers.getName(), CodeEnum.userType.getCode()),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
							name,
							buyname,
							sysUserRoleMenuService.getCodeNameByValue(changeStatus),
							orderNo
							),
					//您有{1}的订单
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.ORDER_TITL),
							sysUserRoleMenuService.getCodeNameByValue(changeStatus))
			);
			//库存还原
			TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
			TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
			detailCriteria.andOrderNoEqualTo(orderNo);
			List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);
			List<TGoodsBaseInfo> goodsList=new ArrayList<>();
			if (getCodeValueByName(
					CodeNameEnum.unPay.getName(),CodeEnum.editOrderStatus.getCode()).equals(
					order.getTransStatus())){
				detailList.forEach(tbusOrderDetail->{
					//创建订单后 删除 购物车
					TBusShoppCartExample example=new TBusShoppCartExample();
					TBusShoppCartExample.Criteria criteria=example.createCriteria();
					criteria.andCustNoEqualTo(order.getBuyPerson());
					criteria.andGoodsNoEqualTo(tbusOrderDetail.getGoodsNo());
					shoppCartMapper.deleteByExample(example);
					goodsList.add(goodsBaseInfoMapper.selectByPrimaryKey(tbusOrderDetail.getGoodsNo()));
				});

			}
			order.setTransStatus(changeStatus);
			//商户推送模板消息
            sendMessage(order,goodsList,tuserBaseInfoMapper.selectByPrimaryKey(order.getBuyPerson()),null,null);

        }else {
            throw new RuntimeException("该订单不存在或不属于该经销商");
        }
    }



	//修改订单状态的方法
	public void editOrderStatus(String orderNo, String changeStatus) throws Exception{
		Map<String,Object> pmap=new HashMap<>();
		pmap.put("changeStatus",changeStatus);
		pmap.put("orderNo",orderNo);
		int count =tbusOrderMapper.updateStatusByPrimaryKey(pmap);
		if(count!=1){
			throw new RuntimeException("操作失败");
		}

	}


	//处理订单
	@Override
	public void processOrders(String orderNo, String changeStatus,
							  String disNo,String formId,String page) throws Exception {
		TBusOrder order=tbusOrderMapper.selectByPrimaryKey(orderNo);
		String oldStatus=order.getTransStatus();


		//先判断 改订单是否存在 以及 是否属于经销商
		if(null!=order && order.getOrderType().equals(
				getCodeValueByName(
						CodeNameEnum.dealers.getName(), CodeEnum.userType.getCode())) &&
                order.getSellPerson().equals(disNo)
                ){
			//存到变更表
			TBusOrderChange change=new TBusOrderChange();
			change.setChangeStatus(changeStatus);
			change.setOrderNo(orderNo);
			change.setChangeDesc(sysUserRoleMenuService.getCodeNameByValue(changeStatus));
			change.setChangeDate(new Date());
			tbusOrderChangeMapper.insertSelective(change);
			//修改订单状态
			order.setTransStatus(changeStatus);
			//tbusOrderMapper.updateByPrimaryKeySelective(order);

			editOrderStatus(orderNo,changeStatus);
			//增加一条信息 到  用户信息 表里面  推送到微信
			String name= merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName();
			String sellname= distributorInfoMapper.selectByPrimaryKey(order.getSellPerson()).getDistributorName();
			if(StringUtils.isEmpty(name)){
				name="";
			}
			//插入信息记录
			insertOrderNews(order.getOrderNo(),order.getBuyPerson(),
					getCodeValueByName(CodeNameEnum.Merchant.getName(), CodeEnum.userType.getCode()),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.OREDER_DEMO),
							name,
							sellname,
							sysUserRoleMenuService.getCodeNameByValue(changeStatus),
							order.getOrderNo()
							),
					MessageFormat.format(
							PropertyUtil.getPropertyValue(BaseConstant.APP_PER,BaseConstant.ORDER_TITL),

							sysUserRoleMenuService.getCodeNameByValue(changeStatus))
			);
			//库存还原
			TBusOrderDetailExample detailExample=new TBusOrderDetailExample();
			TBusOrderDetailExample.Criteria detailCriteria=detailExample.createCriteria();
			detailCriteria.andOrderNoEqualTo(orderNo);
			List<TBusOrderDetail> detailList=tBusOrderDetailMapper.selectByExample(detailExample);
			List<TGoodsBaseInfo> goods=new ArrayList<>();
			//如果状态为退款成功并且之前的状态为交易成功
			if(getCodeValueByName(
					CodeNameEnum.orderSeccess.getName(),CodeEnum.editOrderStatus.getCode()).equals(
							order.getTransStatus()
					) &&oldStatus.equals(getCodeValueByName(
                    CodeNameEnum.orderSeccess.getName(),CodeEnum.editOrderStatus.getCode()))){


				detailList.forEach(node->{
					goods.add(goodsBaseInfoMapper.selectByPrimaryKey(node.getGoodsNo()));
					TGoodsDistributorExample example=new TGoodsDistributorExample();
					TGoodsDistributorExample.Criteria criteria=example.createCriteria();
					criteria.andGoodsNoEqualTo(node.getGoodsNo());
					criteria.andDistributorNoEqualTo(order.getSellPerson());
					List<TGoodsDistributor> list=distributorMapper.selectByExample(example);
					TGoodsDistributor distributor=list.get(0);
					distributor.setGoodsStock(distributor.getGoodsStock()+node.getGoodsNum());
					distributorMapper.updateByPrimaryKeySelective(distributor);
					// 还要根据 订单详情减少商品销量
					/*TGoodsBaseInfo goodsBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(node.getGoodsNo());
					goodsBaseInfo.setGoodsSaleNum(node.getGoodsNum()-goodsBaseInfo.getGoodsSaleNum());
					goodsBaseInfoMapper.updateByPrimaryKeySelective(goodsBaseInfo);*/
				});
			}
			order.setTransStatus(changeStatus);
			//经销商推送模板消息
			sendMessage(order,goods,tuserBaseInfoMapper.selectByPrimaryKey(order.getSellPerson()),formId,page);
		}else{
			throw new RuntimeException("该订单不存在或不属于该经销商");
		}
	}

	@Override
	public OrderInfo getRefundOrderInfo(Integer tableKey) throws Exception {
		OrderInfo orderInfo=orderInfosMapper.getRefundInfoByKey(tableKey);
		TBusOrder order=orderInfo.gettBusOrder();
		order.setBuyPerson(merInfoMapper.selectByPrimaryKey(order.getBuyPerson()).getMerName());


		return orderInfo;
	}

    @Override
    public List<TUserRecAddr> getAddrInfo(String userNo,String isDefault) throws Exception {
        TUserRecAddrExample example=new TUserRecAddrExample();
        TUserRecAddrExample.Criteria criteria=example.createCriteria();
        criteria.andUserNoEqualTo(userNo);
        //使用状态
        criteria.andStatusEqualTo(Context.ADDR_STATUS_USEING);
		List<TUserRecAddr> list=new ArrayList<>();
		if(StringUtils.isNotBlank(isDefault)){
			criteria.andIsDefaultEqualTo(isDefault);
			list=addrMapper.selectByExample(example);
			if(null!=list && list.size()>0){
				return list;
			}else {
				TUserRecAddrExample exampleTemp=new TUserRecAddrExample();
				TUserRecAddrExample.Criteria criteriaTemp=exampleTemp.createCriteria();
				criteriaTemp.andUserNoEqualTo(userNo);
				//使用状态
				criteriaTemp.andStatusEqualTo(Context.ADDR_STATUS_USEING);
				return addrMapper.selectByExample(exampleTemp);

			}
		}
			list=addrMapper.selectByExample(example);




        return list;
    }

    @Override
    public void editAddrInfo(TUserRecAddr addr) throws Exception {
		if(Context.ADDR_DEFAULT_TRUE.equals(addr.getIsDefault())){
			TUserRecAddrExample example=new TUserRecAddrExample();
			TUserRecAddrExample.Criteria criteria=example.createCriteria();
			criteria.andUserNoEqualTo(addr.getUserNo());
			//使用状态
			criteria.andStatusEqualTo(Context.ADDR_STATUS_USEING);
			List<TUserRecAddr> list= addrMapper.selectByExample(example);
			if(null!=list && list.size()>0){
				list.forEach(node->{
					node.setUpdateDate(new Date());
					node.setIsDefault(Context.ADDR_DEFAULT_FALSE);
					addrMapper.updateByPrimaryKeySelective(node);
				});
			}
		}

        if(StringUtils.isNotBlank(addr.getTableKey())){
            addr.setUpdateDate(new Date());
            addrMapper.updateByPrimaryKeySelective(addr);
        }else{
			TuserBaseInfo tuserBaseInfo=tuserBaseInfoMapper.selectByPrimaryKey(addr.getUserNo());
			if(null!=tuserBaseInfo){
				addr.setUserName(tuserBaseInfo.getUserName());
				addr.setTableKey(UUIDUtils.getUuid());
				addr.setInsertDate(new Date());
				addr.setStatus(Context.ADDR_STATUS_USEING);
				addrMapper.insertSelective(addr);
			}else{
				throw  new RuntimeException("用户不存在");
			}

        }


    }



}
