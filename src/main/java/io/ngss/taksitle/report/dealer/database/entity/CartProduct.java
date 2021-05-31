package io.ngss.taksitle.report.dealer.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ngss.taksitle.report.shared.database.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cartProduct")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "amount")
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne
    @JoinColumn(name = "vendor_id")
    Vendor vendor;

    @ManyToOne
    @JsonBackReference(value = "cartProduct")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "hullInsuranceAmount")
    private Integer hullInsuranceAmount;

    @ElementCollection
    private Map<String,String> additionalParameters;

    public Map<String, String> getAdditionalParameters() {
        return additionalParameters;
    }
    private String oldCarPlate;

    private String carPlate;

    private String carIdentityNo ;

    private int modelYear;

    private Integer km;

    @Column(name = "average_price")
    private Double averagePrice;

    @Column(name = "liquidity")
    private Double liquidity;

    @Column(name = "average_price_bank")
    private Double averagePriceBank;

    @Column(name = "liquidity_bank")
    private Double liquidityBank;

    public void setAdditionalParameters(Map<String, String> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public String getOldCarPlate() {
        return oldCarPlate;
    }

    public void setOldCarPlate(String oldCarPlate) {
        this.oldCarPlate = oldCarPlate;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarIdentityNo() {
        return carIdentityNo;
    }

    public void setCarIdentityNo(String carIdentityNo) {
        this.carIdentityNo = carIdentityNo;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Vendor getVendor() { return vendor; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public void addAdditionalParameters(String key, String value){

        if(additionalParameters==null) additionalParameters = new HashMap<>();
        additionalParameters.put(key,value);

    }

    public Integer getHullInsuranceAmount() {
        return hullInsuranceAmount;
    }

    public void setHullInsuranceAmount(Integer hullInsuranceAmount) {
        this.hullInsuranceAmount = hullInsuranceAmount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Double liquidity) {
        this.liquidity = liquidity;
    }

    public Double getAveragePriceBank() {
        return averagePriceBank;
    }

    public void setAveragePriceBank(Double averagePriceBank) {
        this.averagePriceBank = averagePriceBank;
    }

    public Double getLiquidityBank() {
        return liquidityBank;
    }

    public void setLiquidityBank(Double liquidityBank) {
        this.liquidityBank = liquidityBank;
    }
}
