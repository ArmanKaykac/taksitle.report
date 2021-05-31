package io.ngss.taksitle.report.dealer.database.entity;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorRelations;
import io.ngss.taksitle.report.dealer.database.enums.BrandValue;
import io.ngss.taksitle.report.dealer.database.enums.SalesStatusInThreeYears;
import io.ngss.taksitle.report.shared.database.entity.Product;
import io.ngss.taksitle.report.shared.database.entity.ProductCategory;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_name")
    private String name;

    @Column(name = "market_share")
    private Integer marketShare;

    @Column(name =  "xpInSector")
    private Integer experienceInSector;

    @Column(name = "selfOwnedDealerPercentage")
    private Integer selfOwnedDealerPercentage;

    @Column(name = "potentialCustomers")
    private String potentialCustomers;

    @Column(name = "yearlySales")
    private Double yearlySales;

    @Column(name="salesStatusInThreeYears")
    private SalesStatusInThreeYears salesStatusInThreeYears;

    @Column(name = "bondWeight")
    private Integer bondWeight;

    @Column(name = "avgRequestedCapitalForNewDealer")
    private Double avgRequestedCapitalForNewDealer;

    @Column(name = "expectedNPL")
    private Integer expectedNPL;

    @Column(name= "brandValue")
    private BrandValue brandValue;

    @Column(name = "vendorCode")
    private String vendorCode;

    @ManyToMany
    @JoinTable(
            name = "vendor2product",
            joinColumns = {@JoinColumn(name = "vendorid")},
            inverseJoinColumns = {@JoinColumn(name = "productid")}
    )
    private Set<Product> product = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "vendor2productcategory",
            joinColumns = {@JoinColumn(name = "vendor_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_category_id")}
    )
    private Set<ProductCategory> productCategories = new HashSet<>();

    @OneToMany(mappedBy = "vendor")
    private List<DealerVendorRelations> dealerVendorRelations = Collections.emptyList();

    public Vendor() {
    }

    public Vendor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(Integer marketShare) {
        this.marketShare = marketShare;
    }

    public Integer getExperienceInSector() {
        return experienceInSector;
    }

    public void setExperienceInSector(Integer experienceInSector) {
        this.experienceInSector = experienceInSector;
    }

    public Integer getSelfOwnedDealerPercentage() {
        return selfOwnedDealerPercentage;
    }

    public void setSelfOwnedDealerPercentage(Integer selfOwnedDealerPercentage) {
        this.selfOwnedDealerPercentage = selfOwnedDealerPercentage;
    }

    public String getPotentialCustomers() {
        return potentialCustomers;
    }

    public void setPotentialCustomers(String potentialCustomers) {
        this.potentialCustomers = potentialCustomers;
    }

    public Double getYearlySales() {
        return yearlySales;
    }

    public void setYearlySales(Double yearlySales) {
        this.yearlySales = yearlySales;
    }

    public Integer getBondWeight() {
        return bondWeight;
    }

    public void setBondWeight(Integer bondWeight) {
        this.bondWeight = bondWeight;
    }

    public Double getAvgRequestedCapitalForNewDealer() {
        return avgRequestedCapitalForNewDealer;
    }

    public void setAvgRequestedCapitalForNewDealer(Double avgRequestedCapitalForNewDealer) {
        this.avgRequestedCapitalForNewDealer = avgRequestedCapitalForNewDealer;
    }

    public Integer getExpectedNPL() {
        return expectedNPL;
    }

    public void setExpectedNPL(Integer expectedNPL) {
        this.expectedNPL = expectedNPL;
    }

    public BrandValue getBrandValue() {
        return brandValue;
    }

    public void setBrandValue(BrandValue brandValue) {
        this.brandValue = brandValue;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public List<DealerVendorRelations> getDealerVendorRelations() {
        return dealerVendorRelations;
    }

    public void setDealerVendorRelations(List<DealerVendorRelations> dealerVendorRelations) {
        this.dealerVendorRelations = dealerVendorRelations;
    }

    public SalesStatusInThreeYears getSalesStatusInThreeYears() {
        return salesStatusInThreeYears;
    }

    public void setSalesStatusInThreeYears(SalesStatusInThreeYears salesStatusInThreeYears) {
        this.salesStatusInThreeYears = salesStatusInThreeYears;
    }

}
