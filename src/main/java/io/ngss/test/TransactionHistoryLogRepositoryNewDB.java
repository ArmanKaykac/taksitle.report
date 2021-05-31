package io.ngss.test;

import io.ngss.taksitle.report.backoffice.customquery.BackOfficeCustomQuery;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryLogRepositoryNewDB extends JpaRepository<TransactionHistoryLog, Long>, BackOfficeCustomQueryNewDB {

//    Optional<TransactionHistoryLog> findFirstByTransactionIdAndTransactionState(Long id, TransactionState state);
//
//    List<TransactionHistoryLog> findAllByTransactionToken(Integer token);
//
//    List<TransactionHistoryLog> findAllByDateIsAfter(Long start);

    List<TransactionHistoryLog> findAllByDateIsBetween(Long start, Long end);
}

