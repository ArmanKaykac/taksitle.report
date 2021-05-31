package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import io.ngss.taksitle.report.dealer.database.enums.VendorWT;
import io.ngss.taksitle.report.shared.database.entity.Product;
import io.ngss.taksitle.report.shared.database.entity.ProductCategory;

import javax.persistence.*;
import java.util.List;

@Entity(name="DealerVendorRelations")
@Table(name = "dealer_vendor_relations")
public class DealerVendorRelations {

    @EmbeddedId
    private DealerIdVendorId dealerIdVendorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vendorId")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dealerId")
    private Dealer dealer;

    @Enumerated(EnumType.STRING)
    @Column(name="vendor_working_type")
    private VendorWT vendorWT;

    @Column(name="sales_volume")
    private Double salesVolume;

    @OneToMany(mappedBy = "dealerVendorRelations")
    private List<Product> productList;

    @OneToMany(mappedBy = "dealerVendorRelations")
    private List<ProductCategory> productCategoryList;

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

    public VendorWT getVendorWT() {
        return vendorWT;
    }

    public void setVendorWT(VendorWT vendorWT) {
        this.vendorWT = vendorWT;
    }

    public Double getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Double salesVolume) {
        this.salesVolume = salesVolume;
    }

    private DealerVendorRelations(){}

    public DealerVendorRelations(Vendor vendor, Dealer dealer, VendorWT vendorWT, Double salesVolume) {
        this.vendor = vendor;
        this.dealer = dealer;
        this.vendorWT = vendorWT;
        this.salesVolume = salesVolume;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
