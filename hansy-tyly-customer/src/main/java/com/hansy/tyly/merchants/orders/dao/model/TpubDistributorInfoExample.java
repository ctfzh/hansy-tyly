package com.hansy.tyly.merchants.orders.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TpubDistributorInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TpubDistributorInfoExample() {
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

        public Criteria andDistributorNameIsNull() {
            addCriterion("distributor_name is null");
            return (Criteria) this;
        }

        public Criteria andDistributorNameIsNotNull() {
            addCriterion("distributor_name is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorNameEqualTo(String value) {
            addCriterion("distributor_name =", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameNotEqualTo(String value) {
            addCriterion("distributor_name <>", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameGreaterThan(String value) {
            addCriterion("distributor_name >", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_name >=", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameLessThan(String value) {
            addCriterion("distributor_name <", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameLessThanOrEqualTo(String value) {
            addCriterion("distributor_name <=", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameLike(String value) {
            addCriterion("distributor_name like", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameNotLike(String value) {
            addCriterion("distributor_name not like", value, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameIn(List<String> values) {
            addCriterion("distributor_name in", values, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameNotIn(List<String> values) {
            addCriterion("distributor_name not in", values, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameBetween(String value1, String value2) {
            addCriterion("distributor_name between", value1, value2, "distributorName");
            return (Criteria) this;
        }

        public Criteria andDistributorNameNotBetween(String value1, String value2) {
            addCriterion("distributor_name not between", value1, value2, "distributorName");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNull() {
            addCriterion("legal_name is null");
            return (Criteria) this;
        }

        public Criteria andLegalNameIsNotNull() {
            addCriterion("legal_name is not null");
            return (Criteria) this;
        }

        public Criteria andLegalNameEqualTo(String value) {
            addCriterion("legal_name =", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotEqualTo(String value) {
            addCriterion("legal_name <>", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThan(String value) {
            addCriterion("legal_name >", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameGreaterThanOrEqualTo(String value) {
            addCriterion("legal_name >=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThan(String value) {
            addCriterion("legal_name <", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLessThanOrEqualTo(String value) {
            addCriterion("legal_name <=", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameLike(String value) {
            addCriterion("legal_name like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotLike(String value) {
            addCriterion("legal_name not like", value, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameIn(List<String> values) {
            addCriterion("legal_name in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotIn(List<String> values) {
            addCriterion("legal_name not in", values, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameBetween(String value1, String value2) {
            addCriterion("legal_name between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andLegalNameNotBetween(String value1, String value2) {
            addCriterion("legal_name not between", value1, value2, "legalName");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoIsNull() {
            addCriterion("distributor_reg_no is null");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoIsNotNull() {
            addCriterion("distributor_reg_no is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoEqualTo(String value) {
            addCriterion("distributor_reg_no =", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoNotEqualTo(String value) {
            addCriterion("distributor_reg_no <>", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoGreaterThan(String value) {
            addCriterion("distributor_reg_no >", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_reg_no >=", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoLessThan(String value) {
            addCriterion("distributor_reg_no <", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoLessThanOrEqualTo(String value) {
            addCriterion("distributor_reg_no <=", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoLike(String value) {
            addCriterion("distributor_reg_no like", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoNotLike(String value) {
            addCriterion("distributor_reg_no not like", value, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoIn(List<String> values) {
            addCriterion("distributor_reg_no in", values, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoNotIn(List<String> values) {
            addCriterion("distributor_reg_no not in", values, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoBetween(String value1, String value2) {
            addCriterion("distributor_reg_no between", value1, value2, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andDistributorRegNoNotBetween(String value1, String value2) {
            addCriterion("distributor_reg_no not between", value1, value2, "distributorRegNo");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNull() {
            addCriterion("company_type is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("company_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(String value) {
            addCriterion("company_type =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(String value) {
            addCriterion("company_type <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(String value) {
            addCriterion("company_type >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("company_type >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(String value) {
            addCriterion("company_type <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("company_type <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLike(String value) {
            addCriterion("company_type like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotLike(String value) {
            addCriterion("company_type not like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<String> values) {
            addCriterion("company_type in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<String> values) {
            addCriterion("company_type not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(String value1, String value2) {
            addCriterion("company_type between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("company_type not between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreIsNull() {
            addCriterion("distributor_addre is null");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreIsNotNull() {
            addCriterion("distributor_addre is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreEqualTo(String value) {
            addCriterion("distributor_addre =", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreNotEqualTo(String value) {
            addCriterion("distributor_addre <>", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreGreaterThan(String value) {
            addCriterion("distributor_addre >", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_addre >=", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreLessThan(String value) {
            addCriterion("distributor_addre <", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreLessThanOrEqualTo(String value) {
            addCriterion("distributor_addre <=", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreLike(String value) {
            addCriterion("distributor_addre like", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreNotLike(String value) {
            addCriterion("distributor_addre not like", value, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreIn(List<String> values) {
            addCriterion("distributor_addre in", values, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreNotIn(List<String> values) {
            addCriterion("distributor_addre not in", values, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreBetween(String value1, String value2) {
            addCriterion("distributor_addre between", value1, value2, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorAddreNotBetween(String value1, String value2) {
            addCriterion("distributor_addre not between", value1, value2, "distributorAddre");
            return (Criteria) this;
        }

        public Criteria andDistributorContactIsNull() {
            addCriterion("distributor_contact is null");
            return (Criteria) this;
        }

        public Criteria andDistributorContactIsNotNull() {
            addCriterion("distributor_contact is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorContactEqualTo(String value) {
            addCriterion("distributor_contact =", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactNotEqualTo(String value) {
            addCriterion("distributor_contact <>", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactGreaterThan(String value) {
            addCriterion("distributor_contact >", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_contact >=", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactLessThan(String value) {
            addCriterion("distributor_contact <", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactLessThanOrEqualTo(String value) {
            addCriterion("distributor_contact <=", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactLike(String value) {
            addCriterion("distributor_contact like", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactNotLike(String value) {
            addCriterion("distributor_contact not like", value, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactIn(List<String> values) {
            addCriterion("distributor_contact in", values, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactNotIn(List<String> values) {
            addCriterion("distributor_contact not in", values, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactBetween(String value1, String value2) {
            addCriterion("distributor_contact between", value1, value2, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactNotBetween(String value1, String value2) {
            addCriterion("distributor_contact not between", value1, value2, "distributorContact");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneIsNull() {
            addCriterion("distributor_contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneIsNotNull() {
            addCriterion("distributor_contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneEqualTo(String value) {
            addCriterion("distributor_contact_phone =", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneNotEqualTo(String value) {
            addCriterion("distributor_contact_phone <>", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneGreaterThan(String value) {
            addCriterion("distributor_contact_phone >", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_contact_phone >=", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneLessThan(String value) {
            addCriterion("distributor_contact_phone <", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("distributor_contact_phone <=", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneLike(String value) {
            addCriterion("distributor_contact_phone like", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneNotLike(String value) {
            addCriterion("distributor_contact_phone not like", value, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneIn(List<String> values) {
            addCriterion("distributor_contact_phone in", values, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneNotIn(List<String> values) {
            addCriterion("distributor_contact_phone not in", values, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneBetween(String value1, String value2) {
            addCriterion("distributor_contact_phone between", value1, value2, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorContactPhoneNotBetween(String value1, String value2) {
            addCriterion("distributor_contact_phone not between", value1, value2, "distributorContactPhone");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusIsNull() {
            addCriterion("distributor_status is null");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusIsNotNull() {
            addCriterion("distributor_status is not null");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusEqualTo(String value) {
            addCriterion("distributor_status =", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusNotEqualTo(String value) {
            addCriterion("distributor_status <>", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusGreaterThan(String value) {
            addCriterion("distributor_status >", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusGreaterThanOrEqualTo(String value) {
            addCriterion("distributor_status >=", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusLessThan(String value) {
            addCriterion("distributor_status <", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusLessThanOrEqualTo(String value) {
            addCriterion("distributor_status <=", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusLike(String value) {
            addCriterion("distributor_status like", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusNotLike(String value) {
            addCriterion("distributor_status not like", value, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusIn(List<String> values) {
            addCriterion("distributor_status in", values, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusNotIn(List<String> values) {
            addCriterion("distributor_status not in", values, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusBetween(String value1, String value2) {
            addCriterion("distributor_status between", value1, value2, "distributorStatus");
            return (Criteria) this;
        }

        public Criteria andDistributorStatusNotBetween(String value1, String value2) {
            addCriterion("distributor_status not between", value1, value2, "distributorStatus");
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