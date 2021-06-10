package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerIdVendorId;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerVendorProductCategoriesRepositoryNewDB extends JpaRepository<DealerVendorProductCategories, Long> {

    DealerVendorProductCategories findOneByDealerIdVendorId(DealerIdVendorId dealerIdVendorId);

}

