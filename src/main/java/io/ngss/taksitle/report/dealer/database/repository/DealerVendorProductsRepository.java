package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerIdVendorId;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerVendorProductsRepository extends JpaRepository<DealerVendorProducts, Long> {

    DealerVendorProducts findOneByDealerIdVendorId(DealerIdVendorId dealerIdVendorId);

}
