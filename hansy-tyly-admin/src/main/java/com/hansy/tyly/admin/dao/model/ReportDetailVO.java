package com.hansy.tyly.admin.dao.model;

import java.util.List;

public class ReportDetailVO {
    private TReportConf reportConf;
    private List<TReportTableConfDtl> reportTableConfDtlList;

    public ReportDetailVO() {
    }

    public TReportConf getReportConf() {
        return reportConf;
    }

    public void setReportConf(TReportConf reportConf) {
        this.reportConf = reportConf;
    }

    public List<TReportTableConfDtl> getReportTableConfDtlList() {
        return reportTableConfDtlList;
    }

    public void setReportTableConfDtlList(List<TReportTableConfDtl> reportTableConfDtlList) {
        this.reportTableConfDtlList = reportTableConfDtlList;
    }
}
