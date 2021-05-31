package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.ApprovalRejectionReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.ApprovalRejectionReportSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.test.TransactionRepositoryNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalRejectionReportModelManager extends BaseReportModel<ApprovalRejectionReportModel, ApprovalRejectionReportSearchModel> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BackOfficeReportHelper backOfficeReportHelper;

    @Override
    public List<ApprovalRejectionReportModel> filterModel(ApprovalRejectionReportSearchModel searchModel) {
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

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(searchModel.startDate) + " -";
        date += searchModel.endDate == null ? format.format(System.currentTimeMillis()) : format.format(searchModel.endDate);

        TransactionState[] notInStates = new TransactionState[]{
                TransactionState.TASLAK,
                TransactionState.TEKNIK_ARIZA,
                TransactionState.ON_DEGERLENDIRME_OLUMLU
        };
        TransactionState[] offerReceivedStates = new TransactionState[]{
                TransactionState.TEKLIF_ALINDI,
                TransactionState.EVRAKLAR_BANKA_ONAYLI,
                TransactionState.ILK_DOKUMAN_SETI_ONAYLI,
                TransactionState.EVRAK_KONTROL_SURECI,
                TransactionState.EVRAKLAR_TAMAMLANDI,
                TransactionState.EVRAK_BEKLENIYOR,
                TransactionState.BANKA_SECILDI,
                TransactionState.KREDI_KULLANDIRILDI
        };

        List<ApprovalRejectionReportModel> model = new ArrayList<>();
        model.add(new ApprovalRejectionReportModel(date, null));

        Integer validTransactionSize = backOfficeReportHelper.countNotInThisStates(filteredTcknTransactions, notInStates);
        Integer preAppNegativeSize = backOfficeReportHelper.getTransactionsByState(filteredTcknTransactions, new TransactionState[]{TransactionState.ON_DEGERLENDIRME_OLUMSUZ}).size();
        Integer rejectedSize = backOfficeReportHelper.getTransactionsByState(filteredTcknTransactions, new TransactionState[]{TransactionState.REDDEDILDI}).size();
        Integer preAppNegativeAndRejectedSize = preAppNegativeSize + rejectedSize;
        Integer rejectedFromGraySize = backOfficeReportHelper.getTransactionsByState(filteredTcknTransactions, new TransactionState[]{TransactionState.GRIDEN_RED}).size();
        Integer rejectedFromIstihbaratSize = backOfficeReportHelper.getTransactionsByState(filteredTcknTransactions, new TransactionState[]{TransactionState.ISTIHBARATTAN_RED}).size();
        Integer totalRejectedSize = preAppNegativeAndRejectedSize + rejectedFromGraySize + rejectedFromIstihbaratSize;
        Integer graySize = backOfficeReportHelper.getTransactionsByHistoryLogState(filteredTcknTransactions, TransactionState.TAHSIS_DEGERLENDIRME).size();
        List<Transaction> offerReceivedTransactions = backOfficeReportHelper.getTransactionsByState(filteredTcknTransactions, offerReceivedStates);
        Integer offerReceivedFromGraySize = backOfficeReportHelper.countTransactionsPreviousStateByCurrentState(offerReceivedTransactions, TransactionState.TAHSIS_DEGERLENDIRME);
        Integer acceptedSize = offerReceivedTransactions.size() - offerReceivedFromGraySize;
        Integer incomeDocumentQuantity = backOfficeReportHelper.countContainsIncomeDocument(filteredTcknTransactions);

        if (!searchModel.isSummary) {
            model.add(new ApprovalRejectionReportModel("Toplam Başvuru", filteredTcknTransactions.size()));
            model.add(new ApprovalRejectionReportModel("Geçerli Toplam Başvuru", validTransactionSize));
            model.add(new ApprovalRejectionReportModel("Ön Değerlendirme Olumsuz", preAppNegativeSize));
            model.add(new ApprovalRejectionReportModel("1. Oto Red Oranı", BackOfficeReportHelper.format(calculateRate(preAppNegativeSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Değerlendirmeden Red", rejectedSize));
            model.add(new ApprovalRejectionReportModel("2. Oto Red Oranı", BackOfficeReportHelper.format(calculateRate(rejectedSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Oto Red", preAppNegativeAndRejectedSize));
            model.add(new ApprovalRejectionReportModel("Toplam Oto Red Oranı", BackOfficeReportHelper.format(calculateRate(preAppNegativeAndRejectedSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Tahsis Red Adet", rejectedFromGraySize));
            model.add(new ApprovalRejectionReportModel("Tahsis Red Oranı", BackOfficeReportHelper.format(calculateRate(rejectedFromGraySize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("İstihbarattan Red Adet", rejectedFromIstihbaratSize));
            model.add(new ApprovalRejectionReportModel("İstihbarat Red Oranı", BackOfficeReportHelper.format(calculateRate(rejectedFromIstihbaratSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Toplam Red Oranı", BackOfficeReportHelper.format(calculateRate(totalRejectedSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Tahsis Adet", graySize));
            model.add(new ApprovalRejectionReportModel("Tahsis Onay Adet", offerReceivedFromGraySize));
            model.add(new ApprovalRejectionReportModel("Tahsis Onay Oranı", BackOfficeReportHelper.format(calculateRate(offerReceivedFromGraySize, graySize))));
            model.add(new ApprovalRejectionReportModel("Oto Onay Adedi", acceptedSize));
            model.add(new ApprovalRejectionReportModel("Oto Onay Oranı", BackOfficeReportHelper.format(calculateRate(acceptedSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Gelir Belgesi Adedi", incomeDocumentQuantity));
            model.add(new ApprovalRejectionReportModel("Gelir Belgesi Oranı", BackOfficeReportHelper.format(calculateRate(incomeDocumentQuantity, (offerReceivedFromGraySize + acceptedSize)))));
        } else {
            model.add(new ApprovalRejectionReportModel("Sistem (PC) Red Oranı", BackOfficeReportHelper.format(calculateRate(preAppNegativeAndRejectedSize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("Tahsis Red Oranı", BackOfficeReportHelper.format(calculateRate(rejectedFromGraySize, validTransactionSize))));
            model.add(new ApprovalRejectionReportModel("İstihbarat Red Oranı", BackOfficeReportHelper.format(calculateRate(rejectedFromIstihbaratSize, validTransactionSize))));

            double totalRejectedRate = calculateRate(preAppNegativeAndRejectedSize, validTransactionSize)
                    + calculateRate(rejectedFromGraySize, validTransactionSize) + calculateRate(rejectedFromIstihbaratSize, validTransactionSize);
            model.add(new ApprovalRejectionReportModel("Toplam Red Oranı", BackOfficeReportHelper.format(totalRejectedRate)));

            double acceptedRate = 100.0 - totalRejectedRate;
            model.add(new ApprovalRejectionReportModel("Onay Oranı", BackOfficeReportHelper.format(acceptedRate)));
            model.add(new ApprovalRejectionReportModel("Gelir Belgesiz Onay Oranı",
                    BackOfficeReportHelper.format(acceptedRate - (acceptedRate * calculateRate(incomeDocumentQuantity, offerReceivedTransactions.size())) / 100.0)));
        }

        return model;
    }

    private double calculateRate(Integer divided, Integer dividing) {
        double rate;
        if (divided <= 0 || dividing <= 0) {
            rate = 0;
        } else {
            rate = divided * 100.0 / dividing;
        }

        return rate;
    }
}
