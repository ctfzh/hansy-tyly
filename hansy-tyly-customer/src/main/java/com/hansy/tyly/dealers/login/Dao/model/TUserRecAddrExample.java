package com.hansy.tyly.dealers.login.Dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TUserRecAddrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUserRecAddrExample() {
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

        public Criteria andTableKeyEqualTo(String value) {
            addCriterion("table_key =", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotEqualTo(String value) {
            addCriterion("table_key <>", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThan(String value) {
            addCriterion("table_key >", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThanOrEqualTo(String value) {
            addCriterion("table_key >=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThan(String value) {
            addCriterion("table_key <", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThanOrEqualTo(String value) {
            addCriterion("table_key <=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLike(String value) {
            addCriterion("table_key like", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotLike(String value) {
            addCriterion("table_key not like", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyIn(List<String> values) {
            addCriterion("table_key in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotIn(List<String> values) {
            addCriterion("table_key not in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyBetween(String value1, String value2) {
            addCriterion("table_key between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotBetween(String value1, String value2) {
            addCriterion("table_key not between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(String value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(String value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(String value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(String value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(String value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLike(String value) {
            addCriterion("user_no like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotLike(String value) {
            addCriterion("user_no not like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<String> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<String> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(String value1, String value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(String value1, String value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andRecNameIsNull() {
            addCriterion("rec_name is null");
            return (Criteria) this;
        }

        public Criteria andRecNameIsNotNull() {
            addCriterion("rec_name is not null");
            return (Criteria) this;
        }

        public Criteria andRecNameEqualTo(String value) {
            addCriterion("rec_name =", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameNotEqualTo(String value) {
            addCriterion("rec_name <>", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameGreaterThan(String value) {
            addCriterion("rec_name >", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameGreaterThanOrEqualTo(String value) {
            addCriterion("rec_name >=", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameLessThan(String value) {
            addCriterion("rec_name <", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameLessThanOrEqualTo(String value) {
            addCriterion("rec_name <=", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameLike(String value) {
            addCriterion("rec_name like", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameNotLike(String value) {
            addCriterion("rec_name not like", value, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameIn(List<String> values) {
            addCriterion("rec_name in", values, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameNotIn(List<String> values) {
            addCriterion("rec_name not in", values, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameBetween(String value1, String value2) {
            addCriterion("rec_name between", value1, value2, "recName");
            return (Criteria) this;
        }

        public Criteria andRecNameNotBetween(String value1, String value2) {
            addCriterion("rec_name not between", value1, value2, "recName");
            return (Criteria) this;
        }

        public Criteria andRecAddrIsNull() {
            addCriterion("rec_addr is null");
            return (Criteria) this;
        }

        public Criteria andRecAddrIsNotNull() {
            addCriterion("rec_addr is not null");
            return (Criteria) this;
        }

        public Criteria andRecAddrEqualTo(String value) {
            addCriterion("rec_addr =", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrNotEqualTo(String value) {
            addCriterion("rec_addr <>", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrGreaterThan(String value) {
            addCriterion("rec_addr >", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrGreaterThanOrEqualTo(String value) {
            addCriterion("rec_addr >=", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrLessThan(String value) {
            addCriterion("rec_addr <", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrLessThanOrEqualTo(String value) {
            addCriterion("rec_addr <=", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrLike(String value) {
            addCriterion("rec_addr like", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrNotLike(String value) {
            addCriterion("rec_addr not like", value, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrIn(List<String> values) {
            addCriterion("rec_addr in", values, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrNotIn(List<String> values) {
            addCriterion("rec_addr not in", values, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrBetween(String value1, String value2) {
            addCriterion("rec_addr between", value1, value2, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecAddrNotBetween(String value1, String value2) {
            addCriterion("rec_addr not between", value1, value2, "recAddr");
            return (Criteria) this;
        }

        public Criteria andRecPhoneIsNull() {
            addCriterion("rec_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecPhoneIsNotNull() {
            addCriterion("rec_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecPhoneEqualTo(String value) {
            addCriterion("rec_phone =", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneNotEqualTo(String value) {
            addCriterion("rec_phone <>", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneGreaterThan(String value) {
            addCriterion("rec_phone >", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("rec_phone >=", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneLessThan(String value) {
            addCriterion("rec_phone <", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneLessThanOrEqualTo(String value) {
            addCriterion("rec_phone <=", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneLike(String value) {
            addCriterion("rec_phone like", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneNotLike(String value) {
            addCriterion("rec_phone not like", value, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneIn(List<String> values) {
            addCriterion("rec_phone in", values, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneNotIn(List<String> values) {
            addCriterion("rec_phone not in", values, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneBetween(String value1, String value2) {
            addCriterion("rec_phone between", value1, value2, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecPhoneNotBetween(String value1, String value2) {
            addCriterion("rec_phone not between", value1, value2, "recPhone");
            return (Criteria) this;
        }

        public Criteria andRecProvinceIsNull() {
            addCriterion("rec_province is null");
            return (Criteria) this;
        }

        public Criteria andRecProvinceIsNotNull() {
            addCriterion("rec_province is not null");
            return (Criteria) this;
        }

        public Criteria andRecProvinceEqualTo(String value) {
            addCriterion("rec_province =", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceNotEqualTo(String value) {
            addCriterion("rec_province <>", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceGreaterThan(String value) {
            addCriterion("rec_province >", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("rec_province >=", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceLessThan(String value) {
            addCriterion("rec_province <", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceLessThanOrEqualTo(String value) {
            addCriterion("rec_province <=", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceLike(String value) {
            addCriterion("rec_province like", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceNotLike(String value) {
            addCriterion("rec_province not like", value, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceIn(List<String> values) {
            addCriterion("rec_province in", values, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceNotIn(List<String> values) {
            addCriterion("rec_province not in", values, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceBetween(String value1, String value2) {
            addCriterion("rec_province between", value1, value2, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecProvinceNotBetween(String value1, String value2) {
            addCriterion("rec_province not between", value1, value2, "recProvince");
            return (Criteria) this;
        }

        public Criteria andRecCityIsNull() {
            addCriterion("rec_city is null");
            return (Criteria) this;
        }

        public Criteria andRecCityIsNotNull() {
            addCriterion("rec_city is not null");
            return (Criteria) this;
        }

        public Criteria andRecCityEqualTo(String value) {
            addCriterion("rec_city =", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityNotEqualTo(String value) {
            addCriterion("rec_city <>", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityGreaterThan(String value) {
            addCriterion("rec_city >", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityGreaterThanOrEqualTo(String value) {
            addCriterion("rec_city >=", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityLessThan(String value) {
            addCriterion("rec_city <", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityLessThanOrEqualTo(String value) {
            addCriterion("rec_city <=", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityLike(String value) {
            addCriterion("rec_city like", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityNotLike(String value) {
            addCriterion("rec_city not like", value, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityIn(List<String> values) {
            addCriterion("rec_city in", values, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityNotIn(List<String> values) {
            addCriterion("rec_city not in", values, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityBetween(String value1, String value2) {
            addCriterion("rec_city between", value1, value2, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCityNotBetween(String value1, String value2) {
            addCriterion("rec_city not between", value1, value2, "recCity");
            return (Criteria) this;
        }

        public Criteria andRecCountyIsNull() {
            addCriterion("rec_county is null");
            return (Criteria) this;
        }

        public Criteria andRecCountyIsNotNull() {
            addCriterion("rec_county is not null");
            return (Criteria) this;
        }

        public Criteria andRecCountyEqualTo(String value) {
            addCriterion("rec_county =", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyNotEqualTo(String value) {
            addCriterion("rec_county <>", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyGreaterThan(String value) {
            addCriterion("rec_county >", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyGreaterThanOrEqualTo(String value) {
            addCriterion("rec_county >=", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyLessThan(String value) {
            addCriterion("rec_county <", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyLessThanOrEqualTo(String value) {
            addCriterion("rec_county <=", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyLike(String value) {
            addCriterion("rec_county like", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyNotLike(String value) {
            addCriterion("rec_county not like", value, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyIn(List<String> values) {
            addCriterion("rec_county in", values, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyNotIn(List<String> values) {
            addCriterion("rec_county not in", values, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyBetween(String value1, String value2) {
            addCriterion("rec_county between", value1, value2, "recCounty");
            return (Criteria) this;
        }

        public Criteria andRecCountyNotBetween(String value1, String value2) {
            addCriterion("rec_county not between", value1, value2, "recCounty");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("post_code is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("post_code is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("post_code =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("post_code <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("post_code >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("post_code >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("post_code <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("post_code <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("post_code like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("post_code not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("post_code in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("post_code not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("post_code between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("post_code not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(String value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(String value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(String value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(String value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(String value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLike(String value) {
            addCriterion("is_default like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotLike(String value) {
            addCriterion("is_default not like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<String> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<String> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(String value1, String value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(String value1, String value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
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