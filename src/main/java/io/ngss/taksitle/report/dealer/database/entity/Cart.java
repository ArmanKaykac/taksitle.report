package io.ngss.taksitle.report.dealer.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.enums.WhomToBuy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private Double total;

    @Column(name = "hash")
    private Integer hash;

    @ManyToOne
    @JoinColumn(name = "dealerId")
    private Dealer dealer;

    @OneToOne
    @JoinColumn(name = "loanrequest_id")
    private LoanRequest loanRequest;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference(value = "cartProduct")
    private List<CartProduct> cartProducts;

    @Column(name = "whomToBuy")
    private WhomToBuy whomToBuy;

    public WhomToBuy getWhomToBuy() {
        return whomToBuy;
    }

    public void setWhomToBuy(WhomToBuy whomToBuy) {
        this.whomToBuy = whomToBuy;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonIgnore
    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    @JsonIgnore
    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @JsonIgnore
    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void calculateTotal() {

        this.total = 0D;

        for(CartProduct cartProduct:cartProducts){
            this.total = total + cartProduct.getPrice();
        }

    }
}