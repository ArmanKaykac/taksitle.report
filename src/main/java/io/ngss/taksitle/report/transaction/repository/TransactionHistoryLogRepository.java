package io.ngss.taksitle.report.transaction.repository;

import io.ngss.taksitle.report.backoffice.customquery.BackOfficeCustomQuery;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryLogRepository extends JpaRepository<TransactionHistoryLog, Long>, BackOfficeCustomQuery {

//    Optional<TransactionHistoryLog> findFirstByTransactionIdAndTransactionState(Long id, TransactionState state);
//
//    List<TransactionHistoryLog> findAllByTransactionToken(Integer token);
//
//    List<TransactionHistoryLog> findAllByDateIsAfter(Long start);

    List<TransactionHistoryLog> findAllByDateIsBetween(Long start, Long end);
}

