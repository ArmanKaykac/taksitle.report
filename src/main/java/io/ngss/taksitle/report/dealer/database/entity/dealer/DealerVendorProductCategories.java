package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import io.ngss.taksitle.report.shared.database.entity.ProductCategory;

import javax.persistence.*;
import java.util.Set;

@Entity(name="DealerVendorProductCategories")
@Table(name = "dealer_vendor_productCategories")
public class DealerVendorProductCategories {

    @EmbeddedId
    private DealerIdVendorId dealerIdVendorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendorId")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dealerId")
    private Dealer dealer;

    @OneToMany(mappedBy = "dealerVendorRelations")
    private Set<ProductCategory> productCategoryList;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    private DealerVendorProductCategories(){}

    public DealerVendorProductCategories(Vendor vendor, Dealer dealer) {
        this.vendor = vendor;
        this.dealer = dealer;
    }

    public Set<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(Set<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
