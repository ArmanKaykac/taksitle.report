package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.entity.Cart;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "loan_request")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_reference_no")
    private Long requestReferenceNO;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "financial_type")
    private LoanCategory loanCategory;

    @Column(name = "requested_loan_amount")
    private Double requestedLoanAmount;

    @Column(name = "term")
    private Integer term;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "loanRequestCart")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "customerLoanRequest")
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name="totalRequests")
    private Integer totalRequests;

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestReferenceNO() {
        return requestReferenceNO;
    }

    public void setRequestReferenceNO(Long requestReferenceNO) {
        this.requestReferenceNO = requestReferenceNO;
    }

    public LoanCategory getLoanCategory() {
        return loanCategory;
    }

    public void setLoanCategory(LoanCategory loanCategory) {
        this.loanCategory = loanCategory;
    }

    public Double getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(Double requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
