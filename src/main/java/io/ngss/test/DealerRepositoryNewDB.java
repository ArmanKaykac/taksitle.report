package io.ngss.test;

import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.enums.DealerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealerRepositoryNewDB extends JpaRepository<Dealer, Long> {
    Optional<Dealer> findByName(String name);

    List<Dealer> findAllByDealerType(DealerType dealerType);

}
