package io.ngss.test.repository;

import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepositoryNewDB extends JpaRepository<CustomerDetails, Long> {
}
