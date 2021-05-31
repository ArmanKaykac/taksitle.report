package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerAndSubDealerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerAndSubDealerDetailsRepository extends JpaRepository<DealerAndSubDealerDetails, Long> {

    DealerAndSubDealerDetails findOneByDealerId(Long id);
}
