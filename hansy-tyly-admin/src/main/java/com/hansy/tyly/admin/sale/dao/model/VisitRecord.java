package com.hansy.tyly.admin.sale.dao.model;

import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_pub_visit_record")
public class VisitRecord {
    /**
     * 拜访编号
     */
    @Id
    @Column(name = "visit_no")
    private String visitNo;

    /**
     * 销售编号
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * 客户类型(商户经销商)
     */
    @Column(name = "cust_type")
    private String custType;

    /**
     * 客户编号(商户经销商编号)
     */
    @Column(name = "cust_no")
    private String custNo;

    /**
     * 拜访地址
     */
    @Column(name = "visit_addre")
    private String visitAddre;

    /**
     *  拜访地址的纬度
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     *  拜访地址的经度
     */
    @Column(name = "longitude")
    private String longitude;

    /**
     * 拜访内容
     */
    @Column(name = "visit_content")
    private String visitContent;

    /**
     * 拜访时间
     */
    @Column(name = "buy_type")
    private Date buyType;

    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 拜访附件
     */
    @Transient
    private List<VisitFiles> visitFiles;

    /**
     * 获取拜访编号
     *
     * @return visit_no - 拜访编号
     */
    public String getVisitNo() {
        return visitNo;
    }

    /**
     * 设置拜访编号
     *
     * @param visitNo 拜访编号
     */
    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    /**
     * 获取销售编号
     *
     * @return staff_no - 销售编号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 设置销售编号
     *
     * @param staffNo 销售编号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    /**
     * 获取客户类型(商户经销商)
     *
     * @return cust_type - 客户类型(商户经销商)
     */
    public String getCustType() {
        return custType;
    }

    /**
     * 设置客户类型(商户经销商)
     *
     * @param custType 客户类型(商户经销商)
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * 获取客户编号(商户经销商编号)
     *
     * @return cust_no - 客户编号(商户经销商编号)
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * 设置客户编号(商户经销商编号)
     *
     * @param custNo 客户编号(商户经销商编号)
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * 获取拜访地址
     *
     * @return visit_addre - 拜访地址
     */
    public String getVisitAddre() {
        return visitAddre;
    }

    /**
     * 设置拜访地址
     *
     * @param visitAddre 拜访地址
     */
    public void setVisitAddre(String visitAddre) {
        this.visitAddre = visitAddre;
    }

    /**
     * 获取拜访内容
     *
     * @return visit_content - 拜访内容
     */
    public String getVisitContent() {
        return visitContent;
    }

    /**
     * 设置拜访内容
     *
     * @param visitContent 拜访内容
     */
    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }

    /**
     * 获取拜访时间
     *
     * @return buy_type - 拜访时间
     */
    public Date getBuyType() {
        return buyType;
    }

    /**
     * 设置拜访时间
     *
     * @param buyType 拜访时间
     */
    public void setBuyType(Date buyType) {
        this.buyType = buyType;
    }

    public List<VisitFiles> getVisitFiles() {
        return visitFiles;
    }

    public void setVisitFiles(List<VisitFiles> visitFiles) {
        this.visitFiles = visitFiles;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}