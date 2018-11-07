package com.hansy.tyly.dealers.goods.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TBusShoppCartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBusShoppCartExample() {
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

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
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

        public Criteria andBuyTypeEqualTo(String value) {
            addCriterion("buy_type =", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotEqualTo(String value) {
            addCriterion("buy_type <>", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeGreaterThan(String value) {
            addCriterion("buy_type >", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("buy_type >=", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeLessThan(String value) {
            addCriterion("buy_type <", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeLessThanOrEqualTo(String value) {
            addCriterion("buy_type <=", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeLike(String value) {
            addCriterion("buy_type like", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotLike(String value) {
            addCriterion("buy_type not like", value, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeIn(List<String> values) {
            addCriterion("buy_type in", values, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotIn(List<String> values) {
            addCriterion("buy_type not in", values, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeBetween(String value1, String value2) {
            addCriterion("buy_type between", value1, value2, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyTypeNotBetween(String value1, String value2) {
            addCriterion("buy_type not between", value1, value2, "buyType");
            return (Criteria) this;
        }

        public Criteria andBuyNumIsNull() {
            addCriterion("buy_num is null");
            return (Criteria) this;
        }

        public Criteria andBuyNumIsNotNull() {
            addCriterion("buy_num is not null");
            return (Criteria) this;
        }

        public Criteria andBuyNumEqualTo(Integer value) {
            addCriterion("buy_num =", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumNotEqualTo(Integer value) {
            addCriterion("buy_num <>", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumGreaterThan(Integer value) {
            addCriterion("buy_num >", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_num >=", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumLessThan(Integer value) {
            addCriterion("buy_num <", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumLessThanOrEqualTo(Integer value) {
            addCriterion("buy_num <=", value, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumIn(List<Integer> values) {
            addCriterion("buy_num in", values, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumNotIn(List<Integer> values) {
            addCriterion("buy_num not in", values, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumBetween(Integer value1, Integer value2) {
            addCriterion("buy_num between", value1, value2, "buyNum");
            return (Criteria) this;
        }

        public Criteria andBuyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_num not between", value1, value2, "buyNum");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNull() {
            addCriterion("goods_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("goods_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(BigDecimal value) {
            addCriterion("goods_price =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(BigDecimal value) {
            addCriterion("goods_price <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(BigDecimal value) {
            addCriterion("goods_price >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_price >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(BigDecimal value) {
            addCriterion("goods_price <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_price <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<BigDecimal> values) {
            addCriterion("goods_price in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<BigDecimal> values) {
            addCriterion("goods_price not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_price between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_price not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNull() {
            addCriterion("insert_date is null");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNotNull() {
            addCriterion("insert_date is not null");
            return (Criteria) this;
        }

        public Criteria andInsertDateEqualTo(Date value) {
            addCriterion("insert_date =", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotEqualTo(Date value) {
            addCriterion("insert_date <>", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThan(Date value) {
            addCriterion("insert_date >", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_date >=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThan(Date value) {
            addCriterion("insert_date <", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThanOrEqualTo(Date value) {
            addCriterion("insert_date <=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIn(List<Date> values) {
            addCriterion("insert_date in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotIn(List<Date> values) {
            addCriterion("insert_date not in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateBetween(Date value1, Date value2) {
            addCriterion("insert_date between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotBetween(Date value1, Date value2) {
            addCriterion("insert_date not between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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