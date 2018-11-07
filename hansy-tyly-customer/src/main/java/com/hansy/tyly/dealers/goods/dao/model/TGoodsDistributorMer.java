package com.hansy.tyly.dealers.goods.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_distributor_mer")
public class TGoodsDistributorMer {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableKey;

    /**
     * 商品编号
     */
    @Column(name = "goods_no")
    private String goodsNo;

    /**
     * 经销商编号
     */
    @Column(name = "distributor_no")
    private String distributorNo;

    /**
     * 商户编号
     */
    @Column(name = "mer_no")
    private String merNo;

    /**
     * 商品价格
     */
    @Column(name = "goods_amt")
    private BigDecimal goodsAmt;

    /**
     * 上架时间
     */
    @Column(name = "on_date")
    private Date onDate;

    /**
     * 下架时间
     */
    @Column(name = "down_date")
    private Date downDate;

    /**
     * 上架状态
     */
    @Column(name = "on_status")
    private String onStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 获取主键
     *
     * @return table_key - 主键
     */
    public Integer getTableKey() {
        return tableKey;
    }

    /**
     * 设置主键
     *
     * @param tableKey 主键
     */
    public void setTableKey(Integer tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 获取商品编号
     *
     * @return goods_no - 商品编号
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * 设置商品编号
     *
     * @param goodsNo 商品编号
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
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
     * 获取商品价格
     *
     * @return goods_amt - 商品价格
     */
    public BigDecimal getGoodsAmt() {
        return goodsAmt;
    }

    /**
     * 设置商品价格
     *
     * @param goodsAmt 商品价格
     */
    public void setGoodsAmt(BigDecimal goodsAmt) {
        this.goodsAmt = goodsAmt;
    }

    /**
     * 获取上架时间
     *
     * @return on_date - 上架时间
     */
    public Date getOnDate() {
        return onDate;
    }

    /**
     * 设置上架时间
     *
     * @param onDate 上架时间
     */
    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    /**
     * 获取下架时间
     *
     * @return down_date - 下架时间
     */
    public Date getDownDate() {
        return downDate;
    }

    /**
     * 设置下架时间
     *
     * @param downDate 下架时间
     */
    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    /**
     * 获取上架状态
     *
     * @return on_status - 上架状态
     */
    public String getOnStatus() {
        return onStatus;
    }

    /**
     * 设置上架状态
     *
     * @param onStatus 上架状态
     */
    public void setOnStatus(String onStatus) {
        this.onStatus = onStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}