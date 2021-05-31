package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.BankService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="DealerBankServices")
@Table(name = "dealer_bank_services")
public class DealerBankServices {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dealer dealer;

    @OneToMany
    private Set<BankService> bankServices =new HashSet<>();

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public DealerBankServices(){}

    public DealerBankServices(Bank bank, Dealer dealer) {
        this.bank = bank;
        this.dealer = dealer;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Set<BankService> getBankServices() {
        return bankServices;
    }

    public void setBankServices(Set<BankService> bankServices) {
        this.bankServices = bankServices;
    }

    public Bank getBank() {
        return bank;
    }

    public Long getId() {
        return id;
    }
}
