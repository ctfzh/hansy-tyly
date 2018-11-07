package com.hansy.tyly.admin.dao.model;

import java.util.ArrayList;
import java.util.List;

public class TReportTableConfDtlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TReportTableConfDtlExample() {
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

        public Criteria andDetailKeyIsNull() {
            addCriterion("DETAIL_KEY is null");
            return (Criteria) this;
        }

        public Criteria andDetailKeyIsNotNull() {
            addCriterion("DETAIL_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andDetailKeyEqualTo(String value) {
            addCriterion("DETAIL_KEY =", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotEqualTo(String value) {
            addCriterion("DETAIL_KEY <>", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyGreaterThan(String value) {
            addCriterion("DETAIL_KEY >", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_KEY >=", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLessThan(String value) {
            addCriterion("DETAIL_KEY <", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_KEY <=", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyLike(String value) {
            addCriterion("DETAIL_KEY like", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotLike(String value) {
            addCriterion("DETAIL_KEY not like", value, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyIn(List<String> values) {
            addCriterion("DETAIL_KEY in", values, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotIn(List<String> values) {
            addCriterion("DETAIL_KEY not in", values, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyBetween(String value1, String value2) {
            addCriterion("DETAIL_KEY between", value1, value2, "detailKey");
            return (Criteria) this;
        }

        public Criteria andDetailKeyNotBetween(String value1, String value2) {
            addCriterion("DETAIL_KEY not between", value1, value2, "detailKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyIsNull() {
            addCriterion("REPORT_KEY is null");
            return (Criteria) this;
        }

        public Criteria andReportKeyIsNotNull() {
            addCriterion("REPORT_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andReportKeyEqualTo(String value) {
            addCriterion("REPORT_KEY =", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotEqualTo(String value) {
            addCriterion("REPORT_KEY <>", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyGreaterThan(String value) {
            addCriterion("REPORT_KEY >", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_KEY >=", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLessThan(String value) {
            addCriterion("REPORT_KEY <", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLessThanOrEqualTo(String value) {
            addCriterion("REPORT_KEY <=", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyLike(String value) {
            addCriterion("REPORT_KEY like", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotLike(String value) {
            addCriterion("REPORT_KEY not like", value, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyIn(List<String> values) {
            addCriterion("REPORT_KEY in", values, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotIn(List<String> values) {
            addCriterion("REPORT_KEY not in", values, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyBetween(String value1, String value2) {
            addCriterion("REPORT_KEY between", value1, value2, "reportKey");
            return (Criteria) this;
        }

        public Criteria andReportKeyNotBetween(String value1, String value2) {
            addCriterion("REPORT_KEY not between", value1, value2, "reportKey");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("`COLUMN_NAME` is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("`COLUMN_NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("`COLUMN_NAME` =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("`COLUMN_NAME` <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("`COLUMN_NAME` >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("`COLUMN_NAME` >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("`COLUMN_NAME` <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("`COLUMN_NAME` <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("`COLUMN_NAME` like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("`COLUMN_NAME` not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("`COLUMN_NAME` in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("`COLUMN_NAME` not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("`COLUMN_NAME` between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("`COLUMN_NAME` not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIsNull() {
            addCriterion("COLUMN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIsNotNull() {
            addCriterion("COLUMN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeEqualTo(String value) {
            addCriterion("COLUMN_CODE =", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotEqualTo(String value) {
            addCriterion("COLUMN_CODE <>", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThan(String value) {
            addCriterion("COLUMN_CODE >", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_CODE >=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThan(String value) {
            addCriterion("COLUMN_CODE <", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_CODE <=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLike(String value) {
            addCriterion("COLUMN_CODE like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotLike(String value) {
            addCriterion("COLUMN_CODE not like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIn(List<String> values) {
            addCriterion("COLUMN_CODE in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotIn(List<String> values) {
            addCriterion("COLUMN_CODE not in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeBetween(String value1, String value2) {
            addCriterion("COLUMN_CODE between", value1, value2, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotBetween(String value1, String value2) {
            addCriterion("COLUMN_CODE not between", value1, value2, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayIsNull() {
            addCriterion("COLUMN_DISPLAY is null");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayIsNotNull() {
            addCriterion("COLUMN_DISPLAY is not null");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayEqualTo(String value) {
            addCriterion("COLUMN_DISPLAY =", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayNotEqualTo(String value) {
            addCriterion("COLUMN_DISPLAY <>", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayGreaterThan(String value) {
            addCriterion("COLUMN_DISPLAY >", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_DISPLAY >=", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayLessThan(String value) {
            addCriterion("COLUMN_DISPLAY <", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_DISPLAY <=", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayLike(String value) {
            addCriterion("COLUMN_DISPLAY like", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayNotLike(String value) {
            addCriterion("COLUMN_DISPLAY not like", value, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayIn(List<String> values) {
            addCriterion("COLUMN_DISPLAY in", values, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayNotIn(List<String> values) {
            addCriterion("COLUMN_DISPLAY not in", values, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayBetween(String value1, String value2) {
            addCriterion("COLUMN_DISPLAY between", value1, value2, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDisplayNotBetween(String value1, String value2) {
            addCriterion("COLUMN_DISPLAY not between", value1, value2, "columnDisplay");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownIsNull() {
            addCriterion("COLUMN_DROPDOWN is null");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownIsNotNull() {
            addCriterion("COLUMN_DROPDOWN is not null");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownEqualTo(String value) {
            addCriterion("COLUMN_DROPDOWN =", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownNotEqualTo(String value) {
            addCriterion("COLUMN_DROPDOWN <>", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownGreaterThan(String value) {
            addCriterion("COLUMN_DROPDOWN >", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_DROPDOWN >=", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownLessThan(String value) {
            addCriterion("COLUMN_DROPDOWN <", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_DROPDOWN <=", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownLike(String value) {
            addCriterion("COLUMN_DROPDOWN like", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownNotLike(String value) {
            addCriterion("COLUMN_DROPDOWN not like", value, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownIn(List<String> values) {
            addCriterion("COLUMN_DROPDOWN in", values, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownNotIn(List<String> values) {
            addCriterion("COLUMN_DROPDOWN not in", values, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownBetween(String value1, String value2) {
            addCriterion("COLUMN_DROPDOWN between", value1, value2, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andColumnDropdownNotBetween(String value1, String value2) {
            addCriterion("COLUMN_DROPDOWN not between", value1, value2, "columnDropdown");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("IS_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("IS_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(String value) {
            addCriterion("IS_SHOW =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(String value) {
            addCriterion("IS_SHOW <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(String value) {
            addCriterion("IS_SHOW >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SHOW >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(String value) {
            addCriterion("IS_SHOW <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(String value) {
            addCriterion("IS_SHOW <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLike(String value) {
            addCriterion("IS_SHOW like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotLike(String value) {
            addCriterion("IS_SHOW not like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<String> values) {
            addCriterion("IS_SHOW in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<String> values) {
            addCriterion("IS_SHOW not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(String value1, String value2) {
            addCriterion("IS_SHOW between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(String value1, String value2) {
            addCriterion("IS_SHOW not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsNeedIsNull() {
            addCriterion("IS_NEED is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedIsNotNull() {
            addCriterion("IS_NEED is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedEqualTo(String value) {
            addCriterion("IS_NEED =", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotEqualTo(String value) {
            addCriterion("IS_NEED <>", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedGreaterThan(String value) {
            addCriterion("IS_NEED >", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_NEED >=", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedLessThan(String value) {
            addCriterion("IS_NEED <", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedLessThanOrEqualTo(String value) {
            addCriterion("IS_NEED <=", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedLike(String value) {
            addCriterion("IS_NEED like", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotLike(String value) {
            addCriterion("IS_NEED not like", value, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedIn(List<String> values) {
            addCriterion("IS_NEED in", values, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotIn(List<String> values) {
            addCriterion("IS_NEED not in", values, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedBetween(String value1, String value2) {
            addCriterion("IS_NEED between", value1, value2, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsNeedNotBetween(String value1, String value2) {
            addCriterion("IS_NEED not between", value1, value2, "isNeed");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionIsNull() {
            addCriterion("IS_QUERYCONDITION is null");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionIsNotNull() {
            addCriterion("IS_QUERYCONDITION is not null");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionEqualTo(String value) {
            addCriterion("IS_QUERYCONDITION =", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionNotEqualTo(String value) {
            addCriterion("IS_QUERYCONDITION <>", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionGreaterThan(String value) {
            addCriterion("IS_QUERYCONDITION >", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionGreaterThanOrEqualTo(String value) {
            addCriterion("IS_QUERYCONDITION >=", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionLessThan(String value) {
            addCriterion("IS_QUERYCONDITION <", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionLessThanOrEqualTo(String value) {
            addCriterion("IS_QUERYCONDITION <=", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionLike(String value) {
            addCriterion("IS_QUERYCONDITION like", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionNotLike(String value) {
            addCriterion("IS_QUERYCONDITION not like", value, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionIn(List<String> values) {
            addCriterion("IS_QUERYCONDITION in", values, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionNotIn(List<String> values) {
            addCriterion("IS_QUERYCONDITION not in", values, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionBetween(String value1, String value2) {
            addCriterion("IS_QUERYCONDITION between", value1, value2, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andIsQueryconditionNotBetween(String value1, String value2) {
            addCriterion("IS_QUERYCONDITION not between", value1, value2, "isQuerycondition");
            return (Criteria) this;
        }

        public Criteria andCondTypeIsNull() {
            addCriterion("COND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCondTypeIsNotNull() {
            addCriterion("COND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCondTypeEqualTo(String value) {
            addCriterion("COND_TYPE =", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotEqualTo(String value) {
            addCriterion("COND_TYPE <>", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeGreaterThan(String value) {
            addCriterion("COND_TYPE >", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COND_TYPE >=", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLessThan(String value) {
            addCriterion("COND_TYPE <", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLessThanOrEqualTo(String value) {
            addCriterion("COND_TYPE <=", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeLike(String value) {
            addCriterion("COND_TYPE like", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotLike(String value) {
            addCriterion("COND_TYPE not like", value, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeIn(List<String> values) {
            addCriterion("COND_TYPE in", values, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotIn(List<String> values) {
            addCriterion("COND_TYPE not in", values, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeBetween(String value1, String value2) {
            addCriterion("COND_TYPE between", value1, value2, "condType");
            return (Criteria) this;
        }

        public Criteria andCondTypeNotBetween(String value1, String value2) {
            addCriterion("COND_TYPE not between", value1, value2, "condType");
            return (Criteria) this;
        }

        public Criteria andCondExpressionIsNull() {
            addCriterion("COND_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andCondExpressionIsNotNull() {
            addCriterion("COND_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andCondExpressionEqualTo(String value) {
            addCriterion("COND_EXPRESSION =", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionNotEqualTo(String value) {
            addCriterion("COND_EXPRESSION <>", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionGreaterThan(String value) {
            addCriterion("COND_EXPRESSION >", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("COND_EXPRESSION >=", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionLessThan(String value) {
            addCriterion("COND_EXPRESSION <", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionLessThanOrEqualTo(String value) {
            addCriterion("COND_EXPRESSION <=", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionLike(String value) {
            addCriterion("COND_EXPRESSION like", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionNotLike(String value) {
            addCriterion("COND_EXPRESSION not like", value, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionIn(List<String> values) {
            addCriterion("COND_EXPRESSION in", values, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionNotIn(List<String> values) {
            addCriterion("COND_EXPRESSION not in", values, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionBetween(String value1, String value2) {
            addCriterion("COND_EXPRESSION between", value1, value2, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondExpressionNotBetween(String value1, String value2) {
            addCriterion("COND_EXPRESSION not between", value1, value2, "condExpression");
            return (Criteria) this;
        }

        public Criteria andCondWhereIsNull() {
            addCriterion("COND_WHERE is null");
            return (Criteria) this;
        }

        public Criteria andCondWhereIsNotNull() {
            addCriterion("COND_WHERE is not null");
            return (Criteria) this;
        }

        public Criteria andCondWhereEqualTo(String value) {
            addCriterion("COND_WHERE =", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereNotEqualTo(String value) {
            addCriterion("COND_WHERE <>", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereGreaterThan(String value) {
            addCriterion("COND_WHERE >", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereGreaterThanOrEqualTo(String value) {
            addCriterion("COND_WHERE >=", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereLessThan(String value) {
            addCriterion("COND_WHERE <", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereLessThanOrEqualTo(String value) {
            addCriterion("COND_WHERE <=", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereLike(String value) {
            addCriterion("COND_WHERE like", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereNotLike(String value) {
            addCriterion("COND_WHERE not like", value, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereIn(List<String> values) {
            addCriterion("COND_WHERE in", values, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereNotIn(List<String> values) {
            addCriterion("COND_WHERE not in", values, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereBetween(String value1, String value2) {
            addCriterion("COND_WHERE between", value1, value2, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondWhereNotBetween(String value1, String value2) {
            addCriterion("COND_WHERE not between", value1, value2, "condWhere");
            return (Criteria) this;
        }

        public Criteria andCondMustIsNull() {
            addCriterion("COND_MUST is null");
            return (Criteria) this;
        }

        public Criteria andCondMustIsNotNull() {
            addCriterion("COND_MUST is not null");
            return (Criteria) this;
        }

        public Criteria andCondMustEqualTo(String value) {
            addCriterion("COND_MUST =", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustNotEqualTo(String value) {
            addCriterion("COND_MUST <>", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustGreaterThan(String value) {
            addCriterion("COND_MUST >", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustGreaterThanOrEqualTo(String value) {
            addCriterion("COND_MUST >=", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustLessThan(String value) {
            addCriterion("COND_MUST <", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustLessThanOrEqualTo(String value) {
            addCriterion("COND_MUST <=", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustLike(String value) {
            addCriterion("COND_MUST like", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustNotLike(String value) {
            addCriterion("COND_MUST not like", value, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustIn(List<String> values) {
            addCriterion("COND_MUST in", values, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustNotIn(List<String> values) {
            addCriterion("COND_MUST not in", values, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustBetween(String value1, String value2) {
            addCriterion("COND_MUST between", value1, value2, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondMustNotBetween(String value1, String value2) {
            addCriterion("COND_MUST not between", value1, value2, "condMust");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightIsNull() {
            addCriterion("COND_EXPRESSION_RIGHT is null");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightIsNotNull() {
            addCriterion("COND_EXPRESSION_RIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightEqualTo(String value) {
            addCriterion("COND_EXPRESSION_RIGHT =", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightNotEqualTo(String value) {
            addCriterion("COND_EXPRESSION_RIGHT <>", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightGreaterThan(String value) {
            addCriterion("COND_EXPRESSION_RIGHT >", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightGreaterThanOrEqualTo(String value) {
            addCriterion("COND_EXPRESSION_RIGHT >=", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightLessThan(String value) {
            addCriterion("COND_EXPRESSION_RIGHT <", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightLessThanOrEqualTo(String value) {
            addCriterion("COND_EXPRESSION_RIGHT <=", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightLike(String value) {
            addCriterion("COND_EXPRESSION_RIGHT like", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightNotLike(String value) {
            addCriterion("COND_EXPRESSION_RIGHT not like", value, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightIn(List<String> values) {
            addCriterion("COND_EXPRESSION_RIGHT in", values, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightNotIn(List<String> values) {
            addCriterion("COND_EXPRESSION_RIGHT not in", values, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightBetween(String value1, String value2) {
            addCriterion("COND_EXPRESSION_RIGHT between", value1, value2, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andCondExpressionRightNotBetween(String value1, String value2) {
            addCriterion("COND_EXPRESSION_RIGHT not between", value1, value2, "condExpressionRight");
            return (Criteria) this;
        }

        public Criteria andRecordTypeIsNull() {
            addCriterion("RECORD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRecordTypeIsNotNull() {
            addCriterion("RECORD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTypeEqualTo(String value) {
            addCriterion("RECORD_TYPE =", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeNotEqualTo(String value) {
            addCriterion("RECORD_TYPE <>", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeGreaterThan(String value) {
            addCriterion("RECORD_TYPE >", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_TYPE >=", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeLessThan(String value) {
            addCriterion("RECORD_TYPE <", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeLessThanOrEqualTo(String value) {
            addCriterion("RECORD_TYPE <=", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeLike(String value) {
            addCriterion("RECORD_TYPE like", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeNotLike(String value) {
            addCriterion("RECORD_TYPE not like", value, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeIn(List<String> values) {
            addCriterion("RECORD_TYPE in", values, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeNotIn(List<String> values) {
            addCriterion("RECORD_TYPE not in", values, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeBetween(String value1, String value2) {
            addCriterion("RECORD_TYPE between", value1, value2, "recordType");
            return (Criteria) this;
        }

        public Criteria andRecordTypeNotBetween(String value1, String value2) {
            addCriterion("RECORD_TYPE not between", value1, value2, "recordType");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNull() {
            addCriterion("DEFAULT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("DEFAULT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("DEFAULT_VALUE =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("DEFAULT_VALUE <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("DEFAULT_VALUE >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_VALUE >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("DEFAULT_VALUE <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_VALUE <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("DEFAULT_VALUE like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("DEFAULT_VALUE not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("DEFAULT_VALUE in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("DEFAULT_VALUE not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("DEFAULT_VALUE between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_VALUE not between", value1, value2, "defaultValue");
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