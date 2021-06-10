package io.ngss.taksitle.report.bank.database.entity;

import io.ngss.taksitle.report.bank.ononay.model.PreApplicationResponseCode;
import io.ngss.taksitle.report.bank.ononay.model.ProblemReason;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "pre_application_request")
public class PreApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "pre_application_response_code")
    private PreApplicationResponseCode preApplicationResponseCode;

    @ManyToOne
    private Bank bank;

    private Double amount;

    @Column(name = "bank_parameters")
    private String bankParameters;

    @Column(name = "bank_response")
    private String bankResponse;

    @ManyToOne
    private Customer customer;

    @Column(name = "category_id")
    private int categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "problem_reason")
    private ProblemReason problemReason; //KRED - 350

    @Column(name = "kkb_score")
    private String kkbScore;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "banktransactionstate")
//    private BankTransactionState bankTransactionState;


    public PreApplicationRequest(Transaction transaction, PreApplicationResponseCode preApplicationResponseCode, Bank bank, Double amount, Customer customer, int categoryId, ProblemReason problemReason, String bankParameters, String bankResponse, String kkbScore) {
        this.transaction = transaction;
        this.preApplicationResponseCode = preApplicationResponseCode;
        this.bank = bank;
        this.amount = amount;
        this.customer = customer;
        this.categoryId = categoryId;
        this.problemReason = problemReason;
        this.bankParameters = bankParameters;
        this.bankResponse = bankResponse;
        this.kkbScore = kkbScore;
    }

    public PreApplicationRequest(){}

    public ProblemReason getProblemReason() {
        return problemReason;
    }

    public void setProblemReason(ProblemReason problemReason) {
        this.problemReason = problemReason;
    }

    public Long getId() {
        return id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public PreApplicationResponseCode getPreApplicationResponseCode() {
        return preApplicationResponseCode;
    }

    public void setPreApplicationResponseCode(PreApplicationResponseCode preApplicationResponseCode) {
        this.preApplicationResponseCode = preApplicationResponseCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBankParameters() {
        return bankParameters;
    }

    public void setBankParameters(String bankParameters) {
        this.bankParameters = bankParameters;
    }

    public String getBankResponse() {
        return bankResponse;
    }

    public void setBankResponse(String bankResponse) {
        this.bankResponse = bankResponse;
    }

    public String getKkbScore() {
        return kkbScore;
    }

    public void setKkbScore(String kkbScore) {
        this.kkbScore = kkbScore;
    }

//    public BankTransactionState getBankTransactionState() {
//        return bankTransactionState;
//    }
//
//    public void setBankTransactionState(BankTransactionState bankTransactionState) {
//        this.bankTransactionState = bankTransactionState;
//    }

    @Override
    public String toString() {
        return "PreApplicationRequest{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", preApplicationResponseCode=" + preApplicationResponseCode +
                ", bank=" + bank +
                ", amount=" + amount +
                ", bankParameters='" + bankParameters + '\'' +
                ", bankResponse='" + bankResponse + '\'' +
                ", customer=" + customer +
                ", categoryId=" + categoryId +
                ", problemReason=" + problemReason +
                ", kkbScore='" + kkbScore + '\'' +
//                ", bankTransactionState='" + bankTransactionState + '\'' +
                '}';
    }
}
