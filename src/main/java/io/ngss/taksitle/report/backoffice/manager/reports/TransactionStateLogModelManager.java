package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateLogModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionStateLogSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionStateLogModelManager extends BaseReportModel<TransactionStateLogModel, TransactionStateLogSearchModel> {

    @Autowired
    private TransactionHistoryLogRepository transactionHistoryLogRepository;

    @Autowired
    private BackOfficeReportHelper backOfficeReportHelper;

    @Override
    public List<TransactionStateLogModel> filterModel(TransactionStateLogSearchModel searchModel) {
        if (searchModel == null)
            return null;

        if (searchModel.startDate == null)
            return null;

        if (searchModel.endDate == null)
            searchModel.endDate = System.currentTimeMillis();

        List<TransactionHistoryLog> transactionHistoryLogList = transactionHistoryLogRepository.findAllByDateIsBetween(searchModel.startDate, searchModel.endDate);

        if (transactionHistoryLogList == null)
            return null;

        Map<Integer, Transaction> transactionMap = new HashMap<>();
        transactionHistoryLogList.stream().forEach(t -> transactionMap.put(t.getTransaction().getToken(), t.getTransaction()));
        List<Transaction> transactionList = transactionMap.values().stream().collect(Collectors.toList());

        if (transactionList == null)
            return null;

        if (searchModel.dealerId != null)
            transactionList = transactionList.stream().filter(x -> x.getDealer() != null
                    && x.getDealer().getId().equals(searchModel.dealerId))
                    .collect(Collectors.toList());

        if (searchModel.financialTypeId != null)
            transactionList = transactionList.stream().filter(x -> x.getLoanRequest() != null
                    && x.getLoanRequest().getLoanCategory() != null
                    && x.getLoanRequest().getLoanCategory().equals(LoanCategory.getBySekerbankValue(searchModel.financialTypeId.toString())))
                    .collect(Collectors.toList());

        if (searchModel.transactionToken != null)
            transactionList = transactionList.stream().filter(x -> x.getToken().equals(searchModel.transactionToken))
                    .collect(Collectors.toList());

        transactionList.sort(Comparator.comparing(Transaction::getCreatedAt).reversed());

        return backOfficeReportHelper.prepareLogReport(transactionList);
    }
}