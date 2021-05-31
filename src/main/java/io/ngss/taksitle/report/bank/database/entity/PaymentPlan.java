package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_plan")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PaymentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yearly_interested_rate")
    private Double yearlyInterestedRate;

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "allocation_amount")
    private Double allocationAmount;

    @Column(name = "yearly_cost_rate")
    private Double yearlyCostRate;

    @Column(name = "first_installment_date")
    private Long firstInstallmentDate;

    @Column(name = "last_installment_date")
    private Long lastInstallmentDate;

    @Column(name = "maturity_date")
    private Long maturityDate;

    @Column(name = "total_credit_amount")
    private Double totalCreditAmount;

    @Column(name = "credit_allocation_amount")
    private Double creditAllocationAmount;

    @Column(name = "annual_cost_rate")
    private Double annualCostRate;

    @Column(name = "interest_rate_yearly")
    private Double interestRateYearly;

    @Column(name = "interest_amount")
    private Double interestAmount;

    @Column(name = "insurance_amount")
    private Double insuranceAmount;

    @Column(name = "payment_plan_create_date ")
    private Long paymentPlanCreateDate;

    @OneToMany(
            mappedBy = "paymentPlan",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties(value = "paymentPlan")
    private List<PaymentPlanDetails> paymentPlanDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loanoffer_id")
    private LoanOffer loanOffer;

    public PaymentPlan() {
    }

    public PaymentPlan(Double yearlyInterestedRate, Double installmentAmount, Double totalAmount, Double allocationAmount, Double yearlyCostRate, Long firstInstallmentDate, Long lastInstallmentDate, Long maturityDate, Double totalCreditAmount, Double creditAllocationAmount, Double annualCostRate, Double interestRateYearly, Double interestAmount, Double insuranceAmount) {
        this.yearlyInterestedRate = yearlyInterestedRate;
        this.installmentAmount = installmentAmount;
        this.totalAmount = totalAmount;
        this.allocationAmount = allocationAmount;
        this.yearlyCostRate = yearlyCostRate;
        this.firstInstallmentDate = firstInstallmentDate;
        this.lastInstallmentDate = lastInstallmentDate;
        this.maturityDate = maturityDate;
        this.totalCreditAmount = totalCreditAmount;
        this.creditAllocationAmount = creditAllocationAmount;
        this.annualCostRate = annualCostRate;
        this.interestRateYearly = interestRateYearly;
        this.interestAmount = interestAmount;
        this.insuranceAmount = insuranceAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getYearlyInterestedRate() {
        return yearlyInterestedRate;
    }

    public void setYearlyInterestedRate(Double yearlyInterestedRate) {
        this.yearlyInterestedRate = yearlyInterestedRate;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getAllocationAmount() {
        return allocationAmount;
    }

    public void setAllocationAmount(Double allocationAmount) {
        this.allocationAmount = allocationAmount;
    }

    public Double getYearlyCostRate() {
        return yearlyCostRate;
    }

    public void setYearlyCostRate(Double yearlyCostRate) {
        this.yearlyCostRate = yearlyCostRate;
    }

    public Long getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(Long firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public Long getLastInstallmentDate() {
        return lastInstallmentDate;
    }

    public void setLastInstallmentDate(Long lastInstallmentDate) {
        this.lastInstallmentDate = lastInstallmentDate;
    }

    public Long getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Long maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Double getTotalCreditAmount() {
        return totalCreditAmount;
    }

    public void setTotalCreditAmount(Double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    public Double getCreditAllocationAmount() {
        return creditAllocationAmount;
    }

    public void setCreditAllocationAmount(Double creditAllocationAmount) {
        this.creditAllocationAmount = creditAllocationAmount;
    }

    public Double getAnnualCostRate() {
        return annualCostRate;
    }

    public void setAnnualCostRate(Double annualCostRate) {
        this.annualCostRate = annualCostRate;
    }

    public Double getInterestRateYearly() {
        return interestRateYearly;
    }

    public void setInterestRateYearly(Double interestRateYearly) {
        this.interestRateYearly = interestRateYearly;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Long getPaymentPlanCreateDate() {
        return paymentPlanCreateDate;
    }

    public void setPaymentPlanCreateDate(Long paymentPlanCreateDate) {
        this.paymentPlanCreateDate = paymentPlanCreateDate;
    }

    public List<PaymentPlanDetails> getPaymentPlanDetails() {
        return paymentPlanDetails;
    }

    public void setPaymentPlanDetails(List<PaymentPlanDetails> paymentPlanDetails) {
        this.paymentPlanDetails = paymentPlanDetails;
    }

    public LoanOffer getLoanOffer() {
        return loanOffer;
    }

    public void setLoanOffer(LoanOffer loanOffer) {
        this.loanOffer = loanOffer;
    }
}
