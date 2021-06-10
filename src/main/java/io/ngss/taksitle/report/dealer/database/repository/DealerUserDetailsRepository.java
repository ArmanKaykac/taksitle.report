package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerUserDetailsRepository extends JpaRepository<DealerUserDetails, Long> {

    DealerUserDetails findByDealerUserId(Long along);
}
