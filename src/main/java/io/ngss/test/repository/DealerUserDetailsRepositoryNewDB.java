package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerUserDetailsRepositoryNewDB extends JpaRepository<DealerUserDetails, Long> {
}

