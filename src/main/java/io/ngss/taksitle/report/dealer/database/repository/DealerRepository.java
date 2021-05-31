package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.enums.DealerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    Optional<Dealer> findByName(String name);

    List<Dealer> findAllByDealerType(DealerType dealerType);

}

