package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.VendorProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProductCategoryRepository extends JpaRepository<VendorProductCategory, Long> {
    VendorProductCategory findByVendorIdAndProductCategoryId(Long vendorId, Long productCategoryId);
}
