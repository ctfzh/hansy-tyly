package com.hansy.tyly.admin.dao.model;

import javax.persistence.*;

@Table(name = "t_report_table_conf_dtl")
public class TReportTableConfDtl {
    /**
     * 主键
     */
    @Id
    @Column(name = "DETAIL_KEY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String detailKey;

    /**
     * 报表主键，关联报表配置表
     */
    @Column(name = "REPORT_KEY")
    private String reportKey;

    /**
     * 列名
     */
    @Column(name = "COLUMN_NAME")
    private String columnName;

    /**
     * 列编码
     */
    @Column(name = "COLUMN_CODE")
    private String columnCode;

    /**
     * 列显示名称
     */
    @Column(name = "COLUMN_DISPLAY")
    private String columnDisplay;

    /**
     * 下拉
     */
    @Column(name = "COLUMN_DROPDOWN")
    private String columnDropdown;

    /**
     * 是否显示
     */
    @Column(name = "IS_SHOW")
    private String isShow;

    /**
     * 是否必输
     */
    @Column(name = "IS_NEED")
    private String isNeed;

    /**
     * 是否查询条件
     */
    @Column(name = "IS_QUERYCONDITION")
    private String isQuerycondition;

    /**
     * 条件类型
     */
    @Column(name = "COND_TYPE")
    private String condType;

    /**
     * 条件表达式
     */
    @Column(name = "COND_EXPRESSION")
    private String condExpression;

    /**
     * 条件替换位置
     */
    @Column(name = "COND_WHERE")
    private String condWhere;

    /**
     * 条件是否必输
     */
    @Column(name = "COND_MUST")
    private String condMust;

    /**
     * 等号右边表达式
     */
    @Column(name = "COND_EXPRESSION_RIGHT")
    private String condExpressionRight;

    /**
     * 值类型
     */
    @Column(name = "RECORD_TYPE")
    private String recordType;

    /**
     * 默认值
     */
    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;
    
    /**
     * 默认值
     */
    @Column(name = "SORT")
    private String sort;
    

    public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
     * 获取主键
     *
     * @return DETAIL_KEY - 主键
     */
    public String getDetailKey() {
        return detailKey;
    }

    /**
     * 设置主键
     *
     * @param detailKey 主键
     */
    public void setDetailKey(String detailKey) {
        this.detailKey = detailKey;
    }

    /**
     * 获取报表主键，关联报表配置表
     *
     * @return REPORT_KEY - 报表主键，关联报表配置表
     */
    public String getReportKey() {
        return reportKey;
    }

    /**
     * 设置报表主键，关联报表配置表
     *
     * @param reportKey 报表主键，关联报表配置表
     */
    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    /**
     * 获取列名
     *
     * @return COLUMN_NAME - 列名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置列名
     *
     * @param columnName 列名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 获取列编码
     *
     * @return COLUMN_CODE - 列编码
     */
    public String getColumnCode() {
        return columnCode;
    }

    /**
     * 设置列编码
     *
     * @param columnCode 列编码
     */
    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    /**
     * 获取列显示名称
     *
     * @return COLUMN_DISPLAY - 列显示名称
     */
    public String getColumnDisplay() {
        return columnDisplay;
    }

    /**
     * 设置列显示名称
     *
     * @param columnDisplay 列显示名称
     */
    public void setColumnDisplay(String columnDisplay) {
        this.columnDisplay = columnDisplay;
    }

    /**
     * 获取下拉
     *
     * @return COLUMN_DROPDOWN - 下拉
     */
    public String getColumnDropdown() {
        return columnDropdown;
    }

    /**
     * 设置下拉
     *
     * @param columnDropdown 下拉
     */
    public void setColumnDropdown(String columnDropdown) {
        this.columnDropdown = columnDropdown;
    }

    /**
     * 获取是否显示
     *
     * @return IS_SHOW - 是否显示
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     *
     * @param isShow 是否显示
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取是否必输
     *
     * @return IS_NEED - 是否必输
     */
    public String getIsNeed() {
        return isNeed;
    }

    /**
     * 设置是否必输
     *
     * @param isNeed 是否必输
     */
    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    /**
     * 获取是否查询条件
     *
     * @return IS_QUERYCONDITION - 是否查询条件
     */
    public String getIsQuerycondition() {
        return isQuerycondition;
    }

    /**
     * 设置是否查询条件
     *
     * @param isQuerycondition 是否查询条件
     */
    public void setIsQuerycondition(String isQuerycondition) {
        this.isQuerycondition = isQuerycondition;
    }

    /**
     * 获取条件类型
     *
     * @return COND_TYPE - 条件类型
     */
    public String getCondType() {
        return condType;
    }

    /**
     * 设置条件类型
     *
     * @param condType 条件类型
     */
    public void setCondType(String condType) {
        this.condType = condType;
    }

    /**
     * 获取条件表达式
     *
     * @return COND_EXPRESSION - 条件表达式
     */
    public String getCondExpression() {
        return condExpression;
    }

    /**
     * 设置条件表达式
     *
     * @param condExpression 条件表达式
     */
    public void setCondExpression(String condExpression) {
        this.condExpression = condExpression;
    }

    /**
     * 获取条件替换位置
     *
     * @return COND_WHERE - 条件替换位置
     */
    public String getCondWhere() {
        return condWhere;
    }

    /**
     * 设置条件替换位置
     *
     * @param condWhere 条件替换位置
     */
    public void setCondWhere(String condWhere) {
        this.condWhere = condWhere;
    }

    /**
     * 获取条件是否必输
     *
     * @return COND_MUST - 条件是否必输
     */
    public String getCondMust() {
        return condMust;
    }

    /**
     * 设置条件是否必输
     *
     * @param condMust 条件是否必输
     */
    public void setCondMust(String condMust) {
        this.condMust = condMust;
    }

    /**
     * 获取等号右边表达式
     *
     * @return COND_EXPRESSION_RIGHT - 等号右边表达式
     */
    public String getCondExpressionRight() {
        return condExpressionRight;
    }

    /**
     * 设置等号右边表达式
     *
     * @param condExpressionRight 等号右边表达式
     */
    public void setCondExpressionRight(String condExpressionRight) {
        this.condExpressionRight = condExpressionRight;
    }

    /**
     * 获取值类型
     *
     * @return RECORD_TYPE - 值类型
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置值类型
     *
     * @param recordType 值类型
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    /**
     * 获取默认值
     *
     * @return DEFAULT_VALUE - 默认值
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置默认值
     *
     * @param defaultValue 默认值
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}