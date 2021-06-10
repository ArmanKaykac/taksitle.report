package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer> {

    List<LoanRequest> findAllByTransactionDealerUserIdAndTransactionDealerId(Long dealerUserId, Long dealerId);

    List<LoanRequest> findAllByTransactionToken(Integer transactionToken);
}
