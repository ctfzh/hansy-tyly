package com.hansy.tyly.dealers.goods.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_goods_type")
@ApiModel(value="商品分类信息",description="商品分类信息对象")
public class TGoodsType {
    /**
     * 商品类型编号
     */
    @Id
    @Column(name = "type_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="商品类型编号",name="typeNo",example="主键")
    private String typeNo;

    /**
     * 商品类型名称
     */
    @Column(name = "type_name")
    @ApiModelProperty(value="商品类型名称",name="typeName",example="水果")
    private String typeName;

    /**
     * 商品类型描述
     */
    @Column(name = "type_desc")
    @ApiModelProperty(value="商品类型描述",name="typeDesc",example="水果分类")
    private String typeDesc;

    /**
     * 商品类型状态
     */
    @Column(name = "type_status")
    @ApiModelProperty(value="商品类型状态",name="typeStatus",example="商品类型状态码值 添加不比传")
    private String typeStatus;

    /**
     * 新增时间
     */
    @Column(name = "insert_date")
    @ApiModelProperty(value="新增时间",name="insertDate",example="主键")
    private Date insertDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    @ApiModelProperty(value="修改时间",name="updateDate",example="主键")
    private Date updateDate;

    /**
     * 获取商品类型编号
     *
     * @return type_no - 商品类型编号
     */
    public String getTypeNo() {
        return typeNo;
    }

    /**
     * 设置商品类型编号
     *
     * @param typeNo 商品类型编号
     */
    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    /**
     * 获取商品类型名称
     *
     * @return type_name - 商品类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置商品类型名称
     *
     * @param typeName 商品类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取商品类型描述
     *
     * @return type_desc - 商品类型描述
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * 设置商品类型描述
     *
     * @param typeDesc 商品类型描述
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    /**
     * 获取商品类型状态
     *
     * @return type_status - 商品类型状态
     */
    public String getTypeStatus() {
        return typeStatus;
    }

    /**
     * 设置商品类型状态
     *
     * @param typeStatus 商品类型状态
     */
    public void setTypeStatus(String typeStatus) {
        this.typeStatus = typeStatus;
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
}