package com.hansy.tyly.admin.utils.enumClass;

public enum CodeEnum {
    userType("goodsAscription","00001001"),
    goodsStaus("goodsStatus","00001001"),
    goodsTypeStatus("goodsTypeStatus","00001001"),
    goodsFileStatus("goodsFileStatus","00001001"),
    goodsOnStatus("goodsOnStatus","00001001"),
    editOrderStatus("editOrderStatus","00001001"), //订单状态
    apprStatus("apprStatus","00001001"),  //退款审批状态
    payType("payType","00001001"),  //支付方式
    orderType("orderType","00001001"),  //支付方式

    numOfPic("numOfPic","00001001"),  //支付方式
    fileType("fileType","00001001"),  //支付方式
    newsType("newsType","00001001"),
    receivingDate("receivingDate","00001001"); //自动收货时间

    private String code;
    private String status;

    

    CodeEnum(String code, String status) {
        this.code=code;
        this.status=status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public static String getStatusByCode(String code) {
        for (CodeEnum codeEnum:CodeEnum.values()) {
            if(codeEnum.code.equals(code)){
                return codeEnum.status;
            }

        }
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
