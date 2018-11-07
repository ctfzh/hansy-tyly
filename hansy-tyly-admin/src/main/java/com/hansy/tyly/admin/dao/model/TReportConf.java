package com.hansy.tyly.admin.dao.model;

import javax.persistence.*;

@Table(name = "t_report_conf")
public class TReportConf {
    /**
     * 报表主键
     */
    @Id
    @Column(name = "report_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reportKey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图形类型
     */
    @Column(name = "chart_type")
    private String chartType;

    /**
     * 报表名
     */
    @Column(name = "report_title")
    private String reportTitle;

    /**
     * 图形宽度
     */
    private String width;

    /**
     * 图形高度
     */
    private String height;

    /**
     * 报表使用查询条件
     */
    private String sql2;

    /**
     * 对比图形分类sql
     */
    @Column(name = "conf_class_sql")
    private String confClassSql;

    /**
     * 报表菜单名称
     */
    @Column(name = "report_menu_name")
    private String reportMenuName;

    /**
     * 报表是否启用
     */
    @Column(name = "is_enable")
    private String isEnable;

    /**
     * 多报表时排列顺序
     */
    private String sequence;

    /**
     * 图形是否显示零数据
     */
    @Column(name = "show_zero_data")
    private String showZeroData;

    /**
     * 图形x轴名
     */
    @Column(name = "x_axis_name")
    private String xAxisName;

    /**
     * 图形y轴名
     */
    @Column(name = "y_axis_name")
    private String yAxisName;

    /**
     * 图形副标题
     */
    private String subcaption;

    /**
     * 图形数字保留小数位
     */
    private String decimals;

    /**
     * 是否格式化图形上数字
     */
    @Column(name = "format_number_scale")
    private String formatNumberScale;

    /**
     * 格式化图形值如1000,10000,100000000
     */
    @Column(name = "number_scale_value")
    private String numberScaleValue;

    /**
     * 格式化图形单位如千,万,亿
     */
    @Column(name = "number_scale_unit")
    private String numberScaleUnit;

    /**
     * 图形数据前缀
     */
    @Column(name = "number_prefix")
    private String numberPrefix;

    /**
     * 图形数据后缀
     */
    @Column(name = "number_suffix")
    private String numberSuffix;

    /**
     * 图形字体大小
     */
    @Column(name = "base_font_size")
    private String baseFontSize;

    /**
     * 图形是否显示数值
     */
    @Column(name = "show_values")
    private String showValues;

    /**
     * 图形在show_Values=1时是否显示为百分比
     */
    @Column(name = "show_percent_values")
    private String showPercentValues;

    /**
     * 图形提示是否显示为百分比
     */
    @Column(name = "show_percent_in_tool_tip")
    private String showPercentInToolTip;

    /**
     * 图形是否显示y轴数字
     */
    @Column(name = "show_y_axis_values")
    private String showYAxisValues;

    /**
     * 图形数据小数是否补零
     */
    @Column(name = "force_decimals")
    private String forceDecimals;

    /**
     * y轴值保留几位小数
     */
    @Column(name = "y_axis_value_decimals")
    private String yAxisValueDecimals;

    /**
     * 是否分页
     */
    @Column(name = "is_page")
    private String isPage;

    /**
     * 分页大小
     */
    @Column(name = "page_size")
    private Double pageSize;

    /**
     * 是否下载excel
     */
    @Column(name = "is_down_load")
    private String isDownLoad;

    /**
     * 图形x轴旋转角度
     */
    @Column(name = "x_axis_turn")
    private String xAxisTurn;

    /**
     * 图形是否显示X轴数字
     */
    @Column(name = "show_x_axis_values")
    private String showXAxisValues;

    /**
     * sql
     */
    @Column(name = "sqll")
    private String sqll;
    
    @Column(name = "url")
    private String url;
    
    /**
     * 是否跳转
     */
    @Column(name = "is_jump")
    private String isJump;
    
    /**
     * 跳转URL
     */
    @Column(name = "jump_url")
    private String jumpUrl;
    public String getIsJump() {
		return isJump;
	}

	public void setIsJump(String isJump) {
		this.isJump = isJump;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	/**
     * 获取报表主键
     *
     * @return report_key - 报表主键
     */
    public String getReportKey() {
        return reportKey;
    }

    /**
     * 设置报表主键
     *
     * @param reportKey 报表主键
     */
    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取图形类型
     *
     * @return chart_type - 图形类型
     */
    public String getChartType() {
        return chartType;
    }

    /**
     * 设置图形类型
     *
     * @param chartType 图形类型
     */
    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    /**
     * 获取报表名
     *
     * @return report_title - 报表名
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * 设置报表名
     *
     * @param reportTitle 报表名
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * 获取图形宽度
     *
     * @return width - 图形宽度
     */
    public String getWidth() {
        return width;
    }

    /**
     * 设置图形宽度
     *
     * @param width 图形宽度
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * 获取图形高度
     *
     * @return height - 图形高度
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置图形高度
     *
     * @param height 图形高度
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * 获取报表使用查询条件
     *
     * @return sql2 - 报表使用查询条件
     */
    public String getSql2() {
        return sql2;
    }

    public String getSqll() {
		return sqll;
	}

	public void setSqll(String sqll) {
		this.sqll = sqll;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * 设置报表使用查询条件
     *
     * @param sql2 报表使用查询条件
     */
    public void setSql2(String sql2) {
        this.sql2 = sql2;
    }

    /**
     * 获取对比图形分类sql
     *
     * @return conf_class_sql - 对比图形分类sql
     */
    public String getConfClassSql() {
        return confClassSql;
    }

    /**
     * 设置对比图形分类sql
     *
     * @param confClassSql 对比图形分类sql
     */
    public void setConfClassSql(String confClassSql) {
        this.confClassSql = confClassSql;
    }

    /**
     * 获取报表菜单名称
     *
     * @return report_menu_name - 报表菜单名称
     */
    public String getReportMenuName() {
        return reportMenuName;
    }

    /**
     * 设置报表菜单名称
     *
     * @param reportMenuName 报表菜单名称
     */
    public void setReportMenuName(String reportMenuName) {
        this.reportMenuName = reportMenuName;
    }

    /**
     * 获取报表是否启用
     *
     * @return is_enable - 报表是否启用
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * 设置报表是否启用
     *
     * @param isEnable 报表是否启用
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取多报表时排列顺序
     *
     * @return sequence - 多报表时排列顺序
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * 设置多报表时排列顺序
     *
     * @param sequence 多报表时排列顺序
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取图形是否显示零数据
     *
     * @return show_zero_data - 图形是否显示零数据
     */
    public String getShowZeroData() {
        return showZeroData;
    }

    /**
     * 设置图形是否显示零数据
     *
     * @param showZeroData 图形是否显示零数据
     */
    public void setShowZeroData(String showZeroData) {
        this.showZeroData = showZeroData;
    }

    /**
     * 获取图形x轴名
     *
     * @return x_axis_name - 图形x轴名
     */
    public String getxAxisName() {
        return xAxisName;
    }

    /**
     * 设置图形x轴名
     *
     * @param xAxisName 图形x轴名
     */
    public void setxAxisName(String xAxisName) {
        this.xAxisName = xAxisName;
    }

    /**
     * 获取图形y轴名
     *
     * @return y_axis_name - 图形y轴名
     */
    public String getyAxisName() {
        return yAxisName;
    }

    /**
     * 设置图形y轴名
     *
     * @param yAxisName 图形y轴名
     */
    public void setyAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
    }

    /**
     * 获取图形副标题
     *
     * @return subcaption - 图形副标题
     */
    public String getSubcaption() {
        return subcaption;
    }

    /**
     * 设置图形副标题
     *
     * @param subcaption 图形副标题
     */
    public void setSubcaption(String subcaption) {
        this.subcaption = subcaption;
    }

    /**
     * 获取图形数字保留小数位
     *
     * @return decimals - 图形数字保留小数位
     */
    public String getDecimals() {
        return decimals;
    }

    /**
     * 设置图形数字保留小数位
     *
     * @param decimals 图形数字保留小数位
     */
    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    /**
     * 获取是否格式化图形上数字
     *
     * @return format_number_scale - 是否格式化图形上数字
     */
    public String getFormatNumberScale() {
        return formatNumberScale;
    }

    /**
     * 设置是否格式化图形上数字
     *
     * @param formatNumberScale 是否格式化图形上数字
     */
    public void setFormatNumberScale(String formatNumberScale) {
        this.formatNumberScale = formatNumberScale;
    }

    /**
     * 获取格式化图形值如1000,10000,100000000
     *
     * @return number_scale_value - 格式化图形值如1000,10000,100000000
     */
    public String getNumberScaleValue() {
        return numberScaleValue;
    }

    /**
     * 设置格式化图形值如1000,10000,100000000
     *
     * @param numberScaleValue 格式化图形值如1000,10000,100000000
     */
    public void setNumberScaleValue(String numberScaleValue) {
        this.numberScaleValue = numberScaleValue;
    }

    /**
     * 获取格式化图形单位如千,万,亿
     *
     * @return number_scale_unit - 格式化图形单位如千,万,亿
     */
    public String getNumberScaleUnit() {
        return numberScaleUnit;
    }

    /**
     * 设置格式化图形单位如千,万,亿
     *
     * @param numberScaleUnit 格式化图形单位如千,万,亿
     */
    public void setNumberScaleUnit(String numberScaleUnit) {
        this.numberScaleUnit = numberScaleUnit;
    }

    /**
     * 获取图形数据前缀
     *
     * @return number_prefix - 图形数据前缀
     */
    public String getNumberPrefix() {
        return numberPrefix;
    }

    /**
     * 设置图形数据前缀
     *
     * @param numberPrefix 图形数据前缀
     */
    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
    }

    /**
     * 获取图形数据后缀
     *
     * @return number_suffix - 图形数据后缀
     */
    public String getNumberSuffix() {
        return numberSuffix;
    }

    /**
     * 设置图形数据后缀
     *
     * @param numberSuffix 图形数据后缀
     */
    public void setNumberSuffix(String numberSuffix) {
        this.numberSuffix = numberSuffix;
    }

    /**
     * 获取图形字体大小
     *
     * @return base_font_size - 图形字体大小
     */
    public String getBaseFontSize() {
        return baseFontSize;
    }

    /**
     * 设置图形字体大小
     *
     * @param baseFontSize 图形字体大小
     */
    public void setBaseFontSize(String baseFontSize) {
        this.baseFontSize = baseFontSize;
    }

    /**
     * 获取图形是否显示数值
     *
     * @return show_values - 图形是否显示数值
     */
    public String getShowValues() {
        return showValues;
    }

    /**
     * 设置图形是否显示数值
     *
     * @param showValues 图形是否显示数值
     */
    public void setShowValues(String showValues) {
        this.showValues = showValues;
    }

    /**
     * 获取图形在show_Values=1时是否显示为百分比
     *
     * @return show_percent_values - 图形在show_Values=1时是否显示为百分比
     */
    public String getShowPercentValues() {
        return showPercentValues;
    }

    /**
     * 设置图形在show_Values=1时是否显示为百分比
     *
     * @param showPercentValues 图形在show_Values=1时是否显示为百分比
     */
    public void setShowPercentValues(String showPercentValues) {
        this.showPercentValues = showPercentValues;
    }

    /**
     * 获取图形提示是否显示为百分比
     *
     * @return show_percent_in_tool_tip - 图形提示是否显示为百分比
     */
    public String getShowPercentInToolTip() {
        return showPercentInToolTip;
    }

    /**
     * 设置图形提示是否显示为百分比
     *
     * @param showPercentInToolTip 图形提示是否显示为百分比
     */
    public void setShowPercentInToolTip(String showPercentInToolTip) {
        this.showPercentInToolTip = showPercentInToolTip;
    }

    /**
     * 获取图形是否显示y轴数字
     *
     * @return show_y_axis_values - 图形是否显示y轴数字
     */
    public String getShowYAxisValues() {
        return showYAxisValues;
    }

    /**
     * 设置图形是否显示y轴数字
     *
     * @param showYAxisValues 图形是否显示y轴数字
     */
    public void setShowYAxisValues(String showYAxisValues) {
        this.showYAxisValues = showYAxisValues;
    }

    /**
     * 获取图形数据小数是否补零
     *
     * @return force_decimals - 图形数据小数是否补零
     */
    public String getForceDecimals() {
        return forceDecimals;
    }

    /**
     * 设置图形数据小数是否补零
     *
     * @param forceDecimals 图形数据小数是否补零
     */
    public void setForceDecimals(String forceDecimals) {
        this.forceDecimals = forceDecimals;
    }

    /**
     * 获取y轴值保留几位小数
     *
     * @return y_axis_value_decimals - y轴值保留几位小数
     */
    public String getyAxisValueDecimals() {
        return yAxisValueDecimals;
    }

    /**
     * 设置y轴值保留几位小数
     *
     * @param yAxisValueDecimals y轴值保留几位小数
     */
    public void setyAxisValueDecimals(String yAxisValueDecimals) {
        this.yAxisValueDecimals = yAxisValueDecimals;
    }

    /**
     * 获取是否分页
     *
     * @return is_page - 是否分页
     */
    public String getIsPage() {
        return isPage;
    }

    /**
     * 设置是否分页
     *
     * @param isPage 是否分页
     */
    public void setIsPage(String isPage) {
        this.isPage = isPage;
    }

    /**
     * 获取分页大小
     *
     * @return page_size - 分页大小
     */
    public Double getPageSize() {
        return pageSize;
    }

    /**
     * 设置分页大小
     *
     * @param pageSize 分页大小
     */
    public void setPageSize(Double pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取是否下载excel
     *
     * @return is_down_load - 是否下载excel
     */
    public String getIsDownLoad() {
        return isDownLoad;
    }

    /**
     * 设置是否下载excel
     *
     * @param isDownLoad 是否下载excel
     */
    public void setIsDownLoad(String isDownLoad) {
        this.isDownLoad = isDownLoad;
    }

    /**
     * 获取图形x轴旋转角度
     *
     * @return x_axis_turn - 图形x轴旋转角度
     */
    public String getxAxisTurn() {
        return xAxisTurn;
    }

    /**
     * 设置图形x轴旋转角度
     *
     * @param xAxisTurn 图形x轴旋转角度
     */
    public void setxAxisTurn(String xAxisTurn) {
        this.xAxisTurn = xAxisTurn;
    }

    /**
     * 获取图形是否显示X轴数字
     *
     * @return show_x_axis_values - 图形是否显示X轴数字
     */
    public String getShowXAxisValues() {
        return showXAxisValues;
    }

    /**
     * 设置图形是否显示X轴数字
     *
     * @param showXAxisValues 图形是否显示X轴数字
     */
    public void setShowXAxisValues(String showXAxisValues) {
        this.showXAxisValues = showXAxisValues;
    }
 
}