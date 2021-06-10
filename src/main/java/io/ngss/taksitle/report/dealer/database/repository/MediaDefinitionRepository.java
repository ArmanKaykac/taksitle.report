package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.entity.dealer.MediaDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaDefinitionRepository extends JpaRepository<MediaDefinition, Long> {

    List<MediaDefinition> findAllByLoanCategoryIsInOrLoanCategory (LoanCategory[] loanCategory, Object object);
}
