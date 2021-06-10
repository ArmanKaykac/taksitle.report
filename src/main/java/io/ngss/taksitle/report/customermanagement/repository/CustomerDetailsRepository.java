package io.ngss.taksitle.report.customermanagement.repository;

import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

}
