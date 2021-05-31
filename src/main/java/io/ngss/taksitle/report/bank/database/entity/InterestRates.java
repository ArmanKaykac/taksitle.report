package io.ngss.taksitle.report.bank.database.entity;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interest_rates")
public class InterestRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "financial_type")
    private LoanCategory loanCategory;

    @Column(name = "interest_rate")
    private Double interestRate;

    @ManyToMany
    @JoinTable(
            name = "interestRates2Dealer",
            joinColumns = {@JoinColumn(name = "interest_rates_id")},
            inverseJoinColumns = {@JoinColumn(name = "dealer_id")}
    )
    private Set<Dealer> dealers = new HashSet<>();

    public LoanCategory getLoanCategory() {
        return loanCategory;
    }

    public void setLoanCategory(LoanCategory loanCategory) {
        this.loanCategory = loanCategory;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Set<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(Set<Dealer> dealers) {
        this.dealers = dealers;
    }

    public Long getId() {
        return id;
    }
}