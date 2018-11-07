package com.hansy.tyly.admin.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPubVisitRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TPubVisitRecordExample() {
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

        public Criteria andVisitNoIsNull() {
            addCriterion("visit_no is null");
            return (Criteria) this;
        }

        public Criteria andVisitNoIsNotNull() {
            addCriterion("visit_no is not null");
            return (Criteria) this;
        }

        public Criteria andVisitNoEqualTo(String value) {
            addCriterion("visit_no =", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotEqualTo(String value) {
            addCriterion("visit_no <>", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoGreaterThan(String value) {
            addCriterion("visit_no >", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoGreaterThanOrEqualTo(String value) {
            addCriterion("visit_no >=", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoLessThan(String value) {
            addCriterion("visit_no <", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoLessThanOrEqualTo(String value) {
            addCriterion("visit_no <=", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoLike(String value) {
            addCriterion("visit_no like", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotLike(String value) {
            addCriterion("visit_no not like", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoIn(List<String> values) {
            addCriterion("visit_no in", values, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotIn(List<String> values) {
            addCriterion("visit_no not in", values, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoBetween(String value1, String value2) {
            addCriterion("visit_no between", value1, value2, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotBetween(String value1, String value2) {
            addCriterion("visit_no not between", value1, value2, "visitNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoIsNull() {
            addCriterion("staff_no is null");
            return (Criteria) this;
        }

        public Criteria andStaffNoIsNotNull() {
            addCriterion("staff_no is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNoEqualTo(String value) {
            addCriterion("staff_no =", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotEqualTo(String value) {
            addCriterion("staff_no <>", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoGreaterThan(String value) {
            addCriterion("staff_no >", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoGreaterThanOrEqualTo(String value) {
            addCriterion("staff_no >=", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLessThan(String value) {
            addCriterion("staff_no <", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLessThanOrEqualTo(String value) {
            addCriterion("staff_no <=", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoLike(String value) {
            addCriterion("staff_no like", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotLike(String value) {
            addCriterion("staff_no not like", value, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoIn(List<String> values) {
            addCriterion("staff_no in", values, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotIn(List<String> values) {
            addCriterion("staff_no not in", values, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoBetween(String value1, String value2) {
            addCriterion("staff_no between", value1, value2, "staffNo");
            return (Criteria) this;
        }

        public Criteria andStaffNoNotBetween(String value1, String value2) {
            addCriterion("staff_no not between", value1, value2, "staffNo");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNull() {
            addCriterion("cust_type is null");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNotNull() {
            addCriterion("cust_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustTypeEqualTo(String value) {
            addCriterion("cust_type =", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotEqualTo(String value) {
            addCriterion("cust_type <>", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThan(String value) {
            addCriterion("cust_type >", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cust_type >=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThan(String value) {
            addCriterion("cust_type <", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThanOrEqualTo(String value) {
            addCriterion("cust_type <=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLike(String value) {
            addCriterion("cust_type like", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotLike(String value) {
            addCriterion("cust_type not like", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeIn(List<String> values) {
            addCriterion("cust_type in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotIn(List<String> values) {
            addCriterion("cust_type not in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeBetween(String value1, String value2) {
            addCriterion("cust_type between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotBetween(String value1, String value2) {
            addCriterion("cust_type not between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andCustNoIsNull() {
            addCriterion("cust_no is null");
            return (Criteria) this;
        }

        public Criteria andCustNoIsNotNull() {
            addCriterion("cust_no is not null");
            return (Criteria) this;
        }

        public Criteria andCustNoEqualTo(String value) {
            addCriterion("cust_no =", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotEqualTo(String value) {
            addCriterion("cust_no <>", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThan(String value) {
            addCriterion("cust_no >", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThanOrEqualTo(String value) {
            addCriterion("cust_no >=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThan(String value) {
            addCriterion("cust_no <", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThanOrEqualTo(String value) {
            addCriterion("cust_no <=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLike(String value) {
            addCriterion("cust_no like", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotLike(String value) {
            addCriterion("cust_no not like", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoIn(List<String> values) {
            addCriterion("cust_no in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotIn(List<String> values) {
            addCriterion("cust_no not in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoBetween(String value1, String value2) {
            addCriterion("cust_no between", value1, value2, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotBetween(String value1, String value2) {
            addCriterion("cust_no not between", value1, value2, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNull() {
            addCriterion("cust_name is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("cust_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("cust_name =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("cust_name <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("cust_name >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("cust_name >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("cust_name <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("cust_name <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("cust_name like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("cust_name not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("cust_name in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("cust_name not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("cust_name between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("cust_name not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andVisitAddreIsNull() {
            addCriterion("visit_addre is null");
            return (Criteria) this;
        }

        public Criteria andVisitAddreIsNotNull() {
            addCriterion("visit_addre is not null");
            return (Criteria) this;
        }

        public Criteria andVisitAddreEqualTo(String value) {
            addCriterion("visit_addre =", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreNotEqualTo(String value) {
            addCriterion("visit_addre <>", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreGreaterThan(String value) {
            addCriterion("visit_addre >", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreGreaterThanOrEqualTo(String value) {
            addCriterion("visit_addre >=", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreLessThan(String value) {
            addCriterion("visit_addre <", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreLessThanOrEqualTo(String value) {
            addCriterion("visit_addre <=", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreLike(String value) {
            addCriterion("visit_addre like", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreNotLike(String value) {
            addCriterion("visit_addre not like", value, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreIn(List<String> values) {
            addCriterion("visit_addre in", values, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreNotIn(List<String> values) {
            addCriterion("visit_addre not in", values, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreBetween(String value1, String value2) {
            addCriterion("visit_addre between", value1, value2, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andVisitAddreNotBetween(String value1, String value2) {
            addCriterion("visit_addre not between", value1, value2, "visitAddre");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andVisitContentIsNull() {
            addCriterion("visit_content is null");
            return (Criteria) this;
        }

        public Criteria andVisitContentIsNotNull() {
            addCriterion("visit_content is not null");
            return (Criteria) this;
        }

        public Criteria andVisitContentEqualTo(String value) {
            addCriterion("visit_content =", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotEqualTo(String value) {
            addCriterion("visit_content <>", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentGreaterThan(String value) {
            addCriterion("visit_content >", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentGreaterThanOrEqualTo(String value) {
            addCriterion("visit_content >=", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLessThan(String value) {
            addCriterion("visit_content <", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLessThanOrEqualTo(String value) {
            addCriterion("visit_content <=", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLike(String value) {
            addCriterion("visit_content like", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotLike(String value) {
            addCriterion("visit_content not like", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentIn(List<String> values) {
            addCriterion("visit_content in", values, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotIn(List<String> values) {
            addCriterion("visit_content not in", values, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentBetween(String value1, String value2) {
            addCriterion("visit_content between", value1, value2, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotBetween(String value1, String value2) {
            addCriterion("visit_content not between", value1, value2, "visitContent");
            return (Criteria) this;
        }

        public Criteria andBuyTypeIsNull() {
            addCriterion("buy_type is null");
            return (Criteria) this;
        }

        public Criteria andBuyTypeIsNotNull() {
            addCriterion("buy_type is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTypeEqualTo(Date value) {
            addCriterion("buy_type =", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotEqualTo(Date value) {
            addCriterion("buy_type <>", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeGreaterThan(Date value) {
            addCriterion("buy_type >", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeGreaterThanOrEqualTo(Date value) {
            addCriterion("buy_type >=", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeLessThan(Date value) {
            addCriterion("buy_type <", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeLessThanOrEqualTo(Date value) {
            addCriterion("buy_type <=", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeIn(List<Date> values) {
            addCriterion("buy_type in", values, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotIn(List<Date> values) {
            addCriterion("buy_type not in", values, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeBetween(Date value1, Date value2) {
            addCriterion("buy_type between", value1, value2, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotBetween(Date value1, Date value2) {
            addCriterion("buy_type not between", value1, value2, "buyType");
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