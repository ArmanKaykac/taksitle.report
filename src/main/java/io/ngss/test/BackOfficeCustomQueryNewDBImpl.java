package io.ngss.test;

import io.ngss.taksitle.report.backoffice.customquery.CustomQueryRequest;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BackOfficeCustomQueryNewDBImpl implements BackOfficeCustomQueryNewDB {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transaction> search(CustomQueryRequest request, Boolean isLimited) {
        //String queryStr = "select t.* from transaction t,customer c,dealer d where t.customer_id = c.id and t.dealer_id = d.id";
        String queryStr = "select t.* from transaction t,customer c,dealer d";

        if (request.getFinancialTypeId() != null)
            queryStr += ", loan_request lr";

        queryStr += " where t.customer_id = c.id and t.dealer_id = d.id";

        if (request.getFinancialTypeId() != null)
            queryStr += " and t.id = lr.transaction_id";
        List<Transaction> data = null;
        if (request.getToken() != null) queryStr += " and t.token = " + request.getToken();
        if (request.getTransactionState() != null)
            queryStr += " and t.transaction_state = " + TransactionState.valueOf(request.getTransactionState()).getOrder();
        if (request.getCustomerId() != null) queryStr += " and t.customer_id = " + request.getCustomerId();
        if (request.getTckNo() != null)
            queryStr += " and t.customer_id = (select id from customer where customer.tckno = " + request.getTckNo() + ")";
        if (request.getDealerId() != null) queryStr += " and t.dealer_id = " + request.getDealerId();
        if (request.getDealerName() != null)
            queryStr += " and t.dealer_id = (select id from dealer where dealer.name = " + request.getDealerName();
        if (request.getGsmNo() != null)
            queryStr += " and t.customer_id = (select id from customer where customer.gsmno = " + request.getGsmNo() + ")";
        if (request.getNameSurname() != null && request.getNameSurname() != "")
            queryStr += " and t.customer_id in (select customer.id from customer,customer_details where customer.customer_details_id = customer_details.id and UPPER(TRANSLATE(customer_details.name,'ıi','Iİ')) = UPPER(TRANSLATE('" + request.getNameSurname() +"','ıi','Iİ')))";
        if (request.getFinancialTypeId() != null) {
            LoanCategory loanCategory = LoanCategory.getBySekerbankValue(request.getFinancialTypeId().toString());
            if (loanCategory != null) {
                queryStr += " and lr.financial_type = '" + loanCategory.toString() + "'";
            }
        }
        //queryStr += " and t.customer_id in (select customer.id from customer,customer_details where customer.customer_details_id = customer_details.id and upper(customer_details.name) ilike '%" + request.getNameSurname().toUpperCase() + "%')";

        if (request.getStartDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            queryStr += " and t.created_at >= '" + sdf.format(new Date(request.getStartDate())) + "'";
        }
        if (request.getEndDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            queryStr += " and  t.created_at  <= '" + sdf.format(new Date(request.getEndDate())) + "'";
        }

        if (isLimited) queryStr += " limit 10000 ";

        Query query = entityManager.createNativeQuery(queryStr, Transaction.class);
        data = query.getResultList();
        return data;
    }

}