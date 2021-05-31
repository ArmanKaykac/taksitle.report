package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class TransactionLastStateCountModel extends ReportModel {

    private String transactionState;
    private Integer stateQuantity;
    private Integer incomeDocumentContainsQuantity;
    private String acceptedRate;
    private String incomeDocumentRate;
    private String useOfLoanRate;
    private String source;
    private String portfolioManager;
    private Long code;
    private String city;


    public TransactionLastStateCountModel(String transactionState, Integer stateQuantity) {
        this.transactionState = transactionState;
        this.stateQuantity = stateQuantity;
    }

    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    public void setStateQuantity(Integer stateQuantity) {
        this.stateQuantity = stateQuantity;
    }

    public Integer getStateQuantity() {
        return stateQuantity;
    }

    public void setIncomeDocumentContainsQuantity(Integer incomeDocumentContainsQuantity) {
        this.incomeDocumentContainsQuantity = incomeDocumentContainsQuantity;
    }

    public Integer getIncomeDocumentContainsQuantity() {
        return incomeDocumentContainsQuantity;
    }

    public void setAcceptedRate(String acceptedRate) {
        this.acceptedRate = acceptedRate;
    }

    public String getAcceptedRate() {
        return acceptedRate;
    }

    public void setIncomeDocumentRate(String incomeDocumentRate) {
        this.incomeDocumentRate = incomeDocumentRate;
    }

    public String getIncomeDocumentRate() {
        return incomeDocumentRate;
    }

    public void setUseOfLoanRate(String useOfLoanRate) {
        this.useOfLoanRate = useOfLoanRate;
    }

    public String getUseOfLoanRate() {
        return useOfLoanRate;
    }

    public Long getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getPortfolioManager() {
        return portfolioManager;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPortfolioManager(String portfolioManager) {
        this.portfolioManager = portfolioManager;
    }
}
