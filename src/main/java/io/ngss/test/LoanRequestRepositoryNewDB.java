package io.ngss.test;

import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRequestRepositoryNewDB extends JpaRepository<LoanRequest, Integer> {

    List<LoanRequest> findAllByTransactionDealerUserIdAndTransactionDealerId(Long dealerUserId, Long dealerId);

    List<LoanRequest> findAllByTransactionToken(Integer transactionToken);
}
