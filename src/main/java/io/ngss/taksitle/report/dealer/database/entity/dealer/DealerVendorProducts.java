package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import io.ngss.taksitle.report.shared.database.entity.Product;

import javax.persistence.*;
import java.util.Set;

@Entity(name="DealerVendorProducts")
@Table(name = "dealer_vendor_products")
public class DealerVendorProducts {

    @EmbeddedId
    private DealerIdVendorId dealerIdVendorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendorId")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dealerId")
    private Dealer dealer;

    @OneToMany(mappedBy = "dealerVendorRelations")
    private Set<Product> productList;

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

    private DealerVendorProducts(){}

    public DealerVendorProducts(Vendor vendor, Dealer dealer) {
        this.vendor = vendor;
        this.dealer = dealer;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }
}
