package com.hansy.tyly.dealers.goods.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TGoodsTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TGoodsTypeExample() {
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

        public Criteria andTypeNoIsNull() {
            addCriterion("goodsType.type_no is null");
            return (Criteria) this;
        }

        public Criteria andTypeNoIsNotNull() {
            addCriterion("goodsType.type_no is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNoEqualTo(String value) {
            addCriterion("goodsType.type_no =", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotEqualTo(String value) {
            addCriterion("goodsType.type_no <>", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoGreaterThan(String value) {
            addCriterion("goodsType.type_no >", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoGreaterThanOrEqualTo(String value) {
            addCriterion("goodsType.type_no >=", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLessThan(String value) {
            addCriterion("goodsType.type_no <", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLessThanOrEqualTo(String value) {
            addCriterion("goodsType.type_no <=", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoLike(String value) {
            addCriterion("goodsType.type_no like", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotLike(String value) {
            addCriterion("goodsType.type_no not like", value, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoIn(List<String> values) {
            addCriterion("goodsType.type_no in", values, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotIn(List<String> values) {
            addCriterion("goodsType.type_no not in", values, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoBetween(String value1, String value2) {
            addCriterion("goodsType.type_no between", value1, value2, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNoNotBetween(String value1, String value2) {
            addCriterion("goodsType.type_no not between", value1, value2, "typeNo");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("goodsType.type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("goodsType.type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("goodsType.type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("goodsType.type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("goodsType.type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("goodsType.type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("goodsType.type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("goodsType.type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("goodsType.type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("goodsType.type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("goodsType.type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("goodsType.type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("goodsType.type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("goodsType.type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNull() {
            addCriterion("goodsType.type_desc is null");
            return (Criteria) this;
        }

        public Criteria andTypeDescIsNotNull() {
            addCriterion("goodsType.type_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTypeDescEqualTo(String value) {
            addCriterion("goodsType.type_desc =", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotEqualTo(String value) {
            addCriterion("goodsType.type_desc <>", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThan(String value) {
            addCriterion("goodsType.type_desc >", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("goodsType.type_desc >=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThan(String value) {
            addCriterion("goodsType.type_desc <", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLessThanOrEqualTo(String value) {
            addCriterion("goodsType.type_desc <=", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescLike(String value) {
            addCriterion("goodsType.type_desc like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotLike(String value) {
            addCriterion("goodsType.type_desc not like", value, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescIn(List<String> values) {
            addCriterion("goodsType.type_desc in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotIn(List<String> values) {
            addCriterion("goodsType.type_desc not in", values, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescBetween(String value1, String value2) {
            addCriterion("goodsType.type_desc between", value1, value2, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeDescNotBetween(String value1, String value2) {
            addCriterion("goodsType.type_desc not between", value1, value2, "typeDesc");
            return (Criteria) this;
        }

        public Criteria andTypeStatusIsNull() {
            addCriterion("goodsType.type_status is null");
            return (Criteria) this;
        }

        public Criteria andTypeStatusIsNotNull() {
            addCriterion("goodsType.type_status is not null");
            return (Criteria) this;
        }

        public Criteria andTypeStatusEqualTo(String value) {
            addCriterion("goodsType.type_status =", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotEqualTo(String value) {
            addCriterion("goodsType.type_status <>", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusGreaterThan(String value) {
            addCriterion("goodsType.type_status >", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("goodsType.type_status >=", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusLessThan(String value) {
            addCriterion("goodsType.type_status <", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusLessThanOrEqualTo(String value) {
            addCriterion("goodsType.type_status <=", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusLike(String value) {
            addCriterion("goodsType.type_status like", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotLike(String value) {
            addCriterion("goodsType.type_status not like", value, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusIn(List<String> values) {
            addCriterion("goodsType.type_status in", values, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotIn(List<String> values) {
            addCriterion("goodsType.type_status not in", values, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusBetween(String value1, String value2) {
            addCriterion("goodsType.type_status between", value1, value2, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andTypeStatusNotBetween(String value1, String value2) {
            addCriterion("goodsType.type_status not between", value1, value2, "typeStatus");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNull() {
            addCriterion("goodsType.insert_date is null");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNotNull() {
            addCriterion("goodsType.insert_date is not null");
            return (Criteria) this;
        }

        public Criteria andInsertDateEqualTo(Date value) {
            addCriterion("goodsType.insert_date =", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotEqualTo(Date value) {
            addCriterion("goodsType.insert_date <>", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThan(Date value) {
            addCriterion("goodsType.insert_date >", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThanOrEqualTo(Date value) {
            addCriterion("goodsType.insert_date >=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThan(Date value) {
            addCriterion("goodsType.insert_date <", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThanOrEqualTo(Date value) {
            addCriterion("goodsType.insert_date <=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIn(List<Date> values) {
            addCriterion("goodsType.insert_date in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotIn(List<Date> values) {
            addCriterion("goodsType.insert_date not in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateBetween(Date value1, Date value2) {
            addCriterion("goodsType.insert_date between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotBetween(Date value1, Date value2) {
            addCriterion("goodsType.insert_date not between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("goodsType.update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("goodsType.update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("goodsType.update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("goodsType.update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("goodsType.update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("goodsType.update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("goodsType.update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("goodsType.update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("goodsType.update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("goodsType.update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("goodsType.update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("goodsType.update_date not between", value1, value2, "updateDate");
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