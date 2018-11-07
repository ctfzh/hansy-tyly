package com.hansy.tyly.admin.dao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TBusRefundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBusRefundExample() {
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

        public Criteria andRefundNoIsNull() {
            addCriterion("refund_no is null");
            return (Criteria) this;
        }

        public Criteria andRefundNoIsNotNull() {
            addCriterion("refund_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNoEqualTo(Integer value) {
            addCriterion("refund_no =", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoNotEqualTo(Integer value) {
            addCriterion("refund_no <>", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoGreaterThan(Integer value) {
            addCriterion("refund_no >", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_no >=", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoLessThan(Integer value) {
            addCriterion("refund_no <", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoLessThanOrEqualTo(Integer value) {
            addCriterion("refund_no <=", value, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoIn(List<Integer> values) {
            addCriterion("refund_no in", values, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoNotIn(List<Integer> values) {
            addCriterion("refund_no not in", values, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoBetween(Integer value1, Integer value2) {
            addCriterion("refund_no between", value1, value2, "refundNo");
            return (Criteria) this;
        }

        public Criteria andRefundNoNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_no not between", value1, value2, "refundNo");
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

        public Criteria andGoodsNumIsNull() {
            addCriterion("goods_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNotNull() {
            addCriterion("goods_num is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumEqualTo(Integer value) {
            addCriterion("goods_num =", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotEqualTo(Integer value) {
            addCriterion("goods_num <>", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThan(Integer value) {
            addCriterion("goods_num >", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_num >=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThan(Integer value) {
            addCriterion("goods_num <", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThanOrEqualTo(Integer value) {
            addCriterion("goods_num <=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIn(List<Integer> values) {
            addCriterion("goods_num in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotIn(List<Integer> values) {
            addCriterion("goods_num not in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumBetween(Integer value1, Integer value2) {
            addCriterion("goods_num between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_num not between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIsNull() {
            addCriterion("refund_amt is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIsNotNull() {
            addCriterion("refund_amt is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmtEqualTo(BigDecimal value) {
            addCriterion("refund_amt =", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotEqualTo(BigDecimal value) {
            addCriterion("refund_amt <>", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtGreaterThan(BigDecimal value) {
            addCriterion("refund_amt >", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amt >=", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtLessThan(BigDecimal value) {
            addCriterion("refund_amt <", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amt <=", value, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtIn(List<BigDecimal> values) {
            addCriterion("refund_amt in", values, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotIn(List<BigDecimal> values) {
            addCriterion("refund_amt not in", values, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amt between", value1, value2, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amt not between", value1, value2, "refundAmt");
            return (Criteria) this;
        }

        public Criteria andRefundReasonIsNull() {
            addCriterion("refund_reason is null");
            return (Criteria) this;
        }

        public Criteria andRefundReasonIsNotNull() {
            addCriterion("refund_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRefundReasonEqualTo(String value) {
            addCriterion("refund_reason =", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonNotEqualTo(String value) {
            addCriterion("refund_reason <>", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonGreaterThan(String value) {
            addCriterion("refund_reason >", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonGreaterThanOrEqualTo(String value) {
            addCriterion("refund_reason >=", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonLessThan(String value) {
            addCriterion("refund_reason <", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonLessThanOrEqualTo(String value) {
            addCriterion("refund_reason <=", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonLike(String value) {
            addCriterion("refund_reason like", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonNotLike(String value) {
            addCriterion("refund_reason not like", value, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonIn(List<String> values) {
            addCriterion("refund_reason in", values, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonNotIn(List<String> values) {
            addCriterion("refund_reason not in", values, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonBetween(String value1, String value2) {
            addCriterion("refund_reason between", value1, value2, "refundReason");
            return (Criteria) this;
        }

        public Criteria andRefundReasonNotBetween(String value1, String value2) {
            addCriterion("refund_reason not between", value1, value2, "refundReason");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("apply_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("apply_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterion("apply_date =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterion("apply_date <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterion("apply_date >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_date >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterion("apply_date <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_date <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterion("apply_date in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterion("apply_date not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterion("apply_date between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_date not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApprStatusIsNull() {
            addCriterion("appr_status is null");
            return (Criteria) this;
        }

        public Criteria andApprStatusIsNotNull() {
            addCriterion("appr_status is not null");
            return (Criteria) this;
        }

        public Criteria andApprStatusEqualTo(String value) {
            addCriterion("appr_status =", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusNotEqualTo(String value) {
            addCriterion("appr_status <>", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusGreaterThan(String value) {
            addCriterion("appr_status >", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusGreaterThanOrEqualTo(String value) {
            addCriterion("appr_status >=", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusLessThan(String value) {
            addCriterion("appr_status <", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusLessThanOrEqualTo(String value) {
            addCriterion("appr_status <=", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusLike(String value) {
            addCriterion("appr_status like", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusNotLike(String value) {
            addCriterion("appr_status not like", value, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusIn(List<String> values) {
            addCriterion("appr_status in", values, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusNotIn(List<String> values) {
            addCriterion("appr_status not in", values, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusBetween(String value1, String value2) {
            addCriterion("appr_status between", value1, value2, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprStatusNotBetween(String value1, String value2) {
            addCriterion("appr_status not between", value1, value2, "apprStatus");
            return (Criteria) this;
        }

        public Criteria andApprDateIsNull() {
            addCriterion("appr_date is null");
            return (Criteria) this;
        }

        public Criteria andApprDateIsNotNull() {
            addCriterion("appr_date is not null");
            return (Criteria) this;
        }

        public Criteria andApprDateEqualTo(Date value) {
            addCriterion("appr_date =", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateNotEqualTo(Date value) {
            addCriterion("appr_date <>", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateGreaterThan(Date value) {
            addCriterion("appr_date >", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateGreaterThanOrEqualTo(Date value) {
            addCriterion("appr_date >=", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateLessThan(Date value) {
            addCriterion("appr_date <", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateLessThanOrEqualTo(Date value) {
            addCriterion("appr_date <=", value, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateIn(List<Date> values) {
            addCriterion("appr_date in", values, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateNotIn(List<Date> values) {
            addCriterion("appr_date not in", values, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateBetween(Date value1, Date value2) {
            addCriterion("appr_date between", value1, value2, "apprDate");
            return (Criteria) this;
        }

        public Criteria andApprDateNotBetween(Date value1, Date value2) {
            addCriterion("appr_date not between", value1, value2, "apprDate");
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