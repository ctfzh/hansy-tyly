package com.hansy.tyly.dealers.goods.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_bus_shopp_cart")
@ApiModel(value="购物车",description="购物车对象")
public class TBusShoppCart {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="主键",name="tableKey",example="12")
    private Integer tableKey;

    /**
     * 用户编号
     */
    @Column(name = "cust_no")
    @ApiModelProperty(value="用户编号",name="custNo",example="20位")
    private String custNo;

    /**
     * 用户名称
     */
    @Column(name = "cust_name")
    @ApiModelProperty(value="用户名称",name="custName",example="20位")
    private String custName;

    /**
     * 用户类型：经销商。商户
     */
    @Column(name = "cust_type")
    @ApiModelProperty(value="用户类型",name="custType",example="经销商。商户类型码值")
    private String custType;

    /**
     * 商品编号
     */
    @Column(name = "goods_no")
    @ApiModelProperty(value="商品编号",name="goodsNo",example="20位")
    private String goodsNo;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    @ApiModelProperty(value="商品名称",name="goodsName",example="撒大声地")
    private String goodsName;

    /**
     * 购买类型
     */
    @Column(name = "buy_type")
    @ApiModelProperty(value="购买类型",name="buyType",example="暂时不用")
    private String buyType;

    /**
     * 购买数量
     */
    @Column(name = "buy_num")
    @ApiModelProperty(value="购买数量",name="buyNum",example="100")
    private Integer buyNum;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    @ApiModelProperty(value="商品价格",name="goodsPrice",example="100")
    private BigDecimal goodsPrice;

    /**
     * 新增时间
     */
    @Column(name = "insert_date")
    @ApiModelProperty(value="新增时间",name="insertDate",example="20位")
    private Date insertDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    @ApiModelProperty(value="更新时间",name="updateDate",example="20位")
    private Date updateDate;

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
     * 获取用户编号
     *
     * @return cust_no - 用户编号
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * 设置用户编号
     *
     * @param custNo 用户编号
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * 获取用户名称
     *
     * @return cust_name - 用户名称
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置用户名称
     *
     * @param custName 用户名称
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * 获取用户类型：经销商。商户
     *
     * @return cust_type - 用户类型：经销商。商户
     */
    public String getCustType() {
        return custType;
    }

    /**
     * 设置用户类型：经销商。商户
     *
     * @param custType 用户类型：经销商。商户
     */
    public void setCustType(String custType) {
        this.custType = custType;
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
     * 获取购买类型
     *
     * @return buy_type - 购买类型
     */
    public String getBuyType() {
        return buyType;
    }

    /**
     * 设置购买类型
     *
     * @param buyType 购买类型
     */
    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    /**
     * 获取购买数量
     *
     * @return buy_num - 购买数量
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * 设置购买数量
     *
     * @param buyNum 购买数量
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * 获取商品价格
     *
     * @return goods_price - 商品价格
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
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

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}