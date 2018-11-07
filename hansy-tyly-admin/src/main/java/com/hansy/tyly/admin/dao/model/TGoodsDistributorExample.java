package com.hansy.tyly.admin.dao.model;

import java.util.ArrayList;
import java.util.List;

public class TGoodsDistributorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TGoodsDistributorExample() {
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

        public Criteria andGoodsStockIsNull() {
            addCriterion("goods_stock is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockIsNotNull() {
            addCriterion("goods_stock is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockEqualTo(Integer value) {
            addCriterion("goods_stock =", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotEqualTo(Integer value) {
            addCriterion("goods_stock <>", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockGreaterThan(Integer value) {
            addCriterion("goods_stock >", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_stock >=", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockLessThan(Integer value) {
            addCriterion("goods_stock <", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockLessThanOrEqualTo(Integer value) {
            addCriterion("goods_stock <=", value, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockIn(List<Integer> values) {
            addCriterion("goods_stock in", values, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotIn(List<Integer> values) {
            addCriterion("goods_stock not in", values, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockBetween(Integer value1, Integer value2) {
            addCriterion("goods_stock between", value1, value2, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_stock not between", value1, value2, "goodsStock");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdIsNull() {
            addCriterion("goods_stock_threshold is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdIsNotNull() {
            addCriterion("goods_stock_threshold is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdEqualTo(Integer value) {
            addCriterion("goods_stock_threshold =", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdNotEqualTo(Integer value) {
            addCriterion("goods_stock_threshold <>", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdGreaterThan(Integer value) {
            addCriterion("goods_stock_threshold >", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_stock_threshold >=", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdLessThan(Integer value) {
            addCriterion("goods_stock_threshold <", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_stock_threshold <=", value, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdIn(List<Integer> values) {
            addCriterion("goods_stock_threshold in", values, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdNotIn(List<Integer> values) {
            addCriterion("goods_stock_threshold not in", values, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdBetween(Integer value1, Integer value2) {
            addCriterion("goods_stock_threshold between", value1, value2, "goodsStockThreshold");
            return (Criteria) this;
        }

        public Criteria andGoodsStockThresholdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_stock_threshold not between", value1, value2, "goodsStockThreshold");
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

        public Criteria andOnStatusEqualTo(Byte value) {
            addCriterion("on_status =", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotEqualTo(Byte value) {
            addCriterion("on_status <>", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusGreaterThan(Byte value) {
            addCriterion("on_status >", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("on_status >=", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusLessThan(Byte value) {
            addCriterion("on_status <", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusLessThanOrEqualTo(Byte value) {
            addCriterion("on_status <=", value, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusIn(List<Byte> values) {
            addCriterion("on_status in", values, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotIn(List<Byte> values) {
            addCriterion("on_status not in", values, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusBetween(Byte value1, Byte value2) {
            addCriterion("on_status between", value1, value2, "onStatus");
            return (Criteria) this;
        }

        public Criteria andOnStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("on_status not between", value1, value2, "onStatus");
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