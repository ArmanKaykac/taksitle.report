package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class SalesTrackingReportModel extends ReportModel {

    private String dealerName;
    private Long dealerCode;
    private String dealerCreateDate;
    private String financialTypes;
    private Integer uniqueCustomerQuantity;
    private String dealerLastTransactionDate;
    private Integer offerReceivedQuantity;
    private Double acceptedAmount;
    private Integer cancellationFormOfferQuantity;
    private String lastOfferDate;
    private Integer bankSelectedQuantity;
    private Integer cancellationFormBankSelectedQuantity;
    private Integer useOfLoanQuantity;
    private Double useOfLoanAmount;
    private String city;
    private String source;
    private String portfolioManager;

    public SalesTrackingReportModel(String dealerName, Long dealerCode, String dealerCreateDate, String financialTypes) {
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
        this.dealerCreateDate = dealerCreateDate;
        this.financialTypes = financialTypes;
    }

    public SalesTrackingReportModel(Builder builder) {
        this.dealerName = builder.dealerName;
        this.dealerCode = builder.dealerCode;
        this.dealerCreateDate = builder.dealerCreateDate;
        this.financialTypes = builder.financialTypes;
        this.uniqueCustomerQuantity = builder.uniqueCustomerQuantity;
        this.dealerLastTransactionDate = builder.dealerLastTransactionDate;
        this.offerReceivedQuantity = builder.offerReceivedQuantity;
        this.acceptedAmount = builder.acceptedAmount;
        this.cancellationFormOfferQuantity = builder.cancellationFormOfferQuantity;
        this.lastOfferDate = builder.lastOfferDate;
        this.bankSelectedQuantity = builder.bankSelectedQuantity;
        this.cancellationFormBankSelectedQuantity = builder.cancellationFormBankSelectedQuantity;
        this.useOfLoanQuantity = builder.useOfLoanQuantity;
        this.useOfLoanAmount = builder.useOfLoanAmount;
        this.city = builder.city;
        this.source = builder.source;
        this.portfolioManager = builder.portfolioManager;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Long getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(Long dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerCreateDate() {
        return dealerCreateDate;
    }

    public void setDealerCreateDate(String dealerCreateDate) {
        this.dealerCreateDate = dealerCreateDate;
    }

    public String getFinancialTypes() {
        return financialTypes;
    }

    public void setFinancialTypes(String financialTypes) {
        this.financialTypes = financialTypes;
    }

    public Integer getUniqueCustomerQuantity() {
        return uniqueCustomerQuantity;
    }

    public void setUniqueCustomerQuantity(Integer uniqueCustomerQuantity) {
        this.uniqueCustomerQuantity = uniqueCustomerQuantity;
    }

    public String getDealerLastTransactionDate() {
        return dealerLastTransactionDate;
    }

    public void setDealerLastTransactionDate(String dealerLastTransactionDate) {
        this.dealerLastTransactionDate = dealerLastTransactionDate;
    }

    public Integer getOfferReceivedQuantity() {
        return offerReceivedQuantity;
    }

    public void setOfferReceivedQuantity(Integer offerReceivedQuantity) {
        this.offerReceivedQuantity = offerReceivedQuantity;
    }

    public Double getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Double acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public Integer getCancellationFormOfferQuantity() {
        return cancellationFormOfferQuantity;
    }

    public void setCancellationFormOfferQuantity(Integer cancellationFormOfferQuantity) {
        this.cancellationFormOfferQuantity = cancellationFormOfferQuantity;
    }

    public String getLastOfferDate() {
        return lastOfferDate;
    }

    public void setLastOfferDate(String lastOfferDate) {
        this.lastOfferDate = lastOfferDate;
    }

    public Integer getBankSelectedQuantity() {
        return bankSelectedQuantity;
    }

    public void setBankSelectedQuantity(Integer bankSelectedQuantity) {
        this.bankSelectedQuantity = bankSelectedQuantity;
    }

    public Integer getCancellationFormBankSelectedQuantity() {
        return cancellationFormBankSelectedQuantity;
    }

    public void setCancellationFormBankSelectedQuantity(Integer cancellationFormBankSelectedQuantity) {
        this.cancellationFormBankSelectedQuantity = cancellationFormBankSelectedQuantity;
    }

    public Integer getUseOfLoanQuantity() {
        return useOfLoanQuantity;
    }

    public void setUseOfLoanQuantity(Integer useOfLoanQuantity) {
        this.useOfLoanQuantity = useOfLoanQuantity;
    }

    public Double getUseOfLoanAmount() {
        return useOfLoanAmount;
    }

    public void setUseOfLoanAmount(Double useOfLoanAmount) {
        this.useOfLoanAmount = useOfLoanAmount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public void setPortfolioManager(String portfolioManager) {
        this.portfolioManager = portfolioManager;
    }

    public static class Builder {

        private String dealerName;
        private Long dealerCode;
        private String dealerCreateDate;
        private String financialTypes;
        private Integer uniqueCustomerQuantity;
        private String dealerLastTransactionDate;
        private Integer offerReceivedQuantity;
        private Double acceptedAmount;
        private Integer cancellationFormOfferQuantity;
        private String lastOfferDate;
        private Integer bankSelectedQuantity;
        private Integer cancellationFormBankSelectedQuantity;
        private Integer useOfLoanQuantity;
        private Double useOfLoanAmount;
        private String city;
        private String source;
        private String portfolioManager;

        public Builder() {
        }

        public Builder dealerName(String dealerName) {
            this.dealerName = dealerName;
            return this;
        }

        public Builder dealerCode(Long dealerCode) {
            this.dealerCode = dealerCode;
            return this;
        }

        public Builder dealerCreateDate(String dealerCreateDate) {
            this.dealerCreateDate = dealerCreateDate;
            return this;
        }

        public Builder financialTypes(String financialTypes) {
            this.financialTypes = financialTypes;
            return this;
        }

        public Builder uniqueCustomerQuantity(Integer uniqueCustomerQuantity) {
            this.uniqueCustomerQuantity = uniqueCustomerQuantity;
            return this;
        }

        public Builder dealerLastTransactionDate(String dealerLastTransactionDate) {
            this.dealerLastTransactionDate = dealerLastTransactionDate;
            return this;
        }

        public Builder offerReceivedQuantity(Integer offerReceivedQuantity) {
            this.offerReceivedQuantity = offerReceivedQuantity;
            return this;
        }

        public Builder acceptedAmount(Double acceptedAmount) {
            this.acceptedAmount = acceptedAmount;
            return this;
        }

        public Builder cancellationFormOfferQuantity(Integer cancellationFormOfferQuantity) {
            this.cancellationFormOfferQuantity = cancellationFormOfferQuantity;
            return this;
        }

        public Builder lastOfferDate(String lastOfferDate) {
            this.lastOfferDate = lastOfferDate;
            return this;
        }

        public Builder bankSelectedQuantity(Integer bankSelectedQuantity) {
            this.bankSelectedQuantity = bankSelectedQuantity;
            return this;
        }

        public Builder cancellationFormBankSelectedQuantity(Integer cancellationFormBankSelectedQuantity) {
            this.cancellationFormBankSelectedQuantity = cancellationFormBankSelectedQuantity;
            return this;
        }

        public Builder useOfLoanQuantity(Integer useOfLoanQuantity) {
            this.useOfLoanQuantity = useOfLoanQuantity;
            return this;
        }

        public Builder useOfLoanAmount(Double useOfLoanAmount) {
            this.useOfLoanAmount = useOfLoanAmount;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder portfolioManager(String portfolioManager) {
            this.portfolioManager = portfolioManager;
            return this;
        }

        public SalesTrackingReportModel build() {
            return new SalesTrackingReportModel(this);
        }
    }
}
