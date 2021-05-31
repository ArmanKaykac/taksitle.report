package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorRelations;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory productCategory;

    @ManyToMany(mappedBy = "product")
    private List<Vendor> vendors;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private DealerVendorRelations dealerVendorRelations;

    @ManyToOne
    private SubCategory subCategory;

    @Column(name="uniqueId")
    private Integer uniqueId;

    @Column(name="uniqueId2")
    private Integer uniqueId2;

    @Column(name = "code")
    private String code;

    public Integer getUniqueId2() {
        return uniqueId2;
    }

    public void setUniqueId2(Integer uniqueId2) {
        this.uniqueId2 = uniqueId2;
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @JsonIgnore
    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public DealerVendorRelations getDealerVendorRelations() {
        return dealerVendorRelations;
    }

    public void setDealerVendorRelations(DealerVendorRelations dealerVendorRelations) {
        this.dealerVendorRelations = dealerVendorRelations;
    }
}
