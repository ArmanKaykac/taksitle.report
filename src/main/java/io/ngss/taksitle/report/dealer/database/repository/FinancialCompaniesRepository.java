package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.FinancialCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialCompaniesRepository extends JpaRepository<FinancialCompanies, Long> {
}
