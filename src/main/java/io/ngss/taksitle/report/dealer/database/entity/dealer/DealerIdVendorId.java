package io.ngss.taksitle.report.dealer.database.entity.dealer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DealerIdVendorId implements Serializable {

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "dealer_id")
    private Long dealerId;

    private DealerIdVendorId(){}

    public DealerIdVendorId(Long vendorId,Long dealerId){this.dealerId = dealerId; this.vendorId=vendorId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        DealerIdVendorId that = (DealerIdVendorId) o;
        return Objects.equals(dealerId, that.dealerId) &&
                Objects.equals(vendorId, that.vendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealerId, vendorId);
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }
}
