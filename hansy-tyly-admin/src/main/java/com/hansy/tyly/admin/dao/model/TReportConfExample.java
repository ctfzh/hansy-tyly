package com.hansy.tyly.admin.dao.model;

import java.util.ArrayList;
import java.util.List;

public class TReportConfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TReportConfExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andReportKeyIsNull() {
            addCriterion("report_key is null");
            return (Criteria) this;
        }

        public Criteria andReportKeyIsNotNull() {
            addCriterion("report_key is not null");
            return (Criteria) this;
        }

        public Criteria andReportKeyEqualTo(String value) {
            addCriterion("report_key =", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotEqualTo(String value) {
            addCriterion("report_key <>", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyGreaterThan(String value) {
            addCriterion("report_key >", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyGreaterThanOrEqualTo(String value) {
            addCriterion("report_key >=", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLessThan(String value) {
            addCriterion("report_key <", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLessThanOrEqualTo(String value) {
            addCriterion("report_key <=", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLike(String value) {
            addCriterion("report_key like", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotLike(String value) {
            addCriterion("report_key not like", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyIn(List<String> values) {
            addCriterion("report_key in", values, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotIn(List<String> values) {
            addCriterion("report_key not in", values, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyBetween(String value1, String value2) {
            addCriterion("report_key between", value1, value2, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotBetween(String value1, String value2) {
            addCriterion("report_key not between", value1, value2, "reportKey");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andChartTypeIsNull() {
            addCriterion("chart_type is null");
            return (Criteria) this;
        }

        public Criteria andChartTypeIsNotNull() {
            addCriterion("chart_type is not null");
            return (Criteria) this;
        }

        public Criteria andChartTypeEqualTo(String value) {
            addCriterion("chart_type =", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeNotEqualTo(String value) {
            addCriterion("chart_type <>", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeGreaterThan(String value) {
            addCriterion("chart_type >", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeGreaterThanOrEqualTo(String value) {
            addCriterion("chart_type >=", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeLessThan(String value) {
            addCriterion("chart_type <", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeLessThanOrEqualTo(String value) {
            addCriterion("chart_type <=", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeLike(String value) {
            addCriterion("chart_type like", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeNotLike(String value) {
            addCriterion("chart_type not like", value, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeIn(List<String> values) {
            addCriterion("chart_type in", values, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeNotIn(List<String> values) {
            addCriterion("chart_type not in", values, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeBetween(String value1, String value2) {
            addCriterion("chart_type between", value1, value2, "chartType");
            return (Criteria) this;
        }

        public Criteria andChartTypeNotBetween(String value1, String value2) {
            addCriterion("chart_type not between", value1, value2, "chartType");
            return (Criteria) this;
        }

        public Criteria andReportTitleIsNull() {
            addCriterion("report_title is null");
            return (Criteria) this;
        }

        public Criteria andReportTitleIsNotNull() {
            addCriterion("report_title is not null");
            return (Criteria) this;
        }

        public Criteria andReportTitleEqualTo(String value) {
            addCriterion("report_title =", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleNotEqualTo(String value) {
            addCriterion("report_title <>", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleGreaterThan(String value) {
            addCriterion("report_title >", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleGreaterThanOrEqualTo(String value) {
            addCriterion("report_title >=", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleLessThan(String value) {
            addCriterion("report_title <", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleLessThanOrEqualTo(String value) {
            addCriterion("report_title <=", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleLike(String value) {
            addCriterion("report_title like", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleNotLike(String value) {
            addCriterion("report_title not like", value, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleIn(List<String> values) {
            addCriterion("report_title in", values, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleNotIn(List<String> values) {
            addCriterion("report_title not in", values, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleBetween(String value1, String value2) {
            addCriterion("report_title between", value1, value2, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andReportTitleNotBetween(String value1, String value2) {
            addCriterion("report_title not between", value1, value2, "reportTitle");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(String value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(String value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(String value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(String value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(String value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(String value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(String value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLike(String value) {
            addCriterion("height like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotLike(String value) {
            addCriterion("height not like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<String> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<String> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(String value1, String value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(String value1, String value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andSql2IsNull() {
            addCriterion("sql2 is null");
            return (Criteria) this;
        }

        public Criteria andSql2IsNotNull() {
            addCriterion("sql2 is not null");
            return (Criteria) this;
        }

        public Criteria andSql2EqualTo(String value) {
            addCriterion("sql2 =", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2NotEqualTo(String value) {
            addCriterion("sql2 <>", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2GreaterThan(String value) {
            addCriterion("sql2 >", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2GreaterThanOrEqualTo(String value) {
            addCriterion("sql2 >=", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2LessThan(String value) {
            addCriterion("sql2 <", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2LessThanOrEqualTo(String value) {
            addCriterion("sql2 <=", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2Like(String value) {
            addCriterion("sql2 like", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2NotLike(String value) {
            addCriterion("sql2 not like", value, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2In(List<String> values) {
            addCriterion("sql2 in", values, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2NotIn(List<String> values) {
            addCriterion("sql2 not in", values, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2Between(String value1, String value2) {
            addCriterion("sql2 between", value1, value2, "sql2");
            return (Criteria) this;
        }

        public Criteria andSql2NotBetween(String value1, String value2) {
            addCriterion("sql2 not between", value1, value2, "sql2");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlIsNull() {
            addCriterion("conf_class_sql is null");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlIsNotNull() {
            addCriterion("conf_class_sql is not null");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlEqualTo(String value) {
            addCriterion("conf_class_sql =", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlNotEqualTo(String value) {
            addCriterion("conf_class_sql <>", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlGreaterThan(String value) {
            addCriterion("conf_class_sql >", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlGreaterThanOrEqualTo(String value) {
            addCriterion("conf_class_sql >=", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlLessThan(String value) {
            addCriterion("conf_class_sql <", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlLessThanOrEqualTo(String value) {
            addCriterion("conf_class_sql <=", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlLike(String value) {
            addCriterion("conf_class_sql like", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlNotLike(String value) {
            addCriterion("conf_class_sql not like", value, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlIn(List<String> values) {
            addCriterion("conf_class_sql in", values, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlNotIn(List<String> values) {
            addCriterion("conf_class_sql not in", values, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlBetween(String value1, String value2) {
            addCriterion("conf_class_sql between", value1, value2, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andConfClassSqlNotBetween(String value1, String value2) {
            addCriterion("conf_class_sql not between", value1, value2, "confClassSql");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameIsNull() {
            addCriterion("report_menu_name is null");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameIsNotNull() {
            addCriterion("report_menu_name is not null");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameEqualTo(String value) {
            addCriterion("report_menu_name =", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameNotEqualTo(String value) {
            addCriterion("report_menu_name <>", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameGreaterThan(String value) {
            addCriterion("report_menu_name >", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("report_menu_name >=", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameLessThan(String value) {
            addCriterion("report_menu_name <", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameLessThanOrEqualTo(String value) {
            addCriterion("report_menu_name <=", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameLike(String value) {
            addCriterion("report_menu_name like", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameNotLike(String value) {
            addCriterion("report_menu_name not like", value, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameIn(List<String> values) {
            addCriterion("report_menu_name in", values, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameNotIn(List<String> values) {
            addCriterion("report_menu_name not in", values, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameBetween(String value1, String value2) {
            addCriterion("report_menu_name between", value1, value2, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andReportMenuNameNotBetween(String value1, String value2) {
            addCriterion("report_menu_name not between", value1, value2, "reportMenuName");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("is_enable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("is_enable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(String value) {
            addCriterion("is_enable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(String value) {
            addCriterion("is_enable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(String value) {
            addCriterion("is_enable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(String value) {
            addCriterion("is_enable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(String value) {
            addCriterion("is_enable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(String value) {
            addCriterion("is_enable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLike(String value) {
            addCriterion("is_enable like", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotLike(String value) {
            addCriterion("is_enable not like", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<String> values) {
            addCriterion("is_enable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<String> values) {
            addCriterion("is_enable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(String value1, String value2) {
            addCriterion("is_enable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(String value1, String value2) {
            addCriterion("is_enable not between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNull() {
            addCriterion("`sequence` is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNotNull() {
            addCriterion("`sequence` is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceEqualTo(String value) {
            addCriterion("`sequence` =", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotEqualTo(String value) {
            addCriterion("`sequence` <>", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThan(String value) {
            addCriterion("`sequence` >", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThanOrEqualTo(String value) {
            addCriterion("`sequence` >=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThan(String value) {
            addCriterion("`sequence` <", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThanOrEqualTo(String value) {
            addCriterion("`sequence` <=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLike(String value) {
            addCriterion("`sequence` like", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotLike(String value) {
            addCriterion("`sequence` not like", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceIn(List<String> values) {
            addCriterion("`sequence` in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotIn(List<String> values) {
            addCriterion("`sequence` not in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceBetween(String value1, String value2) {
            addCriterion("`sequence` between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotBetween(String value1, String value2) {
            addCriterion("`sequence` not between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataIsNull() {
            addCriterion("show_zero_data is null");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataIsNotNull() {
            addCriterion("show_zero_data is not null");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataEqualTo(String value) {
            addCriterion("show_zero_data =", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataNotEqualTo(String value) {
            addCriterion("show_zero_data <>", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataGreaterThan(String value) {
            addCriterion("show_zero_data >", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataGreaterThanOrEqualTo(String value) {
            addCriterion("show_zero_data >=", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataLessThan(String value) {
            addCriterion("show_zero_data <", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataLessThanOrEqualTo(String value) {
            addCriterion("show_zero_data <=", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataLike(String value) {
            addCriterion("show_zero_data like", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataNotLike(String value) {
            addCriterion("show_zero_data not like", value, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataIn(List<String> values) {
            addCriterion("show_zero_data in", values, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataNotIn(List<String> values) {
            addCriterion("show_zero_data not in", values, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataBetween(String value1, String value2) {
            addCriterion("show_zero_data between", value1, value2, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andShowZeroDataNotBetween(String value1, String value2) {
            addCriterion("show_zero_data not between", value1, value2, "showZeroData");
            return (Criteria) this;
        }

        public Criteria andXAxisNameIsNull() {
            addCriterion("x_axis_name is null");
            return (Criteria) this;
        }

        public Criteria andXAxisNameIsNotNull() {
            addCriterion("x_axis_name is not null");
            return (Criteria) this;
        }

        public Criteria andXAxisNameEqualTo(String value) {
            addCriterion("x_axis_name =", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameNotEqualTo(String value) {
            addCriterion("x_axis_name <>", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameGreaterThan(String value) {
            addCriterion("x_axis_name >", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameGreaterThanOrEqualTo(String value) {
            addCriterion("x_axis_name >=", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameLessThan(String value) {
            addCriterion("x_axis_name <", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameLessThanOrEqualTo(String value) {
            addCriterion("x_axis_name <=", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameLike(String value) {
            addCriterion("x_axis_name like", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameNotLike(String value) {
            addCriterion("x_axis_name not like", value, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameIn(List<String> values) {
            addCriterion("x_axis_name in", values, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameNotIn(List<String> values) {
            addCriterion("x_axis_name not in", values, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameBetween(String value1, String value2) {
            addCriterion("x_axis_name between", value1, value2, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andXAxisNameNotBetween(String value1, String value2) {
            addCriterion("x_axis_name not between", value1, value2, "xAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameIsNull() {
            addCriterion("y_axis_name is null");
            return (Criteria) this;
        }

        public Criteria andYAxisNameIsNotNull() {
            addCriterion("y_axis_name is not null");
            return (Criteria) this;
        }

        public Criteria andYAxisNameEqualTo(String value) {
            addCriterion("y_axis_name =", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameNotEqualTo(String value) {
            addCriterion("y_axis_name <>", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameGreaterThan(String value) {
            addCriterion("y_axis_name >", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameGreaterThanOrEqualTo(String value) {
            addCriterion("y_axis_name >=", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameLessThan(String value) {
            addCriterion("y_axis_name <", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameLessThanOrEqualTo(String value) {
            addCriterion("y_axis_name <=", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameLike(String value) {
            addCriterion("y_axis_name like", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameNotLike(String value) {
            addCriterion("y_axis_name not like", value, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameIn(List<String> values) {
            addCriterion("y_axis_name in", values, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameNotIn(List<String> values) {
            addCriterion("y_axis_name not in", values, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameBetween(String value1, String value2) {
            addCriterion("y_axis_name between", value1, value2, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andYAxisNameNotBetween(String value1, String value2) {
            addCriterion("y_axis_name not between", value1, value2, "yAxisName");
            return (Criteria) this;
        }

        public Criteria andSubcaptionIsNull() {
            addCriterion("subcaption is null");
            return (Criteria) this;
        }

        public Criteria andSubcaptionIsNotNull() {
            addCriterion("subcaption is not null");
            return (Criteria) this;
        }

        public Criteria andSubcaptionEqualTo(String value) {
            addCriterion("subcaption =", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionNotEqualTo(String value) {
            addCriterion("subcaption <>", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionGreaterThan(String value) {
            addCriterion("subcaption >", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionGreaterThanOrEqualTo(String value) {
            addCriterion("subcaption >=", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionLessThan(String value) {
            addCriterion("subcaption <", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionLessThanOrEqualTo(String value) {
            addCriterion("subcaption <=", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionLike(String value) {
            addCriterion("subcaption like", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionNotLike(String value) {
            addCriterion("subcaption not like", value, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionIn(List<String> values) {
            addCriterion("subcaption in", values, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionNotIn(List<String> values) {
            addCriterion("subcaption not in", values, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionBetween(String value1, String value2) {
            addCriterion("subcaption between", value1, value2, "subcaption");
            return (Criteria) this;
        }

        public Criteria andSubcaptionNotBetween(String value1, String value2) {
            addCriterion("subcaption not between", value1, value2, "subcaption");
            return (Criteria) this;
        }

        public Criteria andDecimalsIsNull() {
            addCriterion("decimals is null");
            return (Criteria) this;
        }

        public Criteria andDecimalsIsNotNull() {
            addCriterion("decimals is not null");
            return (Criteria) this;
        }

        public Criteria andDecimalsEqualTo(String value) {
            addCriterion("decimals =", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsNotEqualTo(String value) {
            addCriterion("decimals <>", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsGreaterThan(String value) {
            addCriterion("decimals >", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsGreaterThanOrEqualTo(String value) {
            addCriterion("decimals >=", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsLessThan(String value) {
            addCriterion("decimals <", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsLessThanOrEqualTo(String value) {
            addCriterion("decimals <=", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsLike(String value) {
            addCriterion("decimals like", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsNotLike(String value) {
            addCriterion("decimals not like", value, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsIn(List<String> values) {
            addCriterion("decimals in", values, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsNotIn(List<String> values) {
            addCriterion("decimals not in", values, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsBetween(String value1, String value2) {
            addCriterion("decimals between", value1, value2, "decimals");
            return (Criteria) this;
        }

        public Criteria andDecimalsNotBetween(String value1, String value2) {
            addCriterion("decimals not between", value1, value2, "decimals");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleIsNull() {
            addCriterion("format_number_scale is null");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleIsNotNull() {
            addCriterion("format_number_scale is not null");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleEqualTo(String value) {
            addCriterion("format_number_scale =", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleNotEqualTo(String value) {
            addCriterion("format_number_scale <>", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleGreaterThan(String value) {
            addCriterion("format_number_scale >", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleGreaterThanOrEqualTo(String value) {
            addCriterion("format_number_scale >=", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleLessThan(String value) {
            addCriterion("format_number_scale <", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleLessThanOrEqualTo(String value) {
            addCriterion("format_number_scale <=", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleLike(String value) {
            addCriterion("format_number_scale like", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleNotLike(String value) {
            addCriterion("format_number_scale not like", value, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleIn(List<String> values) {
            addCriterion("format_number_scale in", values, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleNotIn(List<String> values) {
            addCriterion("format_number_scale not in", values, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleBetween(String value1, String value2) {
            addCriterion("format_number_scale between", value1, value2, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andFormatNumberScaleNotBetween(String value1, String value2) {
            addCriterion("format_number_scale not between", value1, value2, "formatNumberScale");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueIsNull() {
            addCriterion("number_scale_value is null");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueIsNotNull() {
            addCriterion("number_scale_value is not null");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueEqualTo(String value) {
            addCriterion("number_scale_value =", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueNotEqualTo(String value) {
            addCriterion("number_scale_value <>", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueGreaterThan(String value) {
            addCriterion("number_scale_value >", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueGreaterThanOrEqualTo(String value) {
            addCriterion("number_scale_value >=", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueLessThan(String value) {
            addCriterion("number_scale_value <", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueLessThanOrEqualTo(String value) {
            addCriterion("number_scale_value <=", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueLike(String value) {
            addCriterion("number_scale_value like", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueNotLike(String value) {
            addCriterion("number_scale_value not like", value, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueIn(List<String> values) {
            addCriterion("number_scale_value in", values, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueNotIn(List<String> values) {
            addCriterion("number_scale_value not in", values, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueBetween(String value1, String value2) {
            addCriterion("number_scale_value between", value1, value2, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleValueNotBetween(String value1, String value2) {
            addCriterion("number_scale_value not between", value1, value2, "numberScaleValue");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitIsNull() {
            addCriterion("number_scale_unit is null");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitIsNotNull() {
            addCriterion("number_scale_unit is not null");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitEqualTo(String value) {
            addCriterion("number_scale_unit =", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitNotEqualTo(String value) {
            addCriterion("number_scale_unit <>", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitGreaterThan(String value) {
            addCriterion("number_scale_unit >", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitGreaterThanOrEqualTo(String value) {
            addCriterion("number_scale_unit >=", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitLessThan(String value) {
            addCriterion("number_scale_unit <", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitLessThanOrEqualTo(String value) {
            addCriterion("number_scale_unit <=", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitLike(String value) {
            addCriterion("number_scale_unit like", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitNotLike(String value) {
            addCriterion("number_scale_unit not like", value, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitIn(List<String> values) {
            addCriterion("number_scale_unit in", values, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitNotIn(List<String> values) {
            addCriterion("number_scale_unit not in", values, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitBetween(String value1, String value2) {
            addCriterion("number_scale_unit between", value1, value2, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberScaleUnitNotBetween(String value1, String value2) {
            addCriterion("number_scale_unit not between", value1, value2, "numberScaleUnit");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixIsNull() {
            addCriterion("number_prefix is null");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixIsNotNull() {
            addCriterion("number_prefix is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixEqualTo(String value) {
            addCriterion("number_prefix =", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixNotEqualTo(String value) {
            addCriterion("number_prefix <>", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixGreaterThan(String value) {
            addCriterion("number_prefix >", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("number_prefix >=", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixLessThan(String value) {
            addCriterion("number_prefix <", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixLessThanOrEqualTo(String value) {
            addCriterion("number_prefix <=", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixLike(String value) {
            addCriterion("number_prefix like", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixNotLike(String value) {
            addCriterion("number_prefix not like", value, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixIn(List<String> values) {
            addCriterion("number_prefix in", values, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixNotIn(List<String> values) {
            addCriterion("number_prefix not in", values, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixBetween(String value1, String value2) {
            addCriterion("number_prefix between", value1, value2, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberPrefixNotBetween(String value1, String value2) {
            addCriterion("number_prefix not between", value1, value2, "numberPrefix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixIsNull() {
            addCriterion("number_suffix is null");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixIsNotNull() {
            addCriterion("number_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixEqualTo(String value) {
            addCriterion("number_suffix =", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixNotEqualTo(String value) {
            addCriterion("number_suffix <>", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixGreaterThan(String value) {
            addCriterion("number_suffix >", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("number_suffix >=", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixLessThan(String value) {
            addCriterion("number_suffix <", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixLessThanOrEqualTo(String value) {
            addCriterion("number_suffix <=", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixLike(String value) {
            addCriterion("number_suffix like", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixNotLike(String value) {
            addCriterion("number_suffix not like", value, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixIn(List<String> values) {
            addCriterion("number_suffix in", values, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixNotIn(List<String> values) {
            addCriterion("number_suffix not in", values, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixBetween(String value1, String value2) {
            addCriterion("number_suffix between", value1, value2, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andNumberSuffixNotBetween(String value1, String value2) {
            addCriterion("number_suffix not between", value1, value2, "numberSuffix");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeIsNull() {
            addCriterion("base_font_size is null");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeIsNotNull() {
            addCriterion("base_font_size is not null");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeEqualTo(String value) {
            addCriterion("base_font_size =", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeNotEqualTo(String value) {
            addCriterion("base_font_size <>", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeGreaterThan(String value) {
            addCriterion("base_font_size >", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeGreaterThanOrEqualTo(String value) {
            addCriterion("base_font_size >=", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeLessThan(String value) {
            addCriterion("base_font_size <", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeLessThanOrEqualTo(String value) {
            addCriterion("base_font_size <=", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeLike(String value) {
            addCriterion("base_font_size like", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeNotLike(String value) {
            addCriterion("base_font_size not like", value, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeIn(List<String> values) {
            addCriterion("base_font_size in", values, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeNotIn(List<String> values) {
            addCriterion("base_font_size not in", values, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeBetween(String value1, String value2) {
            addCriterion("base_font_size between", value1, value2, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andBaseFontSizeNotBetween(String value1, String value2) {
            addCriterion("base_font_size not between", value1, value2, "baseFontSize");
            return (Criteria) this;
        }

        public Criteria andShowValuesIsNull() {
            addCriterion("show_values is null");
            return (Criteria) this;
        }

        public Criteria andShowValuesIsNotNull() {
            addCriterion("show_values is not null");
            return (Criteria) this;
        }

        public Criteria andShowValuesEqualTo(String value) {
            addCriterion("show_values =", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesNotEqualTo(String value) {
            addCriterion("show_values <>", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesGreaterThan(String value) {
            addCriterion("show_values >", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesGreaterThanOrEqualTo(String value) {
            addCriterion("show_values >=", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesLessThan(String value) {
            addCriterion("show_values <", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesLessThanOrEqualTo(String value) {
            addCriterion("show_values <=", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesLike(String value) {
            addCriterion("show_values like", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesNotLike(String value) {
            addCriterion("show_values not like", value, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesIn(List<String> values) {
            addCriterion("show_values in", values, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesNotIn(List<String> values) {
            addCriterion("show_values not in", values, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesBetween(String value1, String value2) {
            addCriterion("show_values between", value1, value2, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowValuesNotBetween(String value1, String value2) {
            addCriterion("show_values not between", value1, value2, "showValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesIsNull() {
            addCriterion("show_percent_values is null");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesIsNotNull() {
            addCriterion("show_percent_values is not null");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesEqualTo(String value) {
            addCriterion("show_percent_values =", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesNotEqualTo(String value) {
            addCriterion("show_percent_values <>", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesGreaterThan(String value) {
            addCriterion("show_percent_values >", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesGreaterThanOrEqualTo(String value) {
            addCriterion("show_percent_values >=", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesLessThan(String value) {
            addCriterion("show_percent_values <", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesLessThanOrEqualTo(String value) {
            addCriterion("show_percent_values <=", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesLike(String value) {
            addCriterion("show_percent_values like", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesNotLike(String value) {
            addCriterion("show_percent_values not like", value, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesIn(List<String> values) {
            addCriterion("show_percent_values in", values, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesNotIn(List<String> values) {
            addCriterion("show_percent_values not in", values, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesBetween(String value1, String value2) {
            addCriterion("show_percent_values between", value1, value2, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentValuesNotBetween(String value1, String value2) {
            addCriterion("show_percent_values not between", value1, value2, "showPercentValues");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipIsNull() {
            addCriterion("show_percent_in_tool_tip is null");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipIsNotNull() {
            addCriterion("show_percent_in_tool_tip is not null");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipEqualTo(String value) {
            addCriterion("show_percent_in_tool_tip =", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipNotEqualTo(String value) {
            addCriterion("show_percent_in_tool_tip <>", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipGreaterThan(String value) {
            addCriterion("show_percent_in_tool_tip >", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipGreaterThanOrEqualTo(String value) {
            addCriterion("show_percent_in_tool_tip >=", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipLessThan(String value) {
            addCriterion("show_percent_in_tool_tip <", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipLessThanOrEqualTo(String value) {
            addCriterion("show_percent_in_tool_tip <=", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipLike(String value) {
            addCriterion("show_percent_in_tool_tip like", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipNotLike(String value) {
            addCriterion("show_percent_in_tool_tip not like", value, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipIn(List<String> values) {
            addCriterion("show_percent_in_tool_tip in", values, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipNotIn(List<String> values) {
            addCriterion("show_percent_in_tool_tip not in", values, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipBetween(String value1, String value2) {
            addCriterion("show_percent_in_tool_tip between", value1, value2, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowPercentInToolTipNotBetween(String value1, String value2) {
            addCriterion("show_percent_in_tool_tip not between", value1, value2, "showPercentInToolTip");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesIsNull() {
            addCriterion("show_y_axis_values is null");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesIsNotNull() {
            addCriterion("show_y_axis_values is not null");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesEqualTo(String value) {
            addCriterion("show_y_axis_values =", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesNotEqualTo(String value) {
            addCriterion("show_y_axis_values <>", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesGreaterThan(String value) {
            addCriterion("show_y_axis_values >", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesGreaterThanOrEqualTo(String value) {
            addCriterion("show_y_axis_values >=", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesLessThan(String value) {
            addCriterion("show_y_axis_values <", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesLessThanOrEqualTo(String value) {
            addCriterion("show_y_axis_values <=", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesLike(String value) {
            addCriterion("show_y_axis_values like", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesNotLike(String value) {
            addCriterion("show_y_axis_values not like", value, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesIn(List<String> values) {
            addCriterion("show_y_axis_values in", values, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesNotIn(List<String> values) {
            addCriterion("show_y_axis_values not in", values, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesBetween(String value1, String value2) {
            addCriterion("show_y_axis_values between", value1, value2, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowYAxisValuesNotBetween(String value1, String value2) {
            addCriterion("show_y_axis_values not between", value1, value2, "showYAxisValues");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsIsNull() {
            addCriterion("force_decimals is null");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsIsNotNull() {
            addCriterion("force_decimals is not null");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsEqualTo(String value) {
            addCriterion("force_decimals =", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsNotEqualTo(String value) {
            addCriterion("force_decimals <>", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsGreaterThan(String value) {
            addCriterion("force_decimals >", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsGreaterThanOrEqualTo(String value) {
            addCriterion("force_decimals >=", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsLessThan(String value) {
            addCriterion("force_decimals <", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsLessThanOrEqualTo(String value) {
            addCriterion("force_decimals <=", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsLike(String value) {
            addCriterion("force_decimals like", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsNotLike(String value) {
            addCriterion("force_decimals not like", value, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsIn(List<String> values) {
            addCriterion("force_decimals in", values, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsNotIn(List<String> values) {
            addCriterion("force_decimals not in", values, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsBetween(String value1, String value2) {
            addCriterion("force_decimals between", value1, value2, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andForceDecimalsNotBetween(String value1, String value2) {
            addCriterion("force_decimals not between", value1, value2, "forceDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsIsNull() {
            addCriterion("y_axis_value_decimals is null");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsIsNotNull() {
            addCriterion("y_axis_value_decimals is not null");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsEqualTo(String value) {
            addCriterion("y_axis_value_decimals =", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsNotEqualTo(String value) {
            addCriterion("y_axis_value_decimals <>", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsGreaterThan(String value) {
            addCriterion("y_axis_value_decimals >", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsGreaterThanOrEqualTo(String value) {
            addCriterion("y_axis_value_decimals >=", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsLessThan(String value) {
            addCriterion("y_axis_value_decimals <", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsLessThanOrEqualTo(String value) {
            addCriterion("y_axis_value_decimals <=", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsLike(String value) {
            addCriterion("y_axis_value_decimals like", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsNotLike(String value) {
            addCriterion("y_axis_value_decimals not like", value, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsIn(List<String> values) {
            addCriterion("y_axis_value_decimals in", values, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsNotIn(List<String> values) {
            addCriterion("y_axis_value_decimals not in", values, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsBetween(String value1, String value2) {
            addCriterion("y_axis_value_decimals between", value1, value2, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andYAxisValueDecimalsNotBetween(String value1, String value2) {
            addCriterion("y_axis_value_decimals not between", value1, value2, "yAxisValueDecimals");
            return (Criteria) this;
        }

        public Criteria andIsPageIsNull() {
            addCriterion("is_page is null");
            return (Criteria) this;
        }

        public Criteria andIsPageIsNotNull() {
            addCriterion("is_page is not null");
            return (Criteria) this;
        }

        public Criteria andIsPageEqualTo(String value) {
            addCriterion("is_page =", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageNotEqualTo(String value) {
            addCriterion("is_page <>", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageGreaterThan(String value) {
            addCriterion("is_page >", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageGreaterThanOrEqualTo(String value) {
            addCriterion("is_page >=", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageLessThan(String value) {
            addCriterion("is_page <", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageLessThanOrEqualTo(String value) {
            addCriterion("is_page <=", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageLike(String value) {
            addCriterion("is_page like", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageNotLike(String value) {
            addCriterion("is_page not like", value, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageIn(List<String> values) {
            addCriterion("is_page in", values, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageNotIn(List<String> values) {
            addCriterion("is_page not in", values, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageBetween(String value1, String value2) {
            addCriterion("is_page between", value1, value2, "isPage");
            return (Criteria) this;
        }

        public Criteria andIsPageNotBetween(String value1, String value2) {
            addCriterion("is_page not between", value1, value2, "isPage");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNull() {
            addCriterion("page_size is null");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNotNull() {
            addCriterion("page_size is not null");
            return (Criteria) this;
        }

        public Criteria andPageSizeEqualTo(Double value) {
            addCriterion("page_size =", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotEqualTo(Double value) {
            addCriterion("page_size <>", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThan(Double value) {
            addCriterion("page_size >", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("page_size >=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThan(Double value) {
            addCriterion("page_size <", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThanOrEqualTo(Double value) {
            addCriterion("page_size <=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeIn(List<Double> values) {
            addCriterion("page_size in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotIn(List<Double> values) {
            addCriterion("page_size not in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeBetween(Double value1, Double value2) {
            addCriterion("page_size between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotBetween(Double value1, Double value2) {
            addCriterion("page_size not between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadIsNull() {
            addCriterion("is_down_load is null");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadIsNotNull() {
            addCriterion("is_down_load is not null");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadEqualTo(String value) {
            addCriterion("is_down_load =", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadNotEqualTo(String value) {
            addCriterion("is_down_load <>", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadGreaterThan(String value) {
            addCriterion("is_down_load >", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadGreaterThanOrEqualTo(String value) {
            addCriterion("is_down_load >=", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadLessThan(String value) {
            addCriterion("is_down_load <", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadLessThanOrEqualTo(String value) {
            addCriterion("is_down_load <=", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadLike(String value) {
            addCriterion("is_down_load like", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadNotLike(String value) {
            addCriterion("is_down_load not like", value, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadIn(List<String> values) {
            addCriterion("is_down_load in", values, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadNotIn(List<String> values) {
            addCriterion("is_down_load not in", values, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadBetween(String value1, String value2) {
            addCriterion("is_down_load between", value1, value2, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andIsDownLoadNotBetween(String value1, String value2) {
            addCriterion("is_down_load not between", value1, value2, "isDownLoad");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnIsNull() {
            addCriterion("x_axis_turn is null");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnIsNotNull() {
            addCriterion("x_axis_turn is not null");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnEqualTo(String value) {
            addCriterion("x_axis_turn =", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnNotEqualTo(String value) {
            addCriterion("x_axis_turn <>", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnGreaterThan(String value) {
            addCriterion("x_axis_turn >", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnGreaterThanOrEqualTo(String value) {
            addCriterion("x_axis_turn >=", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnLessThan(String value) {
            addCriterion("x_axis_turn <", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnLessThanOrEqualTo(String value) {
            addCriterion("x_axis_turn <=", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnLike(String value) {
            addCriterion("x_axis_turn like", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnNotLike(String value) {
            addCriterion("x_axis_turn not like", value, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnIn(List<String> values) {
            addCriterion("x_axis_turn in", values, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnNotIn(List<String> values) {
            addCriterion("x_axis_turn not in", values, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnBetween(String value1, String value2) {
            addCriterion("x_axis_turn between", value1, value2, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andXAxisTurnNotBetween(String value1, String value2) {
            addCriterion("x_axis_turn not between", value1, value2, "xAxisTurn");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesIsNull() {
            addCriterion("show_x_axis_values is null");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesIsNotNull() {
            addCriterion("show_x_axis_values is not null");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesEqualTo(String value) {
            addCriterion("show_x_axis_values =", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesNotEqualTo(String value) {
            addCriterion("show_x_axis_values <>", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesGreaterThan(String value) {
            addCriterion("show_x_axis_values >", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesGreaterThanOrEqualTo(String value) {
            addCriterion("show_x_axis_values >=", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesLessThan(String value) {
            addCriterion("show_x_axis_values <", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesLessThanOrEqualTo(String value) {
            addCriterion("show_x_axis_values <=", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesLike(String value) {
            addCriterion("show_x_axis_values like", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesNotLike(String value) {
            addCriterion("show_x_axis_values not like", value, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesIn(List<String> values) {
            addCriterion("show_x_axis_values in", values, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesNotIn(List<String> values) {
            addCriterion("show_x_axis_values not in", values, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesBetween(String value1, String value2) {
            addCriterion("show_x_axis_values between", value1, value2, "showXAxisValues");
            return (Criteria) this;
        }

        public Criteria andShowXAxisValuesNotBetween(String value1, String value2) {
            addCriterion("show_x_axis_values not between", value1, value2, "showXAxisValues");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}