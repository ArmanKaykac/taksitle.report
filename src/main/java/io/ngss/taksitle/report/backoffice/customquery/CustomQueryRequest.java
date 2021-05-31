package io.ngss.taksitle.report.backoffice.customquery;

import io.ngss.taksitle.report.backoffice.model.TransactionQueryType;
import io.ngss.taksitle.report.dealer.TransactionState;

public class CustomQueryRequest {

    private Long customerId;
    private Long tckNo;
    private String nameSurname;
    private Long dealerId;
    private String dealerName;
    private Long startDate;
    private Long endDate;
    private Long gsmNo;
    private TransactionQueryType transactionQueryType;
    private String transactionState;
    private Integer token;
    private Integer financialTypeId;

    public CustomQueryRequest(Long customerId, Long tckNo, String nameSurname, Long dealerId, String dealerName, Long startDate, Long endDate, Long gsmNo, TransactionQueryType transactionQueryType, TransactionState transactionState, Integer token, Integer financialTypeId) {
        this.token = token;
        this.customerId = customerId;
        this.tckNo = tckNo;
        this.nameSurname = nameSurname;
        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gsmNo = gsmNo;
        this.transactionQueryType=transactionQueryType;
        this.financialTypeId = financialTypeId;

        if (transactionState != null) {
            this.transactionState = transactionState.name();
        }
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public TransactionQueryType getTransactionQueryType() {
        return transactionQueryType;
    }

    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    public void setTransactionQueryType(TransactionQueryType transactionQueryType) {
        this.transactionQueryType = transactionQueryType;
    }

    public Long getGsmNo() {
        return gsmNo;
    }

    public void setGsmNo(Long gsmNo) {
        this.gsmNo = gsmNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTckNo() {
        return tckNo;
    }

    public void setTckNo(Long tckNo) {
        this.tckNo = tckNo;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Integer getFinancialTypeId() {
        return financialTypeId;
    }

    public void setFinancialTypeId(Integer financialTypeId) {
        this.financialTypeId = financialTypeId;
    }
}
