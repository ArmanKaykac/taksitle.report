package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerVendorRelations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerVendorRelationsRepository extends JpaRepository<DealerVendorRelations, Long> {

    public List<DealerVendorRelations> findAllByDealer(Long dealerId);

}
