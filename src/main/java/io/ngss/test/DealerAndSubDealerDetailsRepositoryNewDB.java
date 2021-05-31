package io.ngss.test;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerAndSubDealerDetails;
import io.ngss.taksitle.report.transaction.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerAndSubDealerDetailsRepositoryNewDB extends JpaRepository<DealerAndSubDealerDetails, Long> {

    DealerAndSubDealerDetails findOneByDealerId(Long id);
}
