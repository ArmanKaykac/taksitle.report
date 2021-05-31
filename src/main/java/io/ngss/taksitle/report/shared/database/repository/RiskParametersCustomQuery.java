package io.ngss.taksitle.report.shared.database.repository;

import io.ngss.taksitle.report.transaction.database.Transaction;

import java.util.List;

public interface RiskParametersCustomQuery {

    Boolean getAppCountBySameTckNoDifferentDealer(Long tckno, Long dealerId);
    List<Transaction> getAppBySameTckNoDifferentDealer(Long tckno, Long dealerId);

    Boolean changeInGSMLast30Days(Long tckNo);
    Boolean changeInMotherMaidenLast30Days(Long tckNo);

    List<Transaction> getAppChangeInMotherMaidenLast30Days(Long tckNo, Integer token, String surnameForCurrentTransaction);

    Boolean getAppCountBySameGSMNoLast30Days(Long gsmno, Long customerId, Long transactionDate);
    List<Transaction> getAppBySameGSMNoLast30Days(Long gsmno,Long customerId,Long transactionDate);

    Boolean getCountAppBySameSurnameSameDealerLast7Days(String surname, Long dealerId, Long tckno);

    List<Transaction> appBySameSurnameSameDealerLast7Days(String surname, Long dealerId, Long tckno);

    Boolean getCountAppForSamePlateChasisLast2Days(String plateNo,String chassisNo,Long tckno);
    List<Transaction> getAppForSamePlateChasisLast2Days(String plateNo, String chassisNo, Long tckno);

    Boolean cellPhoneOwnedByDealerOrEmployees(Long phoneNo,Long dealerId);
    Boolean getAppCountForSamePlateChasisDiffDealerLast7Days(String plateNo, String chassisNo,Long dealerId);
    List<Transaction> getAppForSamePlateChasisDiffDealerLast7Days(String plateNo, String chassisNo,Long dealerId);

    Boolean getSurnameSameAsDealerWorkers(String surname, Long dealerId, Long tckNo);

    Boolean getSameTaxNoMoreThan1TCKno(String taxNo, Long tckNo);

    List<Transaction> getAppSameTaxNoMoreThan1TCKno(String taxNo, Long tckNo);
    Boolean getSameTaxNoAsDealer(String taxNo,Long dealerId);



}
