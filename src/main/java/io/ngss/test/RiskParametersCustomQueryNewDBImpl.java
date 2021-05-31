package io.ngss.test;

import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class RiskParametersCustomQueryNewDBImpl implements RiskParametersCustomQueryNewDB {

    @PersistenceContext
    private EntityManager entityManager;

    private static String appCountBySameTckNoDifferentDealer = "select count(distinct (dealer_id)) from transaction where dealer_id != ?2 and customer_id = (select id from customer where tckno = ?1) and created_at > now() - interval '7 days'";
    private static String appBySameTckNoDifferentDealer = "select * from transaction where dealer_id != ?2 and customer_id = (select id from customer where tckno = ?1) and created_at > now() - interval '7 days'";

    private static String changeInGSMLast30Days = "select count(distinct (gsmno)) from customerhistory where customer_id = (select id from customer where tckno = ?1) and transaction_date > ?2";

    private static String changeInMotherMaidenLast30Days = "select count(distinct LOWER(mother_maiden_name)) from customerhistory where customer_id = (select id from customer where tckno = ?1) and transaction_date > ?2 and mother_maiden_name IS NOT NULL";

    private static String appChangeInMotherMaidenLast30Days = "select * from transaction where id in (select transaction_id from customerhistory where LOWER(mother_maiden_name) != LOWER(?4) and customer_id = (select id from customer where tckno = ?1) and transaction_date > ?2  and mother_maiden_name IS NOT NULL) and token != ?3";

    private static String appCountBySameGSMNoLast30Days = "select count(*) from customerhistory where gsmno = ?1 and customer_id != ?2 and transaction_date > ?3";
    //"select count(*) from transaction where customer_id in (select id from customer where gsmno = ?1) and created_at > now() - interval '30 days'";
    private static String appBySameGSMNoLast30Days = "select * from transaction where id in (select DISTINCT(transaction_id) from customerhistory where gsmno = ?1 and customer_id != ?2 and transaction_date > ?3)";

    //private static String appCountBySameSurnameSameDealerLast7Days = "select count(*) from transaction where customer_id in (select DISTINCT(customer_details.customer_id) from customer_details where UPPER(substring(trim(name) FROM '([^ ]+)$')) = ?1 and dealer_id = ?2 and created_at > now() - interval '7 days'";
    //private static String appCountBySameSurnameSameDealerLast7Days = "select count(DISTINCT (customer_id)) from transaction where customer_id in (select DISTINCT(id) from customer  where customer_details_id in (select DISTINCT (id) from customer_details where UPPER(substring(trim(name) FROM '([^ ]+)$')) = ?1)) and dealer_id = ?2 and created_at > now() - interval '7 days'";
    //private static String appCountBySameSurnameSameDealerLast7Days = "select count(DISTINCT (customer_id)) from transaction where customer_id in (select DISTINCT(id) from customer  where customer_details_id in (select DISTINCT (id) from customer_details where UPPER(substring(trim(name) FROM '([^ ]+)$')) = ?1) and tckno != ?3) and dealer_id = ?2 and created_at > now() - interval '7 days'  and transaction_state = 18";
    private static String appCountBySameSurnameSameDealerLast7Days = "select count(DISTINCT (customer_id)) from transaction where customer_id in (select DISTINCT(id) from customer  where customer_details_id in (select DISTINCT (id) from customer_details where UPPER(substring(trim(name) FROM '([^ ]+)$')) = ?1) and tckno != ?3) and dealer_id = ?2 and created_at > now() - interval '7 days'  and transaction_state in (18, 2, 25, 27)";

    private static String appBySameSurnameSameDealerLast7Days = "select * from transaction where customer_id in (select DISTINCT(id) from customer  where customer_details_id in (select DISTINCT (id) from customer_details where UPPER(substring(trim(name) FROM '([^ ]+)$')) = ?1) and tckno != ?3) and dealer_id = ?2 and created_at > now() - interval '7 days'  and transaction_state in (18, 2, 25, 27)";

    private static String appCountForSamePlateChasisLast2Days = "select count(*) from transaction where created_at > now() - interval '3 days' and id in (select transaction_id from loan_request where id in (select loanrequest_id from cart where id in (select cart_id from cart_product cp where cp.car_identity_no = ?1 or car_plate = ?2))) and customer_id in (select id from customer where tckno != ?3) and transaction_state in (18, 25, 27)";
    private static String appForSamePlateChasisLast2Days = "select * from transaction where created_at > now() - interval '3 days' and id in (select transaction_id from loan_request where id in (select loanrequest_id from cart where id in (select cart_id from cart_product cp where cp.car_identity_no = ?1 or car_plate = ?2))) and customer_id in (select id from customer where tckno != ?3)  and transaction_state in (18, 25, 27)";

    private static String cellPhoneOwnedByDealerOrEmployees = "select count(*) from dealer_user where gsmno = ?1 and dealer_id = ?2";

    private static String appCountForSamePlateChasisDiffDealerLast7Days = "select count(*) from transaction where id in (select transaction_id from loan_request where created_at > now() - interval '7 days' and id in (select loanrequest_id from cart where id in (select cart_id from cart_product cp where cp.car_identity_no = ?1 or car_plate = ?2))) and dealer_id != ?3";
    private static String appForSamePlateChasisDiffDealerLast7Days = "select * from transaction where id in (select transaction_id from loan_request where created_at > now() - interval '7 days' and id in (select loanrequest_id from cart where id in (select cart_id from cart_product cp where cp.car_identity_no = ?1 or car_plate = ?2))) and dealer_id != ?3";

    //private static String surnameSameAsDealerWorkers = "select count(*) from dealer_user where UPPER(regexp_replace(name, '^.* ', '')) = ?1 and dealer_id = ?2";
    private static String surnameSameAsDealerWorkers = "select count(*) from dealer_user du join dealer_user_details dud on du.dealer_user_details_id = dud.id where UPPER(regexp_replace(du.name, '^.* ', '')) = ?1 and du.dealer_id = ?2 and dud.tckno != ?3";

    private static String sameTaxNoMoreThan1TCKno = "select count(DISTINCT (c.tckno)) from customerhistory ch INNER JOIN customer c on c.id = ch.customer_id where ch.companyvdno = ?1 and c.tckno != ?2";
    private static String appSameTaxNoMoreThan1TCKno = "select * from transaction where id in (select transaction_id from customerhistory ch INNER JOIN customer c on c.id = ch.customer_id where ch.companyvdno = ?1 and c.tckno != ?2)";

    private static String sameTaxNoAsDealer = "select count(*) from dealer_and_subdealer_details where tax_id_number = ?1 and dealer_id = ?2";

    @Override
    public Boolean getAppCountBySameTckNoDifferentDealer(Long tckNo, Long dealerId) {

        Query query = entityManager.createNativeQuery(appCountBySameTckNoDifferentDealer);
        query.setParameter(1, tckNo);
        query.setParameter(2, dealerId);
        Integer noOfDealers = ((BigInteger) query.getSingleResult()).intValue();

        return noOfDealers > 0;
    }

    @Override
    public List<Transaction> getAppBySameTckNoDifferentDealer(Long tckNo, Long dealerId) {

        Query query = entityManager.createNativeQuery(appBySameTckNoDifferentDealer, Transaction.class);
        query.setParameter(1, tckNo);
        query.setParameter(2, dealerId);
        List<Transaction> transactions = query.getResultList();
        return transactions;
    }

    @Override
    public Boolean changeInGSMLast30Days(Long tckNo) {
        Query query = entityManager.createNativeQuery(changeInGSMLast30Days);
        query.setParameter(1, tckNo);
        query.setParameter(2, (System.currentTimeMillis() % 1000) - 2629743);
        Integer noOfGsm = ((BigInteger) query.getSingleResult()).intValue();

        return noOfGsm > 1;

    }

    @Override
    public Boolean changeInMotherMaidenLast30Days(Long tckNo) {
        Query query = entityManager.createNativeQuery(changeInMotherMaidenLast30Days);
        query.setParameter(1, tckNo);
        query.setParameter(2, (System.currentTimeMillis()) - 2629743000L);
        Integer noOfSurnames = ((BigInteger) query.getSingleResult()).intValue();

        return noOfSurnames > 1;
    }

    @Override
    public List<Transaction> getAppChangeInMotherMaidenLast30Days(Long tckNo, Integer token, String surnameForCurrentTransaction) {
        Query query = entityManager.createNativeQuery(appChangeInMotherMaidenLast30Days, Transaction.class);
        query.setParameter(1, tckNo);
        //query.setParameter(2, (System.currentTimeMillis() % 1000) - 2629743);
        query.setParameter(2, (System.currentTimeMillis()) - 2629743000L);
        query.setParameter(3, token);
        query.setParameter(4, surnameForCurrentTransaction);

        List<Transaction> transactions = query.getResultList();


        return transactions;
    }


    @Override
    public Boolean getAppCountBySameGSMNoLast30Days(Long gsmno, Long customerId, Long date) {

        Query query = entityManager.createNativeQuery(appCountBySameGSMNoLast30Days);
        query.setParameter(1, gsmno);
        query.setParameter(2, customerId);
        query.setParameter(3, date);

        Integer noOfApplications = ((BigInteger) query.getSingleResult()).intValue();

        return noOfApplications > 0;

    }

    @Override
    public List<Transaction> getAppBySameGSMNoLast30Days(Long gsmno, Long customerId, Long date) {

        Query query = entityManager.createNativeQuery(appBySameGSMNoLast30Days, Transaction.class);
        query.setParameter(1, gsmno);
        query.setParameter(2, customerId);
        query.setParameter(3, date);
        List<Transaction> transactions = query.getResultList();

        return transactions;

    }


    @Override
    public Boolean getCountAppBySameSurnameSameDealerLast7Days(String surname, Long dealerId, Long tckno) {
        Query query = entityManager.createNativeQuery(appCountBySameSurnameSameDealerLast7Days);
        query.setParameter(1, surname);
        query.setParameter(2, dealerId);
        query.setParameter(3, tckno);
        Integer noOfApplications = ((BigInteger) query.getSingleResult()).intValue();

        return noOfApplications > 0;
    }

    @Override
    public List<Transaction> appBySameSurnameSameDealerLast7Days(String surname, Long dealerId, Long tckno) {
        Query query = entityManager.createNativeQuery(appBySameSurnameSameDealerLast7Days, Transaction.class);
        query.setParameter(1, surname);
        query.setParameter(2, dealerId);
        query.setParameter(3, tckno);
        List<Transaction> transactions = query.getResultList();

        return transactions;
    }


    @Override
    public Boolean getCountAppForSamePlateChasisLast2Days(String plateNo, String chassisNo, Long tckno) {
        Query query = entityManager.createNativeQuery(appCountForSamePlateChasisLast2Days);
        query.setParameter(1, chassisNo);
        query.setParameter(2, plateNo);
        query.setParameter(3, tckno);
        Integer noOfApplications = ((BigInteger) query.getSingleResult()).intValue();

        return noOfApplications > 0;
    }

    @Override
    public List<Transaction> getAppForSamePlateChasisLast2Days(String plateNo, String chassisNo, Long tckno) {
        Query query = entityManager.createNativeQuery(appForSamePlateChasisLast2Days, Transaction.class);
        query.setParameter(1, chassisNo);
        query.setParameter(2, plateNo);
        query.setParameter(3, tckno);
        List<Transaction> transactions = query.getResultList();
        return transactions;
    }


    @Override
    public Boolean cellPhoneOwnedByDealerOrEmployees(Long phoneNo, Long dealerId) {

        Query query = entityManager.createNativeQuery(cellPhoneOwnedByDealerOrEmployees);
        query.setParameter(1, String.valueOf(phoneNo));
        query.setParameter(2, dealerId);

        Object obj = query.getSingleResult();
        Integer noOfCellPhoneOwners = ((BigInteger) obj).intValue();

        return noOfCellPhoneOwners != 0;
    }

    @Override
    public Boolean getAppCountForSamePlateChasisDiffDealerLast7Days(String plateNo, String chassisNo, Long dealerId) {

        Query query = entityManager.createNativeQuery(appCountForSamePlateChasisDiffDealerLast7Days);
        query.setParameter(1, chassisNo);
        query.setParameter(2, plateNo);
        query.setParameter(3, dealerId);

        Integer noOfChasisPlateByDealer = ((BigInteger) query.getSingleResult()).intValue();

        return noOfChasisPlateByDealer != 0;
    }

    @Override
    public List<Transaction> getAppForSamePlateChasisDiffDealerLast7Days(String plateNo, String chassisNo, Long dealerId) {

        Query query = entityManager.createNativeQuery(appForSamePlateChasisDiffDealerLast7Days, Transaction.class);
        query.setParameter(1, chassisNo);
        query.setParameter(2, plateNo);
        query.setParameter(3, dealerId);

        List<Transaction> transactions = query.getResultList();

        return transactions;
    }

    @Override
    public Boolean getSurnameSameAsDealerWorkers(String surname, Long dealerId, Long tckNo) {

        Query query = entityManager.createNativeQuery(surnameSameAsDealerWorkers);
        query.setParameter(1, surname);
        query.setParameter(2, dealerId);
        query.setParameter(3,tckNo);

        Integer returnInt = ((BigInteger) query.getSingleResult()).intValue();

        return returnInt != 0;
    }

    @Override
    public Boolean getSameTaxNoMoreThan1TCKno(String taxNo, Long tckno) {

        if (taxNo == null) return false;

        Query query = entityManager.createNativeQuery(sameTaxNoMoreThan1TCKno);
        query.setParameter(1, taxNo);
        query.setParameter(2, tckno);
        Integer returnInt = ((BigInteger) query.getSingleResult()).intValue();

        return returnInt > 0;
    }

    @Override
    public List<Transaction> getAppSameTaxNoMoreThan1TCKno(String taxNo, Long tckNo) {

        Query query = entityManager.createNativeQuery(appSameTaxNoMoreThan1TCKno, Transaction.class);
        query.setParameter(1, taxNo);
        query.setParameter(2, tckNo);

        List<Transaction> transactions = query.getResultList();

        return transactions;

    }


    @Override
    public Boolean getSameTaxNoAsDealer(String taxNo, Long dealerId) {
        Query query = entityManager.createNativeQuery(sameTaxNoAsDealer);
        if (taxNo == null) return false;

        query.setParameter(1, BigInteger.valueOf(Long.parseLong(taxNo)));
        query.setParameter(2, dealerId);
        Object ob = query.getSingleResult();
        Integer returnInt = ((BigInteger) ob).intValue();

        return returnInt != 0;
    }

}