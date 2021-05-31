package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateBaseDealerModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionStateBaseDealerSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionStateBaseDealerModelManager extends BaseReportModel<TransactionStateBaseDealerModel, TransactionStateBaseDealerSearchModel> {

    @Autowired
    private TransactionHistoryLogRepository transactionHistoryLogRepository;

    @Override
    public List<TransactionStateBaseDealerModel> filterModel(TransactionStateBaseDealerSearchModel searchModel) {
        if (searchModel == null)
            return null;

        if (searchModel.startDate == null)
            return null;

        if (searchModel.endDate == null)
            searchModel.endDate = System.currentTimeMillis();

        List<TransactionHistoryLog> transactionHistoryLogList = transactionHistoryLogRepository.findAllByDateIsBetween(searchModel.startDate, searchModel.endDate);
        if (transactionHistoryLogList == null)
            return null;

        List<Transaction> transactionList = new ArrayList<>();
        transactionHistoryLogList.stream().forEach(transactionHistoryLog -> transactionList.add(transactionHistoryLog.getTransaction()));
        if (transactionList == null)
            return null;

        List<Transaction> filteredTransactions = transactionList.stream().distinct().collect(Collectors.toList());
        if (searchModel.dealerId != null)
            filteredTransactions = filteredTransactions.stream().filter(x -> x.getDealer() != null && x.getDealer().getId().equals(searchModel.dealerId)).collect(Collectors.toList());

        if (searchModel.financialTypeId != null)
            filteredTransactions = filteredTransactions.stream().filter(x -> x.getLoanRequest() != null && x.getLoanRequest().getLoanCategory() != null && x.getLoanRequest().getLoanCategory().equals(LoanCategory.getBySekerbankValue(searchModel.financialTypeId.toString()))).collect(Collectors.toList());

        Map<Dealer, List<Transaction>> transactionListGroupedByDealer = filteredTransactions.stream().collect(Collectors.groupingBy(Transaction::getDealer));

        List<TransactionStateBaseDealerModel> models = new ArrayList<>();

        try {
            transactionListGroupedByDealer.forEach((dealer, transactions) -> {

                TransactionStateBaseDealerModel transactionStateBaseDealerModel = new TransactionStateBaseDealerModel();

                transactionStateBaseDealerModel.dealerName = dealer.getName();

                transactionStateBaseDealerModel.onDegerlendirmeOlumsuz = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.ON_DEGERLENDIRME_OLUMSUZ, transactions, searchModel.startDate, searchModel.endDate).size());

                transactionStateBaseDealerModel.siparisCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.SIPARIS_GIRILIYOR, transactions, searchModel.startDate, searchModel.endDate).size());

                transactionStateBaseDealerModel.teklifAlindiCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.TEKLIF_ALINDI, transactions, searchModel.startDate, searchModel.endDate).size());

                transactionStateBaseDealerModel.bankaSecildiCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.BANKA_SECILDI, transactions, searchModel.startDate, searchModel.endDate).size());

                transactionStateBaseDealerModel.redCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.REDDEDILDI, transactions, searchModel.startDate, searchModel.endDate).size());

                transactionStateBaseDealerModel.kullandirildiCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.KREDI_KULLANDIRILDI, transactions, searchModel.startDate, searchModel.endDate).size());

                long allStatesCount = 0;
                for (Transaction transaction : transactions) {
                    if (transaction == null
                            || transaction.getTransactionHistoryLogs() == null
                            || transaction.getTransactionHistoryLogs().isEmpty())
                        continue;

                    for (TransactionHistoryLog transactionHistoryLog : transaction.getTransactionHistoryLogs()) {
                        if (BackOfficeReportHelper.isBetweenDates(transactionHistoryLog, searchModel.startDate, searchModel.endDate))
                            allStatesCount++;
                    }
                }

                transactionStateBaseDealerModel.taslakCount = new Long(BackOfficeReportHelper.filterTransactions(
                        TransactionState.TASLAK, transactions, searchModel.startDate, searchModel.endDate).size());

                try {
                    if (dealer != null && dealer.getDealerAndSubDealerDetails() != null) {
                        transactionStateBaseDealerModel.source = dealer.getDealerAndSubDealerDetails().getSource();
                        transactionStateBaseDealerModel.portfolioManager = dealer.getDealerAndSubDealerDetails().getPortfolioManager();
                        transactionStateBaseDealerModel.city = dealer.getDealerAndSubDealerDetails().getSellerCity() == null ? ""
                                : dealer.getDealerAndSubDealerDetails().getSellerCity().getName();
                        transactionStateBaseDealerModel.code = dealer.getDealerAndSubDealerDetails().getDealer().getId();
                    }
                } catch (Exception e) {

                }

                models.add(transactionStateBaseDealerModel);
            });

            calculateTotalAndAddToList(models);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return models;
    }

    private void calculateTotalAndAddToList(List<TransactionStateBaseDealerModel> modelList) {
        if (modelList == null)
            return;

        TransactionStateBaseDealerModel totalModel = new TransactionStateBaseDealerModel();
        totalModel.dealerName = "Total";

        totalModel.otherStateCount = modelList.stream().mapToLong(i -> i.otherStateCount).sum();
        totalModel.onDegerlendirmeOlumsuz = modelList.stream().mapToLong(i -> i.onDegerlendirmeOlumsuz).sum();
        totalModel.siparisCount = modelList.stream().mapToLong(i -> i.siparisCount).sum();
        totalModel.teklifAlindiCount = modelList.stream().mapToLong(i -> i.teklifAlindiCount).sum();
        totalModel.bankaSecildiCount = modelList.stream().mapToLong(i -> i.bankaSecildiCount).sum();
        totalModel.redCount = modelList.stream().mapToLong(i -> i.redCount).sum();
        totalModel.kullandirildiCount = modelList.stream().mapToLong(i -> i.kullandirildiCount).sum();

        modelList.add(totalModel);
    }
}

