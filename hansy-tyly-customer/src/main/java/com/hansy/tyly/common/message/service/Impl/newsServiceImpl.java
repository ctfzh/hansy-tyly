package com.hansy.tyly.common.message.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hansy.tyly.common.orders.dao.model.TUserNewsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.common.message.service.newsService;
import com.hansy.tyly.common.orders.dao.mapper.TUserNewsMapper;
import com.hansy.tyly.common.orders.dao.model.TUserNews;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.core.helper.NPageHelper;

@Transactional
@Service
public class newsServiceImpl implements newsService{
	@Autowired
	private TUserNewsMapper tUserNewsMapper;
	@Autowired
	public SysUserRoleMenuService sysUserRoleMenuService;
	
	/**
	 * 查看消息列表接口
	 */
	public List<TUserNews> getNewsList(Map<String, Object> inMap,String userNo) {
		
		inMap.put("userNo", userNo);
		NPageHelper.startPage(inMap);
		List<TUserNews> list = tUserNewsMapper.selectNews(inMap);
		if(null!=inMap.get("isRead") && Context.READ_TRUE.equals((String)inMap.get("isRead"))){
			list.forEach(tUserNews -> {
				tUserNews.setIsRead(Context.READ_TRUE);
				tUserNewsMapper.updateByPrimaryKeySelective(tUserNews);
			});
		}
		return list;
	}
	
	public List<Map<String,Object>> getNewsList(Map<String, Object> inMap) {
		NPageHelper.startPage(inMap);
		List<TUserNews> list = tUserNewsMapper.selectNews(inMap);
		List<Map<String,Object>> mapList=new ArrayList<>();
		list.forEach(node->{
				Map<String,Object> map=new HashMap<>();
				map.put("userNo",node.getUserNo());
				map.put("tableKey", node.getTableKey());
				map.put("userType",node.getUserType());
				map.put("newsType",node.getNewsType());
				map.put("newsContent", node.getNewsContent());
				map.put("newsDate", node.getNewsDate());
				map.put("newsTitle", node.getNewsTitle());
				map.put("orderNo", node.getOrderNo());
				mapList.add(map);

		});
		
		return mapList;
	}
	
		/**
		 * 查看消息详情接口
		 */
		public Map<String,Object> getNewsDetail(String tableKey) {
			Map<String,Object> inMap=new HashMap();
			inMap.put("tableKey", tableKey);
			List<Map<String,Object>> list=getNewsList(inMap);
			TUserNews userNews=tUserNewsMapper.selectByPrimaryKey(tableKey);
			userNews.setIsRead(Context.READ_TRUE);
			tUserNewsMapper.updateByPrimaryKeySelective(userNews);
			return list.get(0);
		}

	@Override
	public Integer isNotReadNews(String userNo) throws Exception {
		TUserNewsExample example=new TUserNewsExample();
		TUserNewsExample.Criteria criteria=example.createCriteria();
		criteria.andUserNoEqualTo(userNo);
		criteria.andIsReadEqualTo(Context.READ_FALSE);

		return tUserNewsMapper.selectCountByExample(example);
	}

}
