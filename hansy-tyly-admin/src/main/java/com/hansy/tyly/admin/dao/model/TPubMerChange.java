package com.hansy.tyly.admin.dao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_pub_mer_change")
public class TPubMerChange {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    private String tableKey;

    /**
     * 商户编号
     */
    @Column(name = "mer_no")
    private String merNo;

    /**
     * 商户名称
     */
    @Column(name = "mer_name")
    private String merName;

    /**
     * 商户法人代表
     */
    @Column(name = "legal_name")
    private String legalName;

    /**
     * 商户注册号
     */
    @Column(name = "mer_reg_no")
    private String merRegNo;

    /**
     * 企业类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 商户地址
     */
    @Column(name = "mer_addre")
    private String merAddre;

    /**
     * 商户联系人
     */
    @Column(name = "mer_contact")
    private String merContact;

    /**
     * 商户联系人电话
     */
    @Column(name = "mer_contact_phone")
    private String merContactPhone;

    /**
     * 商户状态
     */
    @Column(name = "mer_status")
    private String merStatus;

    /**
     * 所属经销商
     */
    @Column(name = "distributor_no")
    private String distributorNo;

    /**
     * 新增时间
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * 获取主键
     *
     * @return table_key - 主键
     */
    public String getTableKey() {
        return tableKey;
    }

    /**
     * 设置主键
     *
     * @param tableKey 主键
     */
    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 获取商户编号
     *
     * @return mer_no - 商户编号
     */
    public String getMerNo() {
        return merNo;
    }

    /**
     * 设置商户编号
     *
     * @param merNo 商户编号
     */
    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    /**
     * 获取商户名称
     *
     * @return mer_name - 商户名称
     */
    public String getMerName() {
        return merName;
    }

    /**
     * 设置商户名称
     *
     * @param merName 商户名称
     */
    public void setMerName(String merName) {
        this.merName = merName;
    }

    /**
     * 获取商户法人代表
     *
     * @return legal_name - 商户法人代表
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置商户法人代表
     *
     * @param legalName 商户法人代表
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取商户注册号
     *
     * @return mer_reg_no - 商户注册号
     */
    public String getMerRegNo() {
        return merRegNo;
    }

    /**
     * 设置商户注册号
     *
     * @param merRegNo 商户注册号
     */
    public void setMerRegNo(String merRegNo) {
        this.merRegNo = merRegNo;
    }

    /**
     * 获取企业类型
     *
     * @return company_type - 企业类型
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置企业类型
     *
     * @param companyType 企业类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取商户地址
     *
     * @return mer_addre - 商户地址
     */
    public String getMerAddre() {
        return merAddre;
    }

    /**
     * 设置商户地址
     *
     * @param merAddre 商户地址
     */
    public void setMerAddre(String merAddre) {
        this.merAddre = merAddre;
    }

    /**
     * 获取商户联系人
     *
     * @return mer_contact - 商户联系人
     */
    public String getMerContact() {
        return merContact;
    }

    /**
     * 设置商户联系人
     *
     * @param merContact 商户联系人
     */
    public void setMerContact(String merContact) {
        this.merContact = merContact;
    }

    /**
     * 获取商户联系人电话
     *
     * @return mer_contact_phone - 商户联系人电话
     */
    public String getMerContactPhone() {
        return merContactPhone;
    }

    /**
     * 设置商户联系人电话
     *
     * @param merContactPhone 商户联系人电话
     */
    public void setMerContactPhone(String merContactPhone) {
        this.merContactPhone = merContactPhone;
    }

    /**
     * 获取商户状态
     *
     * @return mer_status - 商户状态
     */
    public String getMerStatus() {
        return merStatus;
    }

    /**
     * 设置商户状态
     *
     * @param merStatus 商户状态
     */
    public void setMerStatus(String merStatus) {
        this.merStatus = merStatus;
    }

    /**
     * 获取所属经销商
     *
     * @return distributor_no - 所属经销商
     */
    public String getDistributorNo() {
        return distributorNo;
    }

    /**
     * 设置所属经销商
     *
     * @param distributorNo 所属经销商
     */
    public void setDistributorNo(String distributorNo) {
        this.distributorNo = distributorNo;
    }

    /**
     * 获取新增时间
     *
     * @return insert_date - 新增时间
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 设置新增时间
     *
     * @param insertDate 新增时间
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}