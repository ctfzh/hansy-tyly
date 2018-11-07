package com.hansy.tyly.customer.prodMgr.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_busi_prod_lib")
public class TBusiProdLib {
    /**
     * 主键
     */
    @Id
    @Column(name = "PROD_LIB_ID")
    private String prodLibId;

    /**
     * 附件路径
     */
    @Column(name = "PROD_LIB_PATH")
    private String prodLibPath;

    /**
     * 附件名称
     */
    @Column(name = "PROD_LIB_FILE")
    private String prodLibFile;

    /**
     * 产品主键
     */
    @Column(name = "PROD_ID")
    private String prodId;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 插入时间
     */
    @Column(name = "INSERT_TIME")
    private Date insertTime;

    /**
     * 插入用户ID
     */
    @Column(name = "INSERT_USER_ID")
    private String insertUserId;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return PROD_LIB_ID - 主键
     */
    public String getProdLibId() {
        return prodLibId;
    }

    /**
     * 设置主键
     *
     * @param prodLibId 主键
     */
    public void setProdLibId(String prodLibId) {
        this.prodLibId = prodLibId;
    }

    /**
     * 获取附件路径
     *
     * @return PROD_LIB_PATH - 附件路径
     */
    public String getProdLibPath() {
        return prodLibPath;
    }

    /**
     * 设置附件路径
     *
     * @param prodLibPath 附件路径
     */
    public void setProdLibPath(String prodLibPath) {
        this.prodLibPath = prodLibPath;
    }

    /**
     * 获取附件名称
     *
     * @return PROD_LIB_FILE - 附件名称
     */
    public String getProdLibFile() {
        return prodLibFile;
    }

    /**
     * 设置附件名称
     *
     * @param prodLibFile 附件名称
     */
    public void setProdLibFile(String prodLibFile) {
        this.prodLibFile = prodLibFile;
    }

    /**
     * 获取产品主键
     *
     * @return PROD_ID - 产品主键
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置产品主键
     *
     * @param prodId 产品主键
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取插入时间
     *
     * @return INSERT_TIME - 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置插入时间
     *
     * @param insertTime 插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取插入用户ID
     *
     * @return INSERT_USER_ID - 插入用户ID
     */
    public String getInsertUserId() {
        return insertUserId;
    }

    /**
     * 设置插入用户ID
     *
     * @param insertUserId 插入用户ID
     */
    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}