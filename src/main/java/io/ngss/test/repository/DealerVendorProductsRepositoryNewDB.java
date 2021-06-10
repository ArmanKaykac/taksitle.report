package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerIdVendorId;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerVendorProductsRepositoryNewDB extends JpaRepository<DealerVendorProducts, Long> {

    DealerVendorProducts findOneByDealerIdVendorId(DealerIdVendorId dealerIdVendorId);

}

