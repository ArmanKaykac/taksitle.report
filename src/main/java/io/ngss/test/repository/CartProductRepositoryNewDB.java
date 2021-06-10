package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepositoryNewDB extends JpaRepository<BusinessConditions, Long> {
}

