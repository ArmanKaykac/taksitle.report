package io.ngss.taksitle.report.dealer.database.entity;

import io.ngss.taksitle.report.dealer.database.entity.dealer.VendorIdProductCategoryId;
import io.ngss.taksitle.report.dealer.database.enums.BrandValue;
import io.ngss.taksitle.report.shared.database.entity.ProductCategory;

import javax.persistence.*;

@Entity
@Table(name = "vendor2productcategory")
public class VendorProductCategory {

    @EmbeddedId
    private VendorIdProductCategoryId vendorIdProductCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendor_id")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_category_id")
    private ProductCategory productCategory;

    @Column(name= "brand_value")
    private BrandValue brandValue;

    public VendorProductCategory () {}

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public BrandValue getBrandValue() {
        return brandValue;
    }

    public void setBrandValue(BrandValue brandValue) {
        this.brandValue = brandValue;
    }
}
