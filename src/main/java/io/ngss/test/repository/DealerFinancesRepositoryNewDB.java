package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerFinances;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealerFinancesRepositoryNewDB extends JpaRepository<DealerFinances, Long> {
    DealerFinances findByDealerId(Long dealerId);
}

