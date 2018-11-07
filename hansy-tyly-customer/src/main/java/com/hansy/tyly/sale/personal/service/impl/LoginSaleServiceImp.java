package com.hansy.tyly.sale.personal.service.impl;
import com.hansy.tyly.sale.personal.dao.mapper.TPubVisitRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.sale.personal.dao.mapper.LoginSaleMapper;
import com.hansy.tyly.sale.personal.service.LoginSaleService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LoginSaleServiceImp implements LoginSaleService{
	@Autowired
	private LoginSaleMapper loginSaleMapper;
	@Autowired
    private TPubVisitRecordMapper tPubVisitRecordMapper;

	@Override
	public SysUser getSysuserInfo(String userId) {
		SysUser sysUser = new SysUser();
		try {
			sysUser = loginSaleMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sysUser;
	}

	@Override
	public Map<String, Object> getReportOfVisit(String saleNo, Integer num, String dateType, String date)throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map=new HashMap<>();
		if("month".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}else if("year".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}

		List<Integer> list=new ArrayList<>();
		if(null==num){
			num=6;
		}
		for(int i=1;i< num+1; i++ ){
			list.add(i);
		}


		List<Map<String,Object>> visits=tPubVisitRecordMapper.getVisits(saleNo,dateType,date,list);
		map.put("visit",transDate(visits,dateType));


		return map;
	}

	@Override
	public Map<String, Object> getReportOfAchievement(String saleNo, Integer num, String dateType, String date)throws Exception {
		Map<String, Object> map=new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if("month".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}else if("year".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}

		List<Integer> list=new ArrayList<>();
		if(null==num){
			num=6;
		}
		for(int i=1;i< num+1; i++ ){
			list.add(i);
		}

		List<Map<String,Object>> userCounts=tPubVisitRecordMapper.getUserCount(saleNo,dateType,date,list);
		map.put("userCounts",transDate(userCounts,dateType));
		List<Map<String,Object>> consumeCounts=tPubVisitRecordMapper.getOneConsumeCount(saleNo,dateType,date,list);
		map.put("consumeCounts",transDate(consumeCounts,dateType));

		List<Map<String,Object>> saleUserOrConsumes=tPubVisitRecordMapper.consume(dateType,date);
		map.put("saleUserOrConsumes",saleUserOrConsumes);
		return map;
	}

	@Override
	public Map<String, Object> getReportOfUserCount(Integer num, String dateType, String date)throws Exception {
		Map<String, Object> map=new HashMap<>();

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if("month".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}else if("year".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}
		List<Integer> list=new ArrayList<>();
		if(null==num){
			num=6;
		}
		for(int i=1;i< num+1; i++ ){
			list.add(i);
		}

		List<Map<String,Object>> userCounts=tPubVisitRecordMapper.getUserCount(null,dateType,date,list);
		map.put("userCounts",transDate(userCounts,dateType));
		List<Map<String,Object>> saleUsers=tPubVisitRecordMapper.saleUser(dateType,date);
		map.put("saleUsers",saleUsers);
		return map;
	}

	@Override
	public Map<String, Object> getReportOfConsume(Integer num, String dateType, String date) throws Exception {
		Map<String, Object> map=new HashMap<>();

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if("month".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}else if("year".equals(dateType) && StringUtils.isEmpty(date)){
			date=sdf.format(new Date());
		}
		List<Integer> list=new ArrayList<>();
		if(null==num){
			num=6;
		}
		for(int i=1;i< num+1; i++ ){
			list.add(i);
		}

		List<Map<String,Object>> consumeCounts=tPubVisitRecordMapper.getConsumeCount(null,dateType,date,list);
		map.put("consumeCounts",transDate(consumeCounts,dateType));
		List<Map<String,Object>> consumes=tPubVisitRecordMapper.consume(dateType,date);
		map.put("consumes",consumes);
		return map;
	}


	private  List<Map<String,Object>> transDate( List<Map<String,Object>> liveMers,String dateType){
		if(null!= liveMers && liveMers.size()>0){
			liveMers.forEach(node->{
				String clickDate=node.get("clickDate").toString();
				if("month".equals(dateType)){
					clickDate=  clickDate.substring(5,7);
					clickDate+="月";
				}else if("year".equals(dateType)){
					clickDate =clickDate.substring(0,4);
					clickDate+="年";
				}else  if("day".equals(dateType)){
					clickDate=clickDate.substring(8,clickDate.length());
					clickDate+="日";
				}
				node.put("clickDate",clickDate);
			});
		}
		return  liveMers;
	}

}
