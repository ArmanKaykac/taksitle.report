package io.ngss.taksitle.report.bank.database.entity;

import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;

import javax.persistence.*;

@Entity

@Table(name = "bank_relations")

public class BankRelations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "banned")
    private Boolean banned;



    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;


    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Long getId() {

        return id;

    }



    public void setId(Long id) {

        this.id = id;

    }



    public Boolean getBanned() {

        return banned;

    }



    public void setBanned(Boolean banned) {

        this.banned = banned;

    }



    public Dealer getDealer() {

        return dealer;

    }



    public void setDealer(Dealer dealer) {

        this.dealer = dealer;

    }

    public Bank getBank() {

        return bank;

    }



    public void setBank(Bank bank) {

        this.bank = bank;

    }

}