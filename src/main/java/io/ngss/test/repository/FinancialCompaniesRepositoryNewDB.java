package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.FinancialCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialCompaniesRepositoryNewDB extends JpaRepository<FinancialCompanies, Long> {
}

