package com.hansy.tyly.common.orders.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TBusOrderChangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBusOrderChangeExample() {
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

        public Criteria andTableKeyIsNull() {
            addCriterion("table_key is null");
            return (Criteria) this;
        }

        public Criteria andTableKeyIsNotNull() {
            addCriterion("table_key is not null");
            return (Criteria) this;
        }

        public Criteria andTableKeyEqualTo(Integer value) {
            addCriterion("table_key =", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotEqualTo(Integer value) {
            addCriterion("table_key <>", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThan(Integer value) {
            addCriterion("table_key >", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_key >=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThan(Integer value) {
            addCriterion("table_key <", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThanOrEqualTo(Integer value) {
            addCriterion("table_key <=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyIn(List<Integer> values) {
            addCriterion("table_key in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotIn(List<Integer> values) {
            addCriterion("table_key not in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyBetween(Integer value1, Integer value2) {
            addCriterion("table_key between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotBetween(Integer value1, Integer value2) {
            addCriterion("table_key not between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIsNull() {
            addCriterion("change_status is null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIsNotNull() {
            addCriterion("change_status is not null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusEqualTo(String value) {
            addCriterion("change_status =", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotEqualTo(String value) {
            addCriterion("change_status <>", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusGreaterThan(String value) {
            addCriterion("change_status >", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("change_status >=", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusLessThan(String value) {
            addCriterion("change_status <", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusLessThanOrEqualTo(String value) {
            addCriterion("change_status <=", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusLike(String value) {
            addCriterion("change_status like", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotLike(String value) {
            addCriterion("change_status not like", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIn(List<String> values) {
            addCriterion("change_status in", values, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotIn(List<String> values) {
            addCriterion("change_status not in", values, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusBetween(String value1, String value2) {
            addCriterion("change_status between", value1, value2, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotBetween(String value1, String value2) {
            addCriterion("change_status not between", value1, value2, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeDescIsNull() {
            addCriterion("change_desc is null");
            return (Criteria) this;
        }

        public Criteria andChangeDescIsNotNull() {
            addCriterion("change_desc is not null");
            return (Criteria) this;
        }

        public Criteria andChangeDescEqualTo(String value) {
            addCriterion("change_desc =", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescNotEqualTo(String value) {
            addCriterion("change_desc <>", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescGreaterThan(String value) {
            addCriterion("change_desc >", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescGreaterThanOrEqualTo(String value) {
            addCriterion("change_desc >=", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescLessThan(String value) {
            addCriterion("change_desc <", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescLessThanOrEqualTo(String value) {
            addCriterion("change_desc <=", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescLike(String value) {
            addCriterion("change_desc like", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescNotLike(String value) {
            addCriterion("change_desc not like", value, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescIn(List<String> values) {
            addCriterion("change_desc in", values, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescNotIn(List<String> values) {
            addCriterion("change_desc not in", values, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescBetween(String value1, String value2) {
            addCriterion("change_desc between", value1, value2, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDescNotBetween(String value1, String value2) {
            addCriterion("change_desc not between", value1, value2, "changeDesc");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNull() {
            addCriterion("change_date is null");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNotNull() {
            addCriterion("change_date is not null");
            return (Criteria) this;
        }

        public Criteria andChangeDateEqualTo(Date value) {
            addCriterion("change_date =", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotEqualTo(Date value) {
            addCriterion("change_date <>", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThan(Date value) {
            addCriterion("change_date >", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("change_date >=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThan(Date value) {
            addCriterion("change_date <", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThanOrEqualTo(Date value) {
            addCriterion("change_date <=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateIn(List<Date> values) {
            addCriterion("change_date in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotIn(List<Date> values) {
            addCriterion("change_date not in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateBetween(Date value1, Date value2) {
            addCriterion("change_date between", value1, value2, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotBetween(Date value1, Date value2) {
            addCriterion("change_date not between", value1, value2, "changeDate");
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