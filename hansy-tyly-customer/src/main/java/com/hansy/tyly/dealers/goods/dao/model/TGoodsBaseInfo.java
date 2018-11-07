package com.hansy.tyly.dealers.goods.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_goods_base_info")
@ApiModel(value="商品基本信息",description="商品基本信息对象")
public class TGoodsBaseInfo {
    /**
     * 商品编号
     */
    @Id
    @Column(name = "goods_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="商品编号",name="goodsNo",example="20位")
    private String goodsNo;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    @ApiModelProperty(value="商品名称",name="goodsName",example="苹果")
    private String goodsName;

    /**
     * 商品归属
     */
    @Column(name = "goods_ascription")
    @ApiModelProperty(value="商品归属",name="goodsAscription",example="平台经销商码值")
    private String goodsAscription;

    /**
     * 市场价格
     */
    @Column(name = "market_amt")
    @ApiModelProperty(value="市场价格",name="marketAmt",example="1000")
    private BigDecimal marketAmt;

    /**
     * 零售价格
     */
    @Column(name = "retail_amt")
    @ApiModelProperty(value="零售价格",name="retailAmt",example="1000")
    private BigDecimal retailAmt;

    /**
     * 商品状态: 正常   已删除
     */
    @Column(name = "goods_status")
    @ApiModelProperty(value="商品状态",name="goodsStatus",example="商品状态码值")
    private String goodsStatus;

    /**
     * 商品规格
     */
    @Column(name = "goods_spec")
    @ApiModelProperty(value="商品规格",name="goodsSpec",example="长宽高")
    private String goodsSpec;

    /**
     * 商品销量信息
     */
    @Column(name = "goods_sale_num")
    @ApiModelProperty(value="商品销量信息",name="goodsSaleNum",example="1000")
    private Integer goodsSaleNum;

    /**
     * 商品类型
     */
    @Column(name = "type_no")
    @ApiModelProperty(value="商品类型",name="typeNo",example="1000")
    private String typeNo;

    /**
     * 生产信息
     */
    @Column(name = "prod_info")
    @ApiModelProperty(value="生产信息",name="prodInfo",example="1000")
    private String prodInfo;

    /**
     * 上架时间
     */
    @Column(name = "on_date")
    @ApiModelProperty(value="上架时间",name="onDate",example="1000")
    private Date onDate;

    /**
     * 下架时间
     */
    @Column(name = "down_date")
    @ApiModelProperty(value="下架时间",name="downDate",example="1000")
    private Date downDate;

    /**
     * 上架状态
     */
    @Column(name = "on_status")
    @ApiModelProperty(value="上架状态",name="onStatus",example="1000")
    private String onStatus;

    /**
     * 商品封面图文件编号
     */
    @Column(name = "mian_pic_file_no")
    @ApiModelProperty(value="商品封面图文件编号",name="mianPicFileNo",example="1000")
    private Integer mianPicFileNo;

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
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品归属
     *
     * @return goods_ascription - 商品归属
     */
    public String getGoodsAscription() {
        return goodsAscription;
    }

    /**
     * 设置商品归属
     *
     * @param goodsAscription 商品归属
     */
    public void setGoodsAscription(String goodsAscription) {
        this.goodsAscription = goodsAscription;
    }

    /**
     * 获取市场价格
     *
     * @return market_amt - 市场价格
     */
    public BigDecimal getMarketAmt() {
        return marketAmt;
    }

    /**
     * 设置市场价格
     *
     * @param marketAmt 市场价格
     */
    public void setMarketAmt(BigDecimal marketAmt) {
        this.marketAmt = marketAmt;
    }

    /**
     * 获取零售价格
     *
     * @return retail_amt - 零售价格
     */
    public BigDecimal getRetailAmt() {
        return retailAmt;
    }

    /**
     * 设置零售价格
     *
     * @param retailAmt 零售价格
     */
    public void setRetailAmt(BigDecimal retailAmt) {
        this.retailAmt = retailAmt;
    }

    /**
     * 获取商品状态: 正常   已删除
     *
     * @return goods_status - 商品状态: 正常   已删除
     */
    public String getGoodsStatus() {
        return goodsStatus;
    }

    /**
     * 设置商品状态: 正常   已删除
     *
     * @param goodsStatus 商品状态: 正常   已删除
     */
    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * 获取商品规格
     *
     * @return goods_spec - 商品规格
     */
    public String getGoodsSpec() {
        return goodsSpec;
    }

    /**
     * 设置商品规格
     *
     * @param goodsSpec 商品规格
     */
    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    /**
     * 获取商品销量信息
     *
     * @return goods_sale_num - 商品销量信息
     */
    public Integer getGoodsSaleNum() {
        return goodsSaleNum;
    }

    /**
     * 设置商品销量信息
     *
     * @param goodsSaleNum 商品销量信息
     */
    public void setGoodsSaleNum(Integer goodsSaleNum) {
        this.goodsSaleNum = goodsSaleNum;
    }

    /**
     * 获取商品类型
     *
     * @return type_no - 商品类型
     */
    public String getTypeNo() {
        return typeNo;
    }

    /**
     * 设置商品类型
     *
     * @param typeNo 商品类型
     */
    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    /**
     * 获取生产信息
     *
     * @return prod_info - 生产信息
     */
    public String getProdInfo() {
        return prodInfo;
    }

    /**
     * 设置生产信息
     *
     * @param prodInfo 生产信息
     */
    public void setProdInfo(String prodInfo) {
        this.prodInfo = prodInfo;
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
     * 获取商品封面图文件编号
     *
     * @return mian_pic_file_no - 商品封面图文件编号
     */
    public Integer getMianPicFileNo() {
        return mianPicFileNo;
    }

    /**
     * 设置商品封面图文件编号
     *
     * @param mianPicFileNo 商品封面图文件编号
     */
    public void setMianPicFileNo(Integer mianPicFileNo) {
        this.mianPicFileNo = mianPicFileNo;
    }
}