package io.ngss.taksitle.report.dealer.database.entity.dealer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VendorIdProductCategoryId implements Serializable {

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "product_category_id")
    private Long productCategoryId;

    public VendorIdProductCategoryId () {}

    public VendorIdProductCategoryId(Long vendorId, Long productCategoryId) {
        this.vendorId = vendorId;
        this.productCategoryId = productCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VendorIdProductCategoryId that = (VendorIdProductCategoryId) o;
        return Objects.equals(vendorId, that.vendorId) &&
                Objects.equals(productCategoryId, that.productCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorId, productCategoryId);
    }
    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}