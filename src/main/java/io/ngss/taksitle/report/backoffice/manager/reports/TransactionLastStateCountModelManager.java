package io.ngss.taksitle.report.backoffice.manager.reports;


import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionLastStateCountModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionLastStateCountSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import io.ngss.test.TransactionHistoryLogRepositoryNewDB;
import io.ngss.test.helper.BackOfficeReportHelperNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionLastStateCountModelManager extends BaseReportModel<TransactionLastStateCountModel, TransactionLastStateCountSearchModel> {

    @Autowired
    private BackOfficeReportHelperNewDB backOfficeReportHelper;

    @Autowired
    private TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepository;

    public static final String TOTAL_ROW_TITLE = "Toplam";


    @Override
    public List<TransactionLastStateCountModel> filterModel(TransactionLastStateCountSearchModel searchModel) {
        if (searchModel.startDate == null)
            return null;

        List<Transaction> tcknTransactions = backOfficeReportHelper.getTcknTransactions(searchModel.startDate, searchModel.endDate);
        if (tcknTransactions == null)
            return null;

        List<Transaction> filteredTcknTransactions = tcknTransactions.stream().filter(transaction ->
                transaction.getCreatedAt().after(new Date(searchModel.startDate))).collect(Collectors.toList());

        if (searchModel.endDate != null) {
            filteredTcknTransactions = filteredTcknTransactions.stream().filter(transaction ->
                    transaction.getCreatedAt().before(new Date(searchModel.endDate))).collect(Collectors.toList());
        }

        if (searchModel.dealerId != null) {
            filteredTcknTransactions = filteredTcknTransactions.stream().filter(transaction ->
                    transaction.getDealer().getId().equals(searchModel.dealerId)).collect(Collectors.toList());
        }

        if (searchModel.financialTypeId != null) {
            filteredTcknTransactions = filteredTcknTransactions.stream().filter(x -> x.getLoanRequest() != null
                    && x.getLoanRequest().getLoanCategory() != null
                    && x.getLoanRequest().getLoanCategory().equals(LoanCategory.getBySekerbankValue(searchModel.financialTypeId.toString())))
                    .collect(Collectors.toList());
        }

        backOfficeReportHelper.changeTransactionsStateByLastState(filteredTcknTransactions);

        List<TransactionLastStateCountModel> model = new ArrayList<>();
        if (searchModel.groupedByDealer) {
            Map<Dealer, List<Transaction>> dealerListMap = filteredTcknTransactions.stream().collect(Collectors.groupingBy(Transaction::getDealer));
            dealerListMap.forEach((dealer, transactionList) -> {
                model.add(backOfficeReportHelper.prepareTotalValuesByTransactions(transactionList, dealer.getName(), dealer, searchModel.onlyDealer));
                model.addAll(backOfficeReportHelper.prepareTransactionStateList(transactionList, searchModel.onlyDealer));
            });
            model.add(backOfficeReportHelper.prepareTotalValuesByTransactions(filteredTcknTransactions, TOTAL_ROW_TITLE, null, false));
        } else {
            model.addAll(backOfficeReportHelper.prepareTransactionStateList(filteredTcknTransactions, false));
            model.add(backOfficeReportHelper.prepareTotalValuesByTransactions(filteredTcknTransactions, TOTAL_ROW_TITLE, null, false));
        }

        return model;
    }
}
