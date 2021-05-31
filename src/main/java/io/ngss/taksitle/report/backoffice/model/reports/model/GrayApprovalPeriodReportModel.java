package io.ngss.taksitle.report.backoffice.model.reports.model;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class GrayApprovalPeriodReportModel extends ReportModel {

    private String transactionToken;
    private String dealerName;
    private String period;

    public GrayApprovalPeriodReportModel() {
    }

    public GrayApprovalPeriodReportModel(String transactionToken, String dealerName, String period) {
        this.transactionToken = transactionToken;
        this.dealerName = dealerName;
        this.period = period;
    }

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
