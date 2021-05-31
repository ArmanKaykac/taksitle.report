package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class IncomeDocumentModel extends ReportModel {

    public Integer transactionToken;
    public String transactionDate;
    public String dealerName;
    public String transactionState;
    public String hasIncomeDocument;
    public String kkbScore;
    public String workingType;
    public String job;
    public Long salary;
    public String companyName;

    public IncomeDocumentModel(Integer transactionToken, String transactionDate, String dealerName, String transactionState, String hasIncomeDocument, String kkbScore, String workingType, String job, Long salary, String companyName) {
        this.transactionToken = transactionToken;
        this.transactionDate = transactionDate;
        this.dealerName = dealerName;
        this.transactionState = transactionState;
        this.hasIncomeDocument = hasIncomeDocument;
        this.kkbScore = kkbScore;
        this.workingType = workingType;
        this.job = job;
        this.salary = salary;
        this.companyName = companyName;
    }
}
