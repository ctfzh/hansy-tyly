package com.hansy.tyly.dealers.manage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 18383
 * @Date: 2018/8/17 15:13
 * @Description:
 */
public class DealersVO implements Serializable {
    private int pageNo;
    private int pageSize;
    private String distributor_no;
    private String mer_name;
    private String mer_status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Deprecated
    private Date start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Deprecated
    private Date end_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private String goods_no;
    private boolean isStop;
    private String group_name;
    private List<String> cust_nos;
    private String mer_no;
    private String group_no;
    private String loginId;
    private String sale_name;
    private String sett_no;
    private String order_status;
    private String merchant_name;

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getSett_no() {
        return sett_no;
    }

    public void setSett_no(String sett_no) {
        this.sett_no = sett_no;
    }

    public String getSale_name() {
        return sale_name;
    }

    public void setSale_name(String sale_name) {
        this.sale_name = sale_name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getMer_no() {
        return mer_no;
    }

    public void setMer_no(String mer_no) {
        this.mer_no = mer_no;
    }

    @Override
    public String toString() {
        return "DealersVO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", distributor_no='" + distributor_no + '\'' +
                ", mer_name='" + mer_name + '\'' +
                ", mer_status='" + mer_status + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", goods_no='" + goods_no + '\'' +
                ", isStop=" + isStop +
                ", group_name='" + group_name + '\'' +
                ", cust_nos=" + cust_nos +
                ", mer_no='" + mer_no + '\'' +
                ", group_no='" + group_no + '\'' +
                ", loginId='" + loginId + '\'' +
                ", sale_name='" + sale_name + '\'' +
                ", sett_no='" + sett_no + '\'' +
                ", order_status='" + order_status + '\'' +
                ", merchant_name='" + merchant_name + '\'' +
                '}';
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDistributor_no() {
        return distributor_no;
    }

    public void setDistributor_no(String distributor_no) {
        this.distributor_no = distributor_no;
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

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
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
}
