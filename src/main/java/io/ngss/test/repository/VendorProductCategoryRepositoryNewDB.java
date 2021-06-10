package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.VendorProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProductCategoryRepositoryNewDB extends JpaRepository<VendorProductCategory, Long> {
    VendorProductCategory findByVendorIdAndProductCategoryId(Long vendorId, Long productCategoryId);
}

