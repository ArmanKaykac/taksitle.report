package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import io.ngss.taksitle.report.dealer.database.entity.Cart;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessConditionsRepository extends JpaRepository<BusinessConditions, Long> {
    BusinessConditions findBusinessConditionsByDealerId(Long aLong);
    BusinessConditions findByDealerId(Long aLong);
}

