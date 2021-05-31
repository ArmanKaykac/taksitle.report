package io.ngss.taksitle.report.bank.database.repository;

import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LoanOfferRepository extends JpaRepository<LoanOffer, Long> {

    LoanOffer findByTransactionAndBank(Transaction transaction, Bank bank);
    LoanOffer findByBankSpecificID(String bankSpecificID);
    List<LoanOffer> findAllByCancelledAndCreationLessThan(Boolean cancelled, Long creation);
    List<LoanOffer> findAllByTransactionToken(Integer token);
    List<LoanOffer> findAllByTransactionTokenAndIsSelectedIsTrue(Integer token);
    String GET_LAST_OFFER_DATE_OF_DEALER = "select * from loan_offer l where l.transaction_id= ?1";

    @Query(value = GET_LAST_OFFER_DATE_OF_DEALER,
            nativeQuery = true)
    LoanOffer findByTransaction(Long id);

    List<LoanOffer> findAllByTransactionTransactionStateInAndTransactionUpdatedAtAfter(TransactionState[] stateList, Date date);
    List<LoanOffer> findAllByTransactionUpdatedAtAfter(Date date);

    List<LoanOffer> findAllByTransactionTransactionStateInAndExpireDateIsLessThanEqual(TransactionState[] stateList,Long currentDate);
    List<LoanOffer> findAllByTransactionTransactionStateInAndExpireDateIsLessThanEqualAndIsSelectedIsTrue(TransactionState[] stateList,Long currentDate);
    List<LoanOffer> findAllByTransactionDealerIdAndTransactionTransactionStateAndIsSelectedTrueAndTransactionCustomerTckno(Long dealerUserId, TransactionState transactionState, Long custId);
    List<LoanOffer> findAllByTransactionTransactionStateAndIsSelectedTrueAndTransactionCustomerTckno(TransactionState transactionState, Long custId);

    List<LoanOffer> findAllByTransactionDealerUserIdAndTransactionDealerIdAndIsSelectedTrueAndTransactionTransactionState(Long dealerUserId, Long dealerId, TransactionState transactionState);

    List<LoanOffer> findAllByTransactionTransactionStateInAndTransactionStateUpdateDateAfter(TransactionState[] stateList, Date date);

    List<LoanOffer> findAllByTransactionTransactionStateInAndTransactionTokenIn(TransactionState[] transactionStates, List<Integer> tokens);

    List<LoanOffer> findAllByTransactionId(Long id);
}
