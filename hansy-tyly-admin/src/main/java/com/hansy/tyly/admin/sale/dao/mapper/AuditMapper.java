package com.hansy.tyly.admin.sale.dao.mapper;

import java.util.List;
import java.util.Map;

public interface AuditMapper {
	/**
	 * 获取销售待审核列表
	 */
	List<Map<String, Object>> getAudits(Map<String, Object> params);
}
