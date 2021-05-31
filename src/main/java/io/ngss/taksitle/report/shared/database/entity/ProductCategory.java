package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorRelations;
import io.ngss.taksitle.report.shared.database.enums.SafetyLevels;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "productCategories")
    private Set<Dealer> dealers;

    @OneToMany
    private List<Product> productList;

    @OneToMany(mappedBy = "productCategory")
    private List<SubCategory> subCategoryList;

    @ManyToOne
    private DealerVendorRelations dealerVendorRelations;

    @ManyToMany(mappedBy = "productCategories")
    private Set<Vendor> vendors;

    @Enumerated(EnumType.STRING)
    private SafetyLevels safetyLevels;

    @Column(name = "code")
    private String code;

    public ProductCategory() {
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public Set<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(Set<Dealer> dealers) {
        this.dealers = dealers;
    }

    public ProductCategory(String name) {
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public DealerVendorRelations getDealerVendorRelations() {
        return dealerVendorRelations;
    }

    public void setDealerVendorRelations(DealerVendorRelations dealerVendorRelations) {
        this.dealerVendorRelations = dealerVendorRelations;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(Set<Vendor> vendors) {
        this.vendors = vendors;
    }

    public SafetyLevels getSafetyLevels() {
        return safetyLevels;
    }

    public void setSafetyLevels(SafetyLevels safetyLevels) {
        this.safetyLevels = safetyLevels;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
