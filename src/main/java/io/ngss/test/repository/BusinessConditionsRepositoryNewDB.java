package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessConditionsRepositoryNewDB extends JpaRepository<BusinessConditions, Long> {
}

