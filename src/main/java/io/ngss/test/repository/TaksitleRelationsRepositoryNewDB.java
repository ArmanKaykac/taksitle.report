package io.ngss.test.repository;

import io.ngss.taksitle.report.bank.database.entity.TaksitleRelations;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaksitleRelationsRepositoryNewDB extends JpaRepository<TaksitleRelations, Long> {
    TaksitleRelations findByDealer(Dealer dealer);
}
