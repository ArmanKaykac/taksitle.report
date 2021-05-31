package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "payment_plan_details")
public class PaymentPlanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installment_date")
    private Long installmentDate;

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "principal_amount_per_installment")
    private Double principalAmountPerInstallment;

    @Column(name = "interest_amount")
    private Double interestAmount;

    @Column(name = "kkdf_amount")
    private Double kkdfAmount;

    @Column(name = "bsmv_amount")
    private Double bsmvAmount;

    @Column(name = "sequance_number")
    private Integer sequanceNumber;

    @Column(name = "remainderMainAmount")
    private Double remainderMainAmount;

    @Column(name = "payment_date")
    private Long paymentDate;

    @ManyToOne
    @JoinColumn(name = "paymentplan_id")
    @JsonIgnoreProperties(value = "paymentPlanDetails")
    private PaymentPlan paymentPlan;

    public PaymentPlanDetails() {
    }

    public PaymentPlanDetails(Long installmentDate, Double installmentAmount, Double principalAmountPerInstallment, Double interestAmount, Double kkdfAmount, Double bsmvAmount, PaymentPlan paymentPlan) {
        this.installmentDate = installmentDate;
        this.installmentAmount = installmentAmount;
        this.principalAmountPerInstallment = principalAmountPerInstallment;
        this.interestAmount = interestAmount;
        this.kkdfAmount = kkdfAmount;
        this.bsmvAmount = bsmvAmount;
        this.paymentPlan = paymentPlan;
    }

    public Double getRemainderMainAmount() {
        return remainderMainAmount;
    }

    public void setRemainderMainAmount(Double remainderMainAmount) {
        this.remainderMainAmount = remainderMainAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(Long installmentDate) {
        this.installmentDate = installmentDate;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Double getPrincipalAmountPerInstallment() {
        return principalAmountPerInstallment;
    }

    public void setPrincipalAmountPerInstallment(Double principalAmountPerInstallment) {
        this.principalAmountPerInstallment = principalAmountPerInstallment;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getKkdfAmount() {
        return kkdfAmount;
    }

    public void setKkdfAmount(Double kkdfAmount) {
        this.kkdfAmount = kkdfAmount;
    }

    public Double getBsmvAmount() {
        return bsmvAmount;
    }

    public void setBsmvAmount(Double bsmvAmount) {
        this.bsmvAmount = bsmvAmount;
    }

    public Integer getSequanceNumber() {
        return sequanceNumber;
    }

    public void setSequanceNumber(Integer sequanceNumber) {
        this.sequanceNumber = sequanceNumber;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
}