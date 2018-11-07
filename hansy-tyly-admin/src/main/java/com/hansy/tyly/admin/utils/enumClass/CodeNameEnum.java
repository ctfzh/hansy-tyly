package com.hansy.tyly.admin.utils.enumClass;

public enum CodeNameEnum {
    platform("平台"),
    Merchant("商户"),
    dealers("经销商"),
    delete("删除"),
    normal("正常"),
    onSale("上架"),
    offShelves("下架"),
    enable("启用"),
    disable("停用"),
    using("使用中"),
    deleted("已删除"),
    unPay("待付款"),
    unFund("待退款"),
    unSend("待发货"),
    unGet("待收货"),
    orderSeccess("已成功"),
    orderFailed("已取消"),
    orderRefund("已退款"),
    waitAppr("待审批"),
    apprSuccess("通过"),
    apprfailed("拒绝"),
    five("五张"),
    receiveDate("自动收货时间"),
    onLinePay("线上支付"),
    outLinePay("线下支付"),
    outLineOrder("线下订单"),
    xitongNews("系统消息"),
    goodsFiles("goodsFiles"),
    orderFiles("orderFiles");

    private String name;
    CodeNameEnum(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
