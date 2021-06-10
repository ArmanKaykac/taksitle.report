package io.ngss.taksitle.report.transaction.repository;

import io.ngss.taksitle.report.transaction.database.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {
    DeliveryInfo findByTransactionId(Long aLong);
}
