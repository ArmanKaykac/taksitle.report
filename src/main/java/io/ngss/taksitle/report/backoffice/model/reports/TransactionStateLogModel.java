package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;
import io.ngss.taksitle.report.dealer.TransactionState;

import java.util.Map;

public class TransactionStateLogModel extends ReportModel {

    private Integer transactionToken;
    private String dealerName;
    private String tckno;
    private String requestAmount;
    private String acceptedAmount;
    private String term;
    private Map<String, String> transactionStateDateMap;
    private TransactionState previousState;
    private TransactionState lastState;
    private String customerName;
    private String kkbScore;
    private Double installmentAmount;
    private String brand;
    private String model;
    private Integer modelYear;
    private Integer hullInsuranceAmount;
    private Double salesAmount;

    /*public TransactionStateLogModel(Integer transactionToken, String dealerName, Map<String, String> transactionStateDateMap) {
        this.transactionToken = transactionToken;
        this.dealerName = dealerName;
        this.transactionStateDateMap = transactionStateDateMap;
    }


    public TransactionStateLogModel(Integer transactionToken, String dealerName, String tckno, String requestAmount, String acceptedAmount, String term, Map<String, String> transactionStateDateMap) {
        this.transactionToken = transactionToken;
        this.dealerName = dealerName;
        this.tckno = tckno;
        this.requestAmount = requestAmount;
        this.acceptedAmount = acceptedAmount;
        this.term = term;
        this.transactionStateDateMap = transactionStateDateMap;
    }

    public TransactionStateLogModel(Integer transactionToken, String dealerName, String tckno, String requestAmount, String acceptedAmount, String term, Map<String, String> transactionStateDateMap, String previousState) {
        this.transactionToken = transactionToken;
        this.dealerName = dealerName;
        this.tckno = tckno;
        this.requestAmount = requestAmount;
        this.acceptedAmount = acceptedAmount;
        this.term = term;
        this.transactionStateDateMap = transactionStateDateMap;
        this.previousState = previousState;
    }*/

    public TransactionStateLogModel(Builder builder) {
        this.transactionToken = builder.transactionToken;
        this.dealerName = builder.dealerName;
        this.tckno = builder.tckno;
        this.requestAmount = builder.requestAmount;
        this.acceptedAmount = builder.acceptedAmount;
        this.term = builder.term;
        this.transactionStateDateMap = builder.transactionStateDateMap;
        this.previousState = builder.previousState;
        this.lastState = builder.lastState;
        this.customerName = builder.customerName;
        this.kkbScore = builder.kkbScore;
        this.installmentAmount = builder.installmentAmount;
        this.brand = builder.brand;
        this.model = builder.model;
        this.modelYear = builder.modelYear;
        this.hullInsuranceAmount = builder.hullInsuranceAmount;
        this.salesAmount = builder.salesAmount;
    }

    public Integer getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(Integer transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getTckno() {
        return tckno;
    }

    public void setTckno(String tckno) {
        this.tckno = tckno;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(String acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Map<String, String> getTransactionStateDateMap() {
        return transactionStateDateMap;
    }

    public void setTransactionStateDateMap(Map<String, String> transactionStateDateMap) {
        this.transactionStateDateMap = transactionStateDateMap;
    }

    public TransactionState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(TransactionState previousState) {
        this.previousState = previousState;
    }

    public TransactionState getLastState() {
        return lastState;
    }

    public void setLastState(TransactionState lastState) {
        this.lastState = lastState;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getKkbScore() {
        return kkbScore;
    }

    public void setKkbScore(String kkbScore) {
        this.kkbScore = kkbScore;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getHullInsuranceAmount() {
        return hullInsuranceAmount;
    }

    public void setHullInsuranceAmount(Integer hullInsuranceAmount) {
        this.hullInsuranceAmount = hullInsuranceAmount;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public static class Builder {

        private Integer transactionToken;
        private String dealerName;
        private String tckno;
        private String requestAmount;
        private String acceptedAmount;
        private String term;
        private Map<String, String> transactionStateDateMap;
        private TransactionState previousState;
        private TransactionState lastState;
        private String customerName;
        private String kkbScore;
        private Double installmentAmount;
        private String brand;
        private String model;
        private Integer modelYear;
        private Integer hullInsuranceAmount;
        private Double salesAmount;

        public Builder () {

        }

        public Builder transactionToken (Integer transactionToken) {
            this.transactionToken = transactionToken;
            return this;
        }

        public Builder dealerName (String dealerName) {
            this.dealerName = dealerName;
            return this;
        }

        public Builder tckno (String tckno) {
            this.tckno = tckno;
            return this;
        }

        public Builder requestAmount (String requestAmount) {
            this.requestAmount = requestAmount;
            return this;
        }

        public Builder acceptedAmount (String acceptedAmount) {
            this.acceptedAmount = acceptedAmount;
            return this;
        }

        public Builder term (String term) {
            this.term = term;
            return this;
        }

        public Builder transactionStateDateMap (Map<String, String> transactionStateDateMap) {
            this.transactionStateDateMap = transactionStateDateMap;
            return this;
        }

        public Builder previousState (TransactionState previousState) {
            this.previousState = previousState;
            return this;
        }

        public Builder lastState (TransactionState lastState) {
            this.lastState = lastState;
            return this;
        }

        public Builder customerName (String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder kkbScore (String kkbScore) {
            this.kkbScore = kkbScore;
            return this;
        }

        public Builder installmentAmount (Double installmentAmount) {
            this.installmentAmount = installmentAmount;
            return this;
        }

        public Builder brand (String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model (String model) {
            this.model = model;
            return this;
        }

        public Builder modelYear (Integer modelYear) {
            this.modelYear = modelYear;
            return this;
        }

        public Builder hullInsuranceAmount (Integer hullInsuranceAmount) {
            this.hullInsuranceAmount = hullInsuranceAmount;
            return this;
        }

        public Builder salesAmount (Double salesAmount) {
            this.salesAmount = salesAmount;
            return this;
        }

        public TransactionStateLogModel build () {
            return new TransactionStateLogModel(this);
        }
    }
}
