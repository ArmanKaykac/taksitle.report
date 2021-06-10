package io.ngss.taksitle.report.backoffice.model;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.PreApplicationRequest;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;
import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.CartProduct;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.service.model.LoanOfferModel;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionModelBO {
    private Integer token;
    private Long offerDate;
    private String bankName;
    private String customerName;
    private Double totalAmount;
    private Integer term;
    private TransactionState transactionState;
    private Long transactionDate;
    private Long updateDate;
    private String IP;
    private Long dealerId;
    private Long customerTckNo;
    private String dealerName;
    private Double requestedLoanAmount;
    private String kkbScore;
    private Integer hullInsuranceAmount;
    private String financialType;
    private Long paymentDate;
    private String brandName;
    private Integer modelYear;

    public TransactionModelBO(Integer token, Long offerDate, String bankName, String customerName, Double totalAmount, Integer term, TransactionState transactionState, Long transactionDate, Long updateDate, String IP,
                              Long dealerId,
                              Long customerTckNo,
                              String dealerName,
                              Double requestedLoanAmount,
                              String kkbScore,
                              Integer hullInsuranceAmount,
                              String financialType,
                              Long paymentDate,
                              String brandName,
                              Integer modelYear) {
        this.token = token;
        this.offerDate = offerDate;
        this.bankName = bankName;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.term = term;
        this.transactionState = transactionState;
        this.transactionDate = transactionDate;
        this.updateDate = updateDate;
        this.IP = IP;
        this.dealerId = dealerId;
        this.customerName = customerName;
        this.customerTckNo = customerTckNo;
        this.dealerName = dealerName;
        this.requestedLoanAmount = requestedLoanAmount;
        this.kkbScore = kkbScore;
        this.hullInsuranceAmount = hullInsuranceAmount;
        this.financialType = financialType;
        this.paymentDate = paymentDate;
        this.brandName = brandName;
        this.modelYear = modelYear;
    }

    private static TransactionModelBO convertToBOTransactionModel(Transaction transaction, String ip_address_v4) {
        List<LoanOfferModel> loanOfferModelList = LoanOfferModel.convertToLoanOffers(transaction.getLoanOffers(), false);
        Integer hullInsurance = null;
        CustomerDetails customerDetails = transaction.getCustomer().getCustomerDetails();
        List<PreApplicationRequest> preApplicationRequests = transaction.getPreApplicationRequests();
        String kkbScore = null;
        Long transactionPaymentDate = null;
        String brandName = "";
        Integer modelYear = null;
        LoanCategory loanCategory = transaction.getLoanRequest() != null && transaction.getLoanRequest().getLoanCategory() != null ? transaction.getLoanRequest().getLoanCategory() : null;
        String loanCategoryName = loanCategory != null ? transaction.getLoanRequest().getLoanCategory().getDisplayName() : "";
        List<TransactionHistoryLog> historyLogs = transaction.getTransactionHistoryLogs();
        Optional<TransactionHistoryLog> historyLog = historyLogs.stream().filter(x -> x.getTransactionState().equals(TransactionState.KREDI_KULLANDIRILDI)).findFirst();
        if (historyLog.isPresent()) {
            transactionPaymentDate = historyLog.get().getDate();
        }

        if (!preApplicationRequests.isEmpty()) {

            kkbScore = preApplicationRequests.get(0) != null ? preApplicationRequests.get(0).getKkbScore() : null;
        }
        if (loanOfferModelList != null && !loanOfferModelList.isEmpty()) {

            //Gri alan duzenleme
            LoanOfferModel loanOfferModel = loanOfferModelList.get(0);
            Optional loanOfferModel1 = loanOfferModelList.stream().filter(x -> x.getOfferStatus() != LoanOfferStatus.GRAY).findFirst();
            if (loanOfferModel1.isPresent())
                loanOfferModel = (LoanOfferModel) loanOfferModel1.get();

            if (transaction.getLoanRequest().getCart() != null && transaction.getLoanRequest().getCart().getCartProducts() != null
                    && transaction.getLoanRequest().getCart().getCartProducts().get(0) != null) {
                CartProduct cartProduct = transaction.getLoanRequest().getCart().getCartProducts().get(0);
                Boolean isCar = loanCategory.equals(LoanCategory.TASIT_IKINCI_EL) || loanCategory.equals(LoanCategory.TASIT_SIFIR);

                if (isCar) {
                    hullInsurance = cartProduct.getHullInsuranceAmount();
                    brandName = cartProduct.getProduct().getBrand().getName();
                    modelYear = cartProduct.getModelYear();
                }
            }
            return new TransactionModelBO(transaction.getToken(), loanOfferModel.getDate(), loanOfferModel.getBankName(),
                    customerDetails != null ? customerDetails.getName() : "", loanOfferModel.isSelected() ? loanOfferModel.getApprovedAmount() : null,
                    transaction.getLoanRequest().getTerm(), transaction.getTransactionState(), transaction.getInitialDate(), transaction.getInitialDate(),
                    ip_address_v4, transaction.getDealer().getId(), transaction.getCustomer().getTckno(), transaction.getDealer().getName(),
                    transaction.getLoanRequest().getRequestedLoanAmount(), kkbScore == null ? loanOfferModel.getKkbScore() : kkbScore, hullInsurance,
                    loanCategoryName, transactionPaymentDate, brandName, modelYear);
        }
        return new TransactionModelBO(transaction.getToken(), null, null, customerDetails != null ? customerDetails.getName() : "",
                null, null, transaction.getTransactionState(), transaction.getInitialDate(), transaction.getInitialDate(),
                ip_address_v4, transaction.getDealer().getId(), transaction.getCustomer().getTckno(),
                transaction.getDealer().getName(), transaction.getLoanRequest().getRequestedLoanAmount(), kkbScore, hullInsurance,
                loanCategoryName, transactionPaymentDate, brandName, modelYear);
    }

    public static List<TransactionModelBO> convertToBOTransactionModels(List<Pair<Transaction, String>> pairs) {
        List<TransactionModelBO> list = new ArrayList<>();
        for (Pair<Transaction, String> pair : pairs) {
            Transaction transaction = pair.getFirst();
            String ipAddressV4 = pair.getSecond();

            list.add(convertToBOTransactionModel(transaction, ipAddressV4));
        }
        return list;

    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Long getCustomerTckNo() {
        return customerTckNo;
    }

    public void setCustomerTckNo(Long customerTckNo) {
        this.customerTckNo = customerTckNo;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Long getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Long offerDate) {
        this.offerDate = offerDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Double getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(Double requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public Integer getHullInsuranceAmount() {
        return hullInsuranceAmount;
    }

    public void setHullInsuranceAmount(Integer hullInsuranceAmount) {
        this.hullInsuranceAmount = hullInsuranceAmount;
    }

    public String getKkbScore() {
        return kkbScore;
    }

    public void setKkbScore(String kkbScore) {
        this.kkbScore = kkbScore;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }
}
