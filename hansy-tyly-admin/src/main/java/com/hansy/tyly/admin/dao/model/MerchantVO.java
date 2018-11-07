package com.hansy.tyly.admin.dao.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 18383
 * @Date: 2018/8/16 16:28
 * @Description:
 */
public class MerchantVO {
    private String loginId;
    private int pageSize;
    private int pageNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;
    private String mer_no;
    private List<String> mer_nos;
    private String user_no;
    private Boolean isFreeze;
    private String group_no;
    private String group_name;
    private List<String> cust_nos;
    private String distributor_no;
    private String mer_name;
    private String mer_status;
    private Boolean auditStatus;
    private String distributor_name;

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public Boolean getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Boolean auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getMer_name() {
        return mer_name;
    }

    public void setMer_name(String mer_name) {
        this.mer_name = mer_name;
    }

    public String getMer_status() {
        return mer_status;
    }

    public void setMer_status(String mer_status) {
        this.mer_status = mer_status;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getMer_no() {
        return mer_no;
    }

    public void setMer_no(String mer_no) {
        this.mer_no = mer_no;
    }

    public List<String> getMer_nos() {
        return mer_nos;
    }

    public void setMer_nos(List<String> mer_nos) {
        this.mer_nos = mer_nos;
    }

    public String getUser_no() {
        return user_no;
    }

    @Override
    public String toString() {
        return "MerchantVO{" +
                "loginId='" + loginId + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", mer_no='" + mer_no + '\'' +
                ", mer_nos=" + mer_nos +
                ", user_no='" + user_no + '\'' +
                ", isFreeze=" + isFreeze +
                ", group_no='" + group_no + '\'' +
                ", group_name='" + group_name + '\'' +
                ", cust_nos=" + cust_nos +
                ", distributor_no='" + distributor_no + '\'' +
                '}';
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public Boolean getFreeze() {
        return isFreeze;
    }

    public void setFreeze(Boolean freeze) {
        isFreeze = freeze;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<String> getCust_nos() {
        return cust_nos;
    }

    public void setCust_nos(List<String> cust_nos) {
        this.cust_nos = cust_nos;
    }

    public String getDistributor_no() {
        return distributor_no;
    }

    public void setDistributor_no(String distributor_no) {
        this.distributor_no = distributor_no;
    }
}
