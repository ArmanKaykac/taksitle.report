package io.ngss.taksitle.report.dealer.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.BankCommissionDefinitions;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "business_conditions")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BusinessConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "integration_cost")
    private Double integrationCost;

    @Column(name = "new_credit_product_integration")
    private Double newCreditProductIntegration;

    @OneToMany(
            mappedBy = "businessConditions"
    )
    private Set<BankCommissionDefinitions> bankCommissionDefinitions;

    @Column(name = "platform_license_fee")
    private Double platformLicenseFee;

    @Column(name = "new_customer_commission")
    private Double newCustomerCommission;

    @Column(name = "credit_utilization_commission")
    private Double creditUtilizationCommission;

    @Column(name = "maintenance_cost")
    private Double maintenanceCost;

    @Column(name = "process_commission")
    private Double processCommission;

    @Column(name = "license_fee")
    private Double license_fee;

    @Column(name = "statue")
    private String statue;

    @OneToOne(mappedBy = "businessConditions")
    @JsonBackReference
    private Bank bank;

    @OneToOne(mappedBy = "businessConditions")
    private Dealer dealer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIntegrationCost() {
        return integrationCost;
    }

    public void setIntegrationCost(Double integrationCost) {
        this.integrationCost = integrationCost;
    }

    public Double getNewCreditProductIntegration() {
        return newCreditProductIntegration;
    }

    public void setNewCreditProductIntegration(Double newCreditProductIntegration) {
        this.newCreditProductIntegration = newCreditProductIntegration;
    }

    public Double getPlatformLicenseFee() {
        return platformLicenseFee;
    }

    public void setPlatformLicenseFee(Double platformLicenseFee) {
        this.platformLicenseFee = platformLicenseFee;
    }

    public Double getNewCustomerCommission() {
        return newCustomerCommission;
    }

    public void setNewCustomerCommission(Double newCustomerCommission) {
        this.newCustomerCommission = newCustomerCommission;
    }

    public Double getCreditUtilizationCommission() {
        return creditUtilizationCommission;
    }

    public void setCreditUtilizationCommission(Double creditUtilizationCommission) {
        this.creditUtilizationCommission = creditUtilizationCommission;
    }

    public Double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public Double getProcessCommission() {
        return processCommission;
    }

    public void setProcessCommission(Double processCommission) {
        this.processCommission = processCommission;
    }

    public Double getLicense_fee() {
        return license_fee;
    }

    public void setLicense_fee(Double license_fee) {
        this.license_fee = license_fee;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Set<BankCommissionDefinitions> getBankCommissionDefinitions() {
        return bankCommissionDefinitions;
    }

    public void setBankCommissionDefinitions(Set<BankCommissionDefinitions> bankCommissionDefinitions) {
        this.bankCommissionDefinitions = bankCommissionDefinitions;
    }


}