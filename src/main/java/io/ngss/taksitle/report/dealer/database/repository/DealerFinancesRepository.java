package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerFinances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DealerFinancesRepository extends JpaRepository<DealerFinances, Long> {
    DealerFinances findByDealerId(Long dealerId);

}
