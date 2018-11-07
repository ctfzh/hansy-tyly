package com.hansy.tyly.dealers.goods.dao.model;



import java.math.BigDecimal;
import java.util.List;

public class GoodsPriceDisToMers {
    private List<Integer> tableKeys;
    private String  type;
    private BigDecimal amt;

    public List<Integer> getTableKeys() {
        return tableKeys;
    }

    public void setTableKeys(List<Integer> tableKeys) {
        this.tableKeys = tableKeys;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
