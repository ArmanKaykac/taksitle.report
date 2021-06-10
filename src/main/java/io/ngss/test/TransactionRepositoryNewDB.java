package io.ngss.test;

import io.ngss.taksitle.report.backoffice.customquery.BackOfficeCustomQuery;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.shared.database.repository.RiskParametersCustomQuery;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.test.BackOfficeCustomQueryNewDB;
import io.ngss.test.RiskParametersCustomQueryNewDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepositoryNewDB extends JpaRepository<Transaction, Long>, BackOfficeCustomQueryNewDB, RiskParametersCustomQueryNewDB {
    Transaction findByToken(Integer token);

    // List<Transaction> findAllByTransactionState(TransactionState state);

    //  List<Transaction> findAllByCreatedAtIsAfterAndDealerId(Date createdDate, Long dealerId);

    // List<Transaction> findAllByTransactionStateIsInAndInitialDateIsBetweenAndCustomerTcknoAndDealerId(TransactionState transactionState, Long startDate, Long finishDate, Long tckNo, Long dealerId);

    // List<Transaction> findAllByTransactionStateIsInAndInitialDateIsBetweenAndDealerId(TransactionState transactionState, Long startDate, Long finishDate, Long dealerId);

    // List<Transaction> findAllByInitialDateIsBetweenAndDealerIdOrderByInitialDateDesc(Long startDate, Long finishDate, Long dealerId);

    //  List<Transaction> findAllByInitialDateIsBetweenAndCustomerTcknoAndDealerIdOrderByInitialDateDesc(Long startDate, Long finishDate, Long tckNo, Long dealerId);

    // List<Transaction> findAllByTransactionStateAndDealerId(TransactionState transactionState, Long dealerId);

    // List<Transaction> findAllByTransactionStateIsInAndCreatedAtIsLessThanEqual(TransactionState[] stateList, Date currentDate);

    //  Optional<Transaction> findByTokenAndDealerId(Integer token, Long dealerId);

    //List<Transaction> findAllWithDateAfter (LocalDate date);

    List<Transaction> findAllByDealerIdAndCreatedAtIsAfter (Long dealerId, Date startDate);

    List<Transaction> findAllByStateUpdateDateAfter(Date createdDate);

    List<Transaction> findAllByCreatedAtIsAfter(Date createdDate);

    List<Transaction> findAllByCustomerTcknoAndDealerIdAndTransactionStateNotInAndLoanRequestLoanCategory (Long tckno, Long dealerId, TransactionState[] transactionStates, LoanCategory loanCategory);

    String GET_LAST_OFFER_DATE_OF_DEALER = "select max(t.initial_date) from Transaction t where t.dealer_id = ?1";

    @Query(value = GET_LAST_OFFER_DATE_OF_DEALER,
            nativeQuery = true)
    Long findLastOfferDateOfDealer(Long dealerId);


    String ALL_TRANSACTION_IDS_FOR_CUSTOMER = "select t.id from Transaction t where t.customer_id = ?1";

    @Query(value = ALL_TRANSACTION_IDS_FOR_CUSTOMER,
            nativeQuery = true)
    List<BigInteger> findAllTransactionIDsForCustomer(Long customerId);

    String GET_LAST_MONTHS_OPEN_TRANSACTION_COUNT_FOR_DEALER = "select count(*) from transaction where dealer_id = ?1 and created_at  > now() - interval '1 months' and transaction_state in (6,14,8,13)";

    @Query(value = GET_LAST_MONTHS_OPEN_TRANSACTION_COUNT_FOR_DEALER,
            nativeQuery = true)
    List<Integer> getLastMonthsTransactionCountForDealer(Long customerId);

    String FIND_TRANSACTION_FOR_LAST_1_WEEK = "select * from transaction where created_at>now()-INTERVAL '7 days';";

    @Query(value = FIND_TRANSACTION_FOR_LAST_1_WEEK, nativeQuery = true)
    List<Transaction> findAllForOneWeek();

  /*  String appBySameTckNoDifferentDealer = "select DISTINCT (dealer_id),array_agg(token) from transaction where  customer_id = (select id from customer where tckno = ?1) group by dealer_id;";

    @Query(value = appBySameTckNoDifferentDealer,
            nativeQuery = true)
    List<DealerIdTransactionTokenListModel> getAppBySameTckNoDifferentDealer1(Long tckno);
*/

}