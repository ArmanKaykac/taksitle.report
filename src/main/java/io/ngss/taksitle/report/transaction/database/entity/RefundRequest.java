package io.ngss.taksitle.report.transaction.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "refundrequest")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class RefundRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "refundreason")
    private String refundReason;

    @Column(name="refundtype")
    private String refundType;

    @Column(name="refundamount")
    private Double refundAmount;

    @Column(name="isactive")
    private Boolean isActive = true;

    @ManyToOne
    private Transaction transaction;

    @Column(name="refunddate")
    private Long refundDate;

    public Long getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Long refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public RefundRequest(String refundReason, String refundType, Double refundAmount, Boolean isActive, Transaction transaction) {
        this.refundReason = refundReason;
        this.refundType = refundType;
        this.refundAmount = refundAmount;
        this.isActive = isActive;
        this.transaction = transaction;
    }
    public RefundRequest(){}

    public Long getId() {
        return id;
    }
}
