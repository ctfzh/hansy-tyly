package com.hansy.tyly.dealers.goods.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TGoodsDistributorMerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TGoodsDistributorMerExample() {
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

        public Criteria andGoodsNoIsNull() {
            addCriterion("goods_no is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIsNotNull() {
            addCriterion("goods_no is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNoEqualTo(String value) {
            addCriterion("goods_no =", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotEqualTo(String value) {
            addCriterion("goods_no <>", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThan(String value) {
            addCriterion("goods_no >", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoGreaterThanOrEqualTo(String value) {
            addCriterion("goods_no >=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThan(String value) {
            addCriterion("goods_no <", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLessThanOrEqualTo(String value) {
            addCriterion("goods_no <=", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoLike(String value) {
            addCriterion("goods_no like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotLike(String value) {
            addCriterion("goods_no not like", value, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoIn(List<String> values) {
            addCriterion("goods_no in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotIn(List<String> values) {
            addCriterion("goods_no not in", values, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoBetween(String value1, String value2) {
            addCriterion("goods_no between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andGoodsNoNotBetween(String value1, String value2) {
            addCriterion("goods_no not between", value1, value2, "goodsNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoIsNull() {
            addCriterion("distributor_no is null");
            return (Criteria) this;
        }

        public Criteria andDistributorNoIsNotNull() {
            addCriterion("distributor_no is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorNoEqualTo(String value) {
            addCriterion("distributor_no =", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoNotEqualTo(String value) {
            addCriterion("distributor_no <>", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoGreaterThan(String value) {
            addCriterion("distributor_no >", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_no >=", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoLessThan(String value) {
            addCriterion("distributor_no <", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoLessThanOrEqualTo(String value) {
            addCriterion("distributor_no <=", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoLike(String value) {
            addCriterion("distributor_no like", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoNotLike(String value) {
            addCriterion("distributor_no not like", value, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoIn(List<String> values) {
            addCriterion("distributor_no in", values, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoNotIn(List<String> values) {
            addCriterion("distributor_no not in", values, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoBetween(String value1, String value2) {
            addCriterion("distributor_no between", value1, value2, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andDistributorNoNotBetween(String value1, String value2) {
            addCriterion("distributor_no not between", value1, value2, "distributorNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNull() {
            addCriterion("mer_no is null");
            return (Criteria) this;
        }

        public Criteria andMerNoIsNotNull() {
            addCriterion("mer_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerNoEqualTo(String value) {
            addCriterion("mer_no =", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotEqualTo(String value) {
            addCriterion("mer_no <>", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThan(String value) {
            addCriterion("mer_no >", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoGreaterThanOrEqualTo(String value) {
            addCriterion("mer_no >=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThan(String value) {
            addCriterion("mer_no <", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLessThanOrEqualTo(String value) {
            addCriterion("mer_no <=", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoLike(String value) {
            addCriterion("mer_no like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotLike(String value) {
            addCriterion("mer_no not like", value, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoIn(List<String> values) {
            addCriterion("mer_no in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotIn(List<String> values) {
            addCriterion("mer_no not in", values, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoBetween(String value1, String value2) {
            addCriterion("mer_no between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andMerNoNotBetween(String value1, String value2) {
            addCriterion("mer_no not between", value1, value2, "merNo");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtIsNull() {
            addCriterion("goods_amt is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtIsNotNull() {
            addCriterion("goods_amt is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtEqualTo(BigDecimal value) {
            addCriterion("goods_amt =", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtNotEqualTo(BigDecimal value) {
            addCriterion("goods_amt <>", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtGreaterThan(BigDecimal value) {
            addCriterion("goods_amt >", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_amt >=", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtLessThan(BigDecimal value) {
            addCriterion("goods_amt <", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_amt <=", value, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtIn(List<BigDecimal> values) {
            addCriterion("goods_amt in", values, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtNotIn(List<BigDecimal> values) {
            addCriterion("goods_amt not in", values, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_amt between", value1, value2, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andGoodsAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_amt not between", value1, value2, "goodsAmt");
            return (Criteria) this;
        }

        public Criteria andOnDateIsNull() {
            addCriterion("on_date is null");
            return (Criteria) this;
        }

        public Criteria andOnDateIsNotNull() {
            addCriterion("on_date is not null");
            return (Criteria) this;
        }

        public Criteria andOnDateEqualTo(Date value) {
            addCriterion("on_date =", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotEqualTo(Date value) {
            addCriterion("on_date <>", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateGreaterThan(Date value) {
            addCriterion("on_date >", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("on_date >=", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateLessThan(Date value) {
            addCriterion("on_date <", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateLessThanOrEqualTo(Date value) {
            addCriterion("on_date <=", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateIn(List<Date> values) {
            addCriterion("on_date in", values, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotIn(List<Date> values) {
            addCriterion("on_date not in", values, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateBetween(Date value1, Date value2) {
            addCriterion("on_date between", value1, value2, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotBetween(Date value1, Date value2) {
            addCriterion("on_date not between", value1, value2, "onDate");
            return (Criteria) this;
        }

        public Criteria andDownDateIsNull() {
            addCriterion("down_date is null");
            return (Criteria) this;
        }

        public Criteria andDownDateIsNotNull() {
            addCriterion("down_date is not null");
            return (Criteria) this;
        }

        public Criteria andDownDateEqualTo(Date value) {
            addCriterion("down_date =", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateNotEqualTo(Date value) {
            addCriterion("down_date <>", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateGreaterThan(Date value) {
            addCriterion("down_date >", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateGreaterThanOrEqualTo(Date value) {
            addCriterion("down_date >=", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateLessThan(Date value) {
            addCriterion("down_date <", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateLessThanOrEqualTo(Date value) {
            addCriterion("down_date <=", value, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateIn(List<Date> values) {
            addCriterion("down_date in", values, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateNotIn(List<Date> values) {
            addCriterion("down_date not in", values, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateBetween(Date value1, Date value2) {
            addCriterion("down_date between", value1, value2, "downDate");
            return (Criteria) this;
        }

        public Criteria andDownDateNotBetween(Date value1, Date value2) {
            addCriterion("down_date not between", value1, value2, "downDate");
            return (Criteria) this;
        }

        public Criteria andOnStatusIsNull() {
            addCriterion("on_status is null");
            return (Criteria) this;
        }

        public Criteria andOnStatusIsNotNull() {
            addCriterion("on_status is not null");
            return (Criteria) this;
        }

        public Criteria andOnStatusEqualTo(String value) {
            addCriterion("on_status =", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotEqualTo(String value) {
            addCriterion("on_status <>", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusGreaterThan(String value) {
            addCriterion("on_status >", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusGreaterThanOrEqualTo(String value) {
            addCriterion("on_status >=", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusLessThan(String value) {
            addCriterion("on_status <", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusLessThanOrEqualTo(String value) {
            addCriterion("on_status <=", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusLike(String value) {
            addCriterion("on_status like", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotLike(String value) {
            addCriterion("on_status not like", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusIn(List<String> values) {
            addCriterion("on_status in", values, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotIn(List<String> values) {
            addCriterion("on_status not in", values, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusBetween(String value1, String value2) {
            addCriterion("on_status between", value1, value2, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotBetween(String value1, String value2) {
            addCriterion("on_status not between", value1, value2, "onStatus");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
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