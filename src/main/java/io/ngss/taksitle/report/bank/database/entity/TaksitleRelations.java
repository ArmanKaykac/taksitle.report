package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ngss.taksitle.report.bank.database.enums.CreditType;
import io.ngss.taksitle.report.bank.database.enums.SBUPeriod;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "taksitle_relations")
public class TaksitleRelations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass= CreditType.class)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "dealer_creditTypes")
    @JsonIgnore // arman
    @Column(name="creditTypes")
    Set<CreditType> creditTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dealer dealer;

    @Column(name="hasAgreement")
    private Boolean hasAgreement;

    @Column(name="maintenanceFee")
    private Double maintenanceFee;

    @Enumerated(EnumType.STRING)
    @Column(name="SBUPeriod")
    private SBUPeriod sbuPeriod;

    @Column(name="eCommerceTransactionFee")
    private Double eCommerceTransactionFee;

    @Column(name="dealerApplicationTransactionFee")
    private Double dealerApplicationTransactionFee;

    public Set<CreditType> getCreditTypes() {
        return creditTypes;
    }

    public void setCreditTypes(Set<CreditType> creditTypes) {
        this.creditTypes = creditTypes;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public boolean isHasAgreement() {
        return hasAgreement;
    }

    public void setHasAgreement(boolean hasAgreement) {
        this.hasAgreement = hasAgreement;
    }

    public Double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(Double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    public SBUPeriod getSbuPeriod() {
        return sbuPeriod;
    }

    public void setSbuPeriod(SBUPeriod sbuPeriod) {
        this.sbuPeriod = sbuPeriod;
    }

    public Double geteCommerceTransactionFee() {
        return eCommerceTransactionFee;
    }

    public void seteCommerceTransactionFee(Double eCommerceTransactionFee) {
        this.eCommerceTransactionFee = eCommerceTransactionFee;
    }

    public Double getDealerApplicationTransactionFee() {
        return dealerApplicationTransactionFee;
    }

    public void setDealerApplicationTransactionFee(Double dealerApplicationTransactionFee) {
        this.dealerApplicationTransactionFee = dealerApplicationTransactionFee;
    }

    public Long getId() {
        return id;
    }


}