package io.ngss.taksitle.report.bank.database.repository;

import io.ngss.taksitle.report.bank.database.entity.TaksitleRelations;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaksitleRelationsRepository extends JpaRepository<TaksitleRelations, Long> {
    TaksitleRelations findByDealerId(Long aLong);
}
