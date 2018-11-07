package com.hansy.tyly.dealers.settlement.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fina_distributor_sett")
public class TFinaDistributorSett {
    /**
     * 结算编号
     */
    @Id
    @Column(name = "sett_no")
    private String settNo;

    /**
     * 经销商编号
     */
    @Column(name = "distributor_no")
    private String distributorNo;

    /**
     * 经销商名称
     */
    @Column(name = "distributor_name")
    private String distributorName;

    /**
     * 经销商员工
     */
    @Column(name = "distributor_person")
    private String distributorPerson;

    /**
     * 经销商联系人
     */
    @Column(name = "person_phone")
    private String personPhone;

    /**
     * 结算总金额
     */
    @Column(name = "sett_total_amt")
    private BigDecimal settTotalAmt;

    /**
     * 结算员工
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * 结算日期
     */
    @Column(name = "sett_date")
    private Date settDate;

    /**
     * 获取结算编号
     *
     * @return sett_no - 结算编号
     */
    public String getSettNo() {
        return settNo;
    }

    /**
     * 设置结算编号
     *
     * @param settNo 结算编号
     */
    public void setSettNo(String settNo) {
        this.settNo = settNo;
    }

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
     * 获取经销商员工
     *
     * @return distributor_person - 经销商员工
     */
    public String getDistributorPerson() {
        return distributorPerson;
    }

    /**
     * 设置经销商员工
     *
     * @param distributorPerson 经销商员工
     */
    public void setDistributorPerson(String distributorPerson) {
        this.distributorPerson = distributorPerson;
    }

    /**
     * 获取经销商联系人
     *
     * @return person_phone - 经销商联系人
     */
    public String getPersonPhone() {
        return personPhone;
    }

    /**
     * 设置经销商联系人
     *
     * @param personPhone 经销商联系人
     */
    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    /**
     * 获取结算总金额
     *
     * @return sett_total_amt - 结算总金额
     */
    public BigDecimal getSettTotalAmt() {
        return settTotalAmt;
    }

    /**
     * 设置结算总金额
     *
     * @param settTotalAmt 结算总金额
     */
    public void setSettTotalAmt(BigDecimal settTotalAmt) {
        this.settTotalAmt = settTotalAmt;
    }

    /**
     * 获取结算员工
     *
     * @return staff_no - 结算员工
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置结算员工
     *
     * @param staffNo 结算员工
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取结算日期
     *
     * @return sett_date - 结算日期
     */
    public Date getSettDate() {
        return settDate;
    }

    /**
     * 设置结算日期
     *
     * @param settDate 结算日期
     */
    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }
}