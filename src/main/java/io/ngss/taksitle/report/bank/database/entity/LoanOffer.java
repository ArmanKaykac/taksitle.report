package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ngss.taksitle.report.bank.database.enums.BankTransactionState;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferProblemReason;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;
import io.ngss.taksitle.report.bank.database.enums.LoanStatus;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "loan_offer")
public class LoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "term")
    private Integer term;

    @Column(name = "agreement")
    private String agreement;

    @Column(name = "expire_date")
    private Long expireDate;

    @Column(name = "expense")
    private Double expense;

    @Column(name = "created_on")
    private Long creation;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "isselected")
    private Boolean isSelected;

    @Column(name = "iscancelled")
    private Boolean cancelled;

    @Column(name = "customership")
    private Boolean customership;

    @Column(name = "bankspecificid")
    private String bankSpecificID;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private LoanStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "offerStatus")
    private LoanOfferStatus offerStatus;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentplan_id")
    private PaymentPlan paymentPlan;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties(value = "loanOffers")
    private Transaction transaction;

    @Column(name = "bankResponse")
    private String bankResponse;

    @Column(name = "approver_Amount")
    private Double approverAmount;

    @Column(name = "bankParameters")
    private String offerParameters;

    @Column(name = "kkbScore")
    private String kkbScore;

    @Column(name = "appEvaluationState")
    private String appEvaluationState;

    //@Column(name = "problemReason")
    @Enumerated(EnumType.STRING)
    @Column(name = "problem_reason")
    private LoanOfferProblemReason problemReason;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "banktransactionstate")
//    private BankTransactionState bankTransactionState;


    public String getOfferParameters() {
        return offerParameters;
    }

    public void setOfferParameters(String offerParameters) {
        this.offerParameters = offerParameters;
    }

    public Double getApproverAmount() {
        return approverAmount;
    }

    public void setApproverAmount(Double approverAmount) {
        this.approverAmount = approverAmount;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public LoanOffer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Long getCreation() {
        return creation;
    }

    public void setCreation(Long creation) {
        this.creation = creation;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public Boolean isSelectedAndNonCanceled() {
        return isSelected && !cancelled;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getCustomership() {
        return customership;
    }

    public void setCustomership(Boolean customership) {
        this.customership = customership;
    }

    public String getBankSpecificID() {
        return bankSpecificID;
    }

    public void setBankSpecificID(String bankSpecificID) {
        this.bankSpecificID = bankSpecificID;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    @JsonIgnore
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public LoanOfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(LoanOfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getKkbScore() {
        return kkbScore;
    }

    public void setKkbScore(String kkbScore) {
        this.kkbScore = kkbScore;
    }

    public String getAppEvaluationState() {
        return appEvaluationState;
    }

    public void setAppEvaluationState(String appEvaluationState) {
        this.appEvaluationState = appEvaluationState;
    }

    public LoanOfferProblemReason getProblemReason() {
        return problemReason;
    }

    public void setProblemReason(LoanOfferProblemReason problemReason) {
        this.problemReason = problemReason;
    }

//    public BankTransactionState getBankTransactionState() {
//        return bankTransactionState;
//    }
//
//    public void setBankTransactionState(BankTransactionState bankTransactionState) {
//        this.bankTransactionState = bankTransactionState;
//    }
}
