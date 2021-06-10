package io.ngss.test.repository;

import io.ngss.taksitle.report.transaction.database.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfoRepositoryNewDB extends JpaRepository<DeliveryInfo, Long> {
    DeliveryInfo findByTransactionId(Long aLong);
}
