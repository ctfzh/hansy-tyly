package com.hansy.tyly.admin.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPubMerInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TPubMerInfoExample() {
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

        public Criteria andMerNameIsNull() {
            addCriterion("mer_name is null");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNotNull() {
            addCriterion("mer_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerNameEqualTo(String value) {
            addCriterion("mer_name =", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotEqualTo(String value) {
            addCriterion("mer_name <>", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThan(String value) {
            addCriterion("mer_name >", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("mer_name >=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThan(String value) {
            addCriterion("mer_name <", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThanOrEqualTo(String value) {
            addCriterion("mer_name <=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLike(String value) {
            addCriterion("mer_name like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotLike(String value) {
            addCriterion("mer_name not like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameIn(List<String> values) {
            addCriterion("mer_name in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotIn(List<String> values) {
            addCriterion("mer_name not in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameBetween(String value1, String value2) {
            addCriterion("mer_name between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotBetween(String value1, String value2) {
            addCriterion("mer_name not between", value1, value2, "merName");
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

        public Criteria andMerRegNoIsNull() {
            addCriterion("mer_reg_no is null");
            return (Criteria) this;
        }

        public Criteria andMerRegNoIsNotNull() {
            addCriterion("mer_reg_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerRegNoEqualTo(String value) {
            addCriterion("mer_reg_no =", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoNotEqualTo(String value) {
            addCriterion("mer_reg_no <>", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoGreaterThan(String value) {
            addCriterion("mer_reg_no >", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoGreaterThanOrEqualTo(String value) {
            addCriterion("mer_reg_no >=", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoLessThan(String value) {
            addCriterion("mer_reg_no <", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoLessThanOrEqualTo(String value) {
            addCriterion("mer_reg_no <=", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoLike(String value) {
            addCriterion("mer_reg_no like", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoNotLike(String value) {
            addCriterion("mer_reg_no not like", value, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoIn(List<String> values) {
            addCriterion("mer_reg_no in", values, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoNotIn(List<String> values) {
            addCriterion("mer_reg_no not in", values, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoBetween(String value1, String value2) {
            addCriterion("mer_reg_no between", value1, value2, "merRegNo");
            return (Criteria) this;
        }

        public Criteria andMerRegNoNotBetween(String value1, String value2) {
            addCriterion("mer_reg_no not between", value1, value2, "merRegNo");
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

        public Criteria andMerAddreIsNull() {
            addCriterion("mer_addre is null");
            return (Criteria) this;
        }

        public Criteria andMerAddreIsNotNull() {
            addCriterion("mer_addre is not null");
            return (Criteria) this;
        }

        public Criteria andMerAddreEqualTo(String value) {
            addCriterion("mer_addre =", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreNotEqualTo(String value) {
            addCriterion("mer_addre <>", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreGreaterThan(String value) {
            addCriterion("mer_addre >", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreGreaterThanOrEqualTo(String value) {
            addCriterion("mer_addre >=", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreLessThan(String value) {
            addCriterion("mer_addre <", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreLessThanOrEqualTo(String value) {
            addCriterion("mer_addre <=", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreLike(String value) {
            addCriterion("mer_addre like", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreNotLike(String value) {
            addCriterion("mer_addre not like", value, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreIn(List<String> values) {
            addCriterion("mer_addre in", values, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreNotIn(List<String> values) {
            addCriterion("mer_addre not in", values, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreBetween(String value1, String value2) {
            addCriterion("mer_addre between", value1, value2, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerAddreNotBetween(String value1, String value2) {
            addCriterion("mer_addre not between", value1, value2, "merAddre");
            return (Criteria) this;
        }

        public Criteria andMerContactIsNull() {
            addCriterion("mer_contact is null");
            return (Criteria) this;
        }

        public Criteria andMerContactIsNotNull() {
            addCriterion("mer_contact is not null");
            return (Criteria) this;
        }

        public Criteria andMerContactEqualTo(String value) {
            addCriterion("mer_contact =", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactNotEqualTo(String value) {
            addCriterion("mer_contact <>", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactGreaterThan(String value) {
            addCriterion("mer_contact >", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactGreaterThanOrEqualTo(String value) {
            addCriterion("mer_contact >=", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactLessThan(String value) {
            addCriterion("mer_contact <", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactLessThanOrEqualTo(String value) {
            addCriterion("mer_contact <=", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactLike(String value) {
            addCriterion("mer_contact like", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactNotLike(String value) {
            addCriterion("mer_contact not like", value, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactIn(List<String> values) {
            addCriterion("mer_contact in", values, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactNotIn(List<String> values) {
            addCriterion("mer_contact not in", values, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactBetween(String value1, String value2) {
            addCriterion("mer_contact between", value1, value2, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactNotBetween(String value1, String value2) {
            addCriterion("mer_contact not between", value1, value2, "merContact");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneIsNull() {
            addCriterion("mer_contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneIsNotNull() {
            addCriterion("mer_contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneEqualTo(String value) {
            addCriterion("mer_contact_phone =", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneNotEqualTo(String value) {
            addCriterion("mer_contact_phone <>", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneGreaterThan(String value) {
            addCriterion("mer_contact_phone >", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("mer_contact_phone >=", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneLessThan(String value) {
            addCriterion("mer_contact_phone <", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("mer_contact_phone <=", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneLike(String value) {
            addCriterion("mer_contact_phone like", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneNotLike(String value) {
            addCriterion("mer_contact_phone not like", value, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneIn(List<String> values) {
            addCriterion("mer_contact_phone in", values, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneNotIn(List<String> values) {
            addCriterion("mer_contact_phone not in", values, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneBetween(String value1, String value2) {
            addCriterion("mer_contact_phone between", value1, value2, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerContactPhoneNotBetween(String value1, String value2) {
            addCriterion("mer_contact_phone not between", value1, value2, "merContactPhone");
            return (Criteria) this;
        }

        public Criteria andMerStatusIsNull() {
            addCriterion("mer_status is null");
            return (Criteria) this;
        }

        public Criteria andMerStatusIsNotNull() {
            addCriterion("mer_status is not null");
            return (Criteria) this;
        }

        public Criteria andMerStatusEqualTo(String value) {
            addCriterion("mer_status =", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusNotEqualTo(String value) {
            addCriterion("mer_status <>", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusGreaterThan(String value) {
            addCriterion("mer_status >", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mer_status >=", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusLessThan(String value) {
            addCriterion("mer_status <", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusLessThanOrEqualTo(String value) {
            addCriterion("mer_status <=", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusLike(String value) {
            addCriterion("mer_status like", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusNotLike(String value) {
            addCriterion("mer_status not like", value, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusIn(List<String> values) {
            addCriterion("mer_status in", values, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusNotIn(List<String> values) {
            addCriterion("mer_status not in", values, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusBetween(String value1, String value2) {
            addCriterion("mer_status between", value1, value2, "merStatus");
            return (Criteria) this;
        }

        public Criteria andMerStatusNotBetween(String value1, String value2) {
            addCriterion("mer_status not between", value1, value2, "merStatus");
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