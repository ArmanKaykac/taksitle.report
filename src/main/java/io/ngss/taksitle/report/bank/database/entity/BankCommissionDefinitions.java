package io.ngss.taksitle.report.bank.database.entity;



import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;

import javax.persistence.*;

@Entity(name="BankCommissionDefinitions")
@Table(name = "bank_commision_definitions")
public class BankCommissionDefinitions {

    @EmbeddedId
    private BankIdCommissionId dealerIdVendorId;

    @ManyToOne
    @JoinColumn(name = "business_conditions")
    private BusinessConditions businessConditions;

    private Double commissionFee;

    public BankIdCommissionId getDealerIdVendorId() {
        return dealerIdVendorId;
    }

    public void setDealerIdVendorId(BankIdCommissionId dealerIdVendorId) {
        this.dealerIdVendorId = dealerIdVendorId;
    }

    public Double getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(Double commissionFee) {
        this.commissionFee = commissionFee;
    }

    public BusinessConditions getBusinessConditions() {
        return businessConditions;
    }

    public void setBusinessConditions(BusinessConditions businessConditions) {
        this.businessConditions = businessConditions;
    }
}
