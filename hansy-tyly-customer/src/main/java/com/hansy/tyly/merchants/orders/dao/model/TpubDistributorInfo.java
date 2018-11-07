package com.hansy.tyly.merchants.orders.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pub_distributor_info")
public class TpubDistributorInfo {
    /**
     * 经销商编号
     */
    @Id
    @Column(name = "distributor_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String distributorNo;

    /**
     * 经销商名称
     */
    @Column(name = "distributor_name")
    private String distributorName;

    /**
     * 法人代表
     */
    @Column(name = "legal_name")
    private String legalName;

    /**
     * 经销商注册号
     */
    @Column(name = "distributor_reg_no")
    private String distributorRegNo;

    /**
     * 企业类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 经销商地址
     */
    @Column(name = "distributor_addre")
    private String distributorAddre;

    /**
     * 经销商联系人
     */
    @Column(name = "distributor_contact")
    private String distributorContact;

    /**
     * 经销商联系人电话
     */
    @Column(name = "distributor_contact_phone")
    private String distributorContactPhone;

    /**
     * 经销商状态
     */
    @Column(name = "distributor_status")
    private String distributorStatus;

    /**
     * 新增是时间
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 所属销售
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * 获取经销商编号
     *
     * @return distributor_no - 经销商编号
     */
    public String getDistributorNo() {
        return distributorNo;
    }

    /**
     * 设置经销商编号
     *
     * @param distributorNo 经销商编号
     */
    public void setDistributorNo(String distributorNo) {
        this.distributorNo = distributorNo;
    }

    /**
     * 获取经销商名称
     *
     * @return distributor_name - 经销商名称
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * 设置经销商名称
     *
     * @param distributorName 经销商名称
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * 获取法人代表
     *
     * @return legal_name - 法人代表
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置法人代表
     *
     * @param legalName 法人代表
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取经销商注册号
     *
     * @return distributor_reg_no - 经销商注册号
     */
    public String getDistributorRegNo() {
        return distributorRegNo;
    }

    /**
     * 设置经销商注册号
     *
     * @param distributorRegNo 经销商注册号
     */
    public void setDistributorRegNo(String distributorRegNo) {
        this.distributorRegNo = distributorRegNo;
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
     * 获取经销商地址
     *
     * @return distributor_addre - 经销商地址
     */
    public String getDistributorAddre() {
        return distributorAddre;
    }

    /**
     * 设置经销商地址
     *
     * @param distributorAddre 经销商地址
     */
    public void setDistributorAddre(String distributorAddre) {
        this.distributorAddre = distributorAddre;
    }

    /**
     * 获取经销商联系人
     *
     * @return distributor_contact - 经销商联系人
     */
    public String getDistributorContact() {
        return distributorContact;
    }

    /**
     * 设置经销商联系人
     *
     * @param distributorContact 经销商联系人
     */
    public void setDistributorContact(String distributorContact) {
        this.distributorContact = distributorContact;
    }

    /**
     * 获取经销商联系人电话
     *
     * @return distributor_contact_phone - 经销商联系人电话
     */
    public String getDistributorContactPhone() {
        return distributorContactPhone;
    }

    /**
     * 设置经销商联系人电话
     *
     * @param distributorContactPhone 经销商联系人电话
     */
    public void setDistributorContactPhone(String distributorContactPhone) {
        this.distributorContactPhone = distributorContactPhone;
    }

    /**
     * 获取经销商状态
     *
     * @return distributor_status - 经销商状态
     */
    public String getDistributorStatus() {
        return distributorStatus;
    }

    /**
     * 设置经销商状态
     *
     * @param distributorStatus 经销商状态
     */
    public void setDistributorStatus(String distributorStatus) {
        this.distributorStatus = distributorStatus;
    }

    /**
     * 获取新增是时间
     *
     * @return insert_date - 新增是时间
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 设置新增是时间
     *
     * @param insertDate 新增是时间
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取所属销售
     *
     * @return staff_no - 所属销售
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置所属销售
     *
     * @param staffNo 所属销售
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}