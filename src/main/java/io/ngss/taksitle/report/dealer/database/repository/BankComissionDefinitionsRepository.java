package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.bank.database.entity.BankCommissionDefinitions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankComissionDefinitionsRepository extends JpaRepository<BankCommissionDefinitions, Long> {
}
