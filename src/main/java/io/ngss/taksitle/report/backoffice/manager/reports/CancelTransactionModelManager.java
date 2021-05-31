package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.model.reports.CancelTransactionModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.CancelTransactionSearchModel;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CancelTransactionModelManager extends BaseReportModel<CancelTransactionModel, CancelTransactionSearchModel> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CancelTransactionModel> filterModel(CancelTransactionSearchModel searchModel) {
        if (searchModel == null) return null;

        if (searchModel.dealerId == null && searchModel.transactionToken == null)
            return new ArrayList<>();

        List<Transaction> transactionList = new ArrayList<>();
        if (searchModel.transactionToken != null) {
            Transaction transaction = transactionRepository.findByToken(searchModel.transactionToken);
            if (transaction != null)
                transactionList.add(transaction);
        } else if (searchModel.dealerId != null) {
            long now = System.currentTimeMillis();
            final int MAX_QUERY_DAYS = 14;
            final long dayInMillis = 86400000L;

            transactionList = transactionRepository.findAllByDealerIdAndCreatedAtIsAfter(searchModel.dealerId, new Date(now - (MAX_QUERY_DAYS * dayInMillis)));
        }

        transactionList.sort(Comparator.comparing(Transaction::getCreatedAt).reversed());
        List<CancelTransactionModel> model = new ArrayList<>();
        transactionList.forEach(x -> model.add(CancelTransactionModel.convertToCancelTransactionModel(x)));
        return model;
    }
}

