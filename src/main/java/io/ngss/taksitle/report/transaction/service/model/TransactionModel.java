package io.ngss.taksitle.report.transaction.service.model;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.Cart;
import io.ngss.taksitle.report.dealer.database.entity.CartProduct;
import io.ngss.taksitle.report.transaction.database.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TransactionModel {

    private Integer token;
    private TransactionState transactionState;
    private List<LoanOfferModel> loanOfferModels;
    private Long tckno;
    private String name;
    private Long gsmNo;
    private LoanRequestModel loanRequestModel;
    private Long initialDate;
    private Long dealerId;
    private String customerName;
    private Long customerTckNo;
    private String dealerName;
    private String financialType;
    private List<String> bankNames;
    private String identityNo;
    private String plate;
    private Integer yaer;
    private String brand;
    private Integer km;
    private Double approvedAmount;

    public TransactionModel() {
    }

    public TransactionModel(Integer token, TransactionState transactionState, List<LoanOfferModel> loanOfferModels,
                            Long tckno, String name, Long gsmNo, LoanRequestModel loanRequestModel, Long initialDate,
                            Long dealerId,
                            String customerName,
                            Long customerTckNo,
                            String dealerName,
                            String financialType,
                            List<String> bankNames) {
        this.token = token;
        this.transactionState = transactionState;
        this.loanOfferModels = loanOfferModels;
        this.tckno = tckno;
        this.name = name;
        this.gsmNo = gsmNo;
        this.loanRequestModel = loanRequestModel;
        this.initialDate = initialDate;
        this.dealerId = dealerId;
        this.customerName = customerName;
        this.customerTckNo = customerTckNo;
        this.dealerName = dealerName;
        this.financialType = financialType;
        this.bankNames = bankNames;
    }

    public static TransactionModel convertToTransactionModel(Transaction transaction, boolean onlyReturnSelectedLoan) {
        TransactionModel model = new TransactionModel();
        List<String> bankNames = new ArrayList<>();
        transaction.getLoanOffers().stream().forEach(x -> {
            if (x.getBank() != null && x.getOfferStatus() == LoanOfferStatus.OK)
                bankNames.add(x.getBank().getName());
        });

        model = new TransactionModel(transaction.getToken(),
                transaction.getTransactionState(),
                LoanOfferModel.convertToLoanOffers(transaction.getLoanOffers(), true),
                transaction.getCustomer().getTckno(),
                transaction.getCustomer().getCustomerDetails().getName(),
                transaction.getCustomer().getGsmno(),
                LoanRequestModel.convertToLoanOffer(transaction.getLoanRequest()),
                transaction.getInitialDate(), transaction.getDealer().getId(),
                transaction.getCustomer().getCustomerDetails().getName(),
                transaction.getCustomer().getTckno(),
                transaction.getDealer().getName(),
                transaction.getLoanRequest() != null && transaction.getLoanRequest().getLoanCategory() != null ? transaction.getLoanRequest().getLoanCategory().getDisplayName() : "",
                bankNames);

        LoanRequest loanRequest = transaction.getLoanRequest();
        Cart cart = loanRequest != null ? loanRequest.getCart() : null;
        if (cart != null && loanRequest != null
                && loanRequest.getLoanCategory() != null
                && loanRequest.getLoanCategory() != null
                && (loanRequest.getLoanCategory().equals(LoanCategory.TASIT_SIFIR) || loanRequest.getLoanCategory().equals(LoanCategory.TASIT_IKINCI_EL))) {

            CartProduct cartProduct = cart.getCartProducts() != null && cart.getCartProducts().size() > 0 ? cart.getCartProducts().get(0) : null;
            if (cartProduct != null) {

                model.setIdentityNo(cartProduct.getCarIdentityNo());
                model.setPlate(cartProduct.getCarPlate());
                model.setYaer(cartProduct.getModelYear());
                model.setBrand(cartProduct.getProduct() != null ? cartProduct.getProduct().getBrand().getName() : "");
                model.setKm(cartProduct.getKm());
            }
        }

        if (transaction.getLoanOffers() != null) {

            Optional<LoanOffer> lo = transaction.getLoanOffers().stream().filter(l -> l.getOfferStatus().equals(LoanOfferStatus.OK) && l.isSelectedAndNonCanceled()).findFirst();
            if (lo.isPresent()) {
                model.setApprovedAmount(lo.get().getApproverAmount());
            }
        }

        return model;
    }

    public static TransactionModel convertToTransactionModelWOLoanOffer(Transaction transaction, boolean onlyReturnSelectedLoan) {
        return new TransactionModel(transaction.getToken(), transaction.getTransactionState(), null, transaction.getCustomer().getTckno(), transaction.getCustomer().getCustomerDetails().getName(), transaction.getCustomer().getGsmno(), LoanRequestModel.convertToLoanOffer(transaction.getLoanRequest()), transaction.getInitialDate(), transaction.getDealer().getId(), transaction.getCustomer().getCustomerDetails().getName(), transaction.getCustomer().getTckno(), transaction.getDealer().getName(), transaction.getLoanRequest().getLoanCategory().getDisplayName(), null);
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public static List<TransactionModel> convertToTransactionModelList(Collection<Transaction> transactions, boolean onlyReturnSelectedLoan) {

        List<TransactionModel> transactionModels = new ArrayList<>();

        for (Transaction t : transactions) {
            transactionModels.add(convertToTransactionModel(t, onlyReturnSelectedLoan));
        }

        return transactionModels;
    }

    public static List<TransactionModel> convertToTransactionModelListWOLoanOffer(List<Transaction> transactions, boolean onlyReturnSelectedLoan) {

        List<TransactionModel> transactionModels = new ArrayList<>();

        for (Transaction t : transactions) {
            transactionModels.add(convertToTransactionModelWOLoanOffer(t, onlyReturnSelectedLoan));
        }
        return transactionModels;
    }

    public List<LoanOfferModel> getLoanOfferModels() {
        return loanOfferModels;
    }

    public void setLoanOfferModels(List<LoanOfferModel> loanOfferModels) {
        this.loanOfferModels = loanOfferModels;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public Long getTckno() {
        return tckno;
    }

    public void setTckno(Long tckno) {
        this.tckno = tckno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGsmNo() {
        return gsmNo;
    }

    public void setGsmNo(Long gsmNo) {
        this.gsmNo = gsmNo;
    }

    public Long getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Long initialDate) {
        this.initialDate = initialDate;
    }

    public LoanRequestModel getLoanRequestModel() {
        return loanRequestModel;
    }

    public void setLoanRequestModel(LoanRequestModel loanRequestModel) {
        this.loanRequestModel = loanRequestModel;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public List<String> getBankNames() {
        return bankNames;
    }

    public void setBankNames(List<String> bankNames) {
        this.bankNames = bankNames;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getYaer() {
        return yaer;
    }

    public void setYaer(Integer yaer) {
        this.yaer = yaer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }
}
