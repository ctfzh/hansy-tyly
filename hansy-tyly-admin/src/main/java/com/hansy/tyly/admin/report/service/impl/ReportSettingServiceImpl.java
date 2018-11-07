package com.hansy.tyly.admin.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hansy.tyly.admin.dao.mapper.TReportConfMapper;
import com.hansy.tyly.admin.dao.mapper.TReportTableConfDtlMapper;
import com.hansy.tyly.admin.dao.model.TReportConf;
import com.hansy.tyly.admin.dao.model.TReportTableConfDtl;
import com.hansy.tyly.admin.report.service.IReportSettingService;
import com.hansy.tyly.admin.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ReportSettingServiceImpl implements IReportSettingService {
	@Autowired
	private TReportConfMapper reportConfMapper;
	@Autowired
	private TReportTableConfDtlMapper reportTableConfDtlMapper;

	@Override
	public List<TReportConf> getReportInfo(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return reportConfMapper.getReportInfo(params);
	}

	@Override
	public String insertOrUpdateReportInfo(TReportConf tReportConf) {
		if (tReportConf.getReportKey().isEmpty()) {
			// 新增
			String reportKey = RandomUtil.uuid();
			tReportConf.setReportKey(reportKey);
			reportConfMapper.insertSelective(tReportConf);
			return reportKey;

		} else {
			// 修改
			reportConfMapper.updateByPrimaryKeySelective(tReportConf);
			return null;
		}
	}

	@Override
	public TReportConf getReportLIstInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		TReportConf tReportConf = new TReportConf();
		tReportConf.setReportKey(params.get("reportKey").toString());
		return reportConfMapper.selectByPrimaryKey(tReportConf);
	}

	@Override
	public List<TReportTableConfDtl> getReportcolomnInfo(
			Map<String, Object> params) {
		return reportTableConfDtlMapper.selectCloum(params);
	}

	@Override
	public void insertOrUpdateClomn(TReportTableConfDtl tReportTableConfDtl) {
		if (tReportTableConfDtl.getDetailKey().isEmpty()) {
			tReportTableConfDtl.setDetailKey(RandomUtil.uuid());
			reportTableConfDtlMapper.insertSelective(tReportTableConfDtl);
		} else {
			reportTableConfDtlMapper
					.updateByPrimaryKeySelective(tReportTableConfDtl);
		}
	}

	@Override
	public void deleteClomn(Map<String, Object> map) {
		reportTableConfDtlMapper.deleteColmn(map);
	}

	@Override
	public void deleteReport(Map<String, Object> map) {
		reportConfMapper.deleteReport(map);
	}

	@Override
	public List<Map<String, Object>> getDataJson(Map<String, Object> map) {
		/**
		 * 获取json
		 */
		TReportConf tReportConf = new TReportConf();
		tReportConf.setReportKey(map.get("reportKey").toString());
		tReportConf = reportConfMapper.selectByPrimaryKey(tReportConf
				.getReportKey());
		String sql = tReportConf.getSqll();
		List<Map<String, Object>> listinfo = new ArrayList<>();
		if (StringUtils.isBlank(sql)) {
			return null;
		} else {
			// 查询sql 查询条件
			List<TReportTableConfDtl> list = reportTableConfDtlMapper
					.getUseClom(map);
			boolean flag = sql.contains("[VALUE]");
			sql = sql.replace("[VALUE]","");
			StringBuffer str = new StringBuffer(sql);
			if (flag) {
				if (list.isEmpty()) {
					str.append("");
				} else {
					for (TReportTableConfDtl params : list) {
						if (!map.containsKey(params.getColumnCode())) {
							str.append("");
						} else {
							// 获取参数
						
							if(map.get(params.getColumnCode()).toString().isEmpty()){
								str.append("");
							}else{
								String value = map.get(params.getColumnCode())
										.toString();	
								// 获取条件类型condType
								String type = params.getCondType();
								// 获取左边表达式 condExpression
								String condExpression = params.getCondExpression();
								// 获取右边表达式condExpressionRight
								String condExpressionRight = params
										.getCondExpressionRight().replace("[VALUE]",
												value);
								String infoString =" "+ type+" "+condExpression+" "+condExpressionRight;
								str.append(infoString);
							}
						}

					}
				}
				listinfo = reportConfMapper.getDataJson(str);
			} else {
				listinfo = reportConfMapper.getDataJson(str);
			}
			return listinfo;
		}

	}

	@Override
	public List<TReportTableConfDtl> getContitionType(Map<String, Object> map) {
		List<TReportTableConfDtl> list = reportTableConfDtlMapper
				.getUseClom(map);
		return list;
	}

	@Override
	public List<TReportTableConfDtl> getReportcolomncount(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return reportTableConfDtlMapper.selectCloumcount(params);
	}

	@Override
	public Map<String, Object> getReportMapLIst(Map<String, Object> map) {
		Map<String, Object> mapinfo = new HashMap<String, Object>();
		//经销商采购月消费
        //商品消费占比
        //客户数
        //订单统计
		return null;
	}
}
