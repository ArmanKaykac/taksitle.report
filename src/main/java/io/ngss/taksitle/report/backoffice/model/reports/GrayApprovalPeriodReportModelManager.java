package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.model.GrayApprovalPeriodReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.GrayApprovalPeriodReportSearchModel;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.test.TransactionRepositoryNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrayApprovalPeriodReportModelManager extends BaseReportModel<GrayApprovalPeriodReportModel, GrayApprovalPeriodReportSearchModel> {

    @Autowired
    private TransactionRepositoryNewDB transactionRepository;

    private long greaterThanThreshold = 0;

    private int threshold = 20; // in minutes

    @Override
    public List<GrayApprovalPeriodReportModel> filterModel(GrayApprovalPeriodReportSearchModel searchModel) {

        if (searchModel.startDate == null)
            return null;

        List<Transaction> transactions = transactionRepository.findAllByCreatedAtIsAfter(new Date(searchModel.startDate));
        if (transactions == null)
            return null;

        greaterThanThreshold = 0;

        if (searchModel.endDate != null) {
            transactions = transactions.stream()
                    .filter(transaction -> transaction.getCreatedAt().before(new Date(searchModel.endDate)))
                    .collect(Collectors.toList());
        }

        List<Transaction> completedTransactions = new ArrayList<>();
        transactions.forEach(transaction -> {
            List<TransactionHistoryLog> historyLogs = transaction.getTransactionHistoryLogs();
            if (historyLogs.stream()
                    .anyMatch((historyLog -> historyLog.getTransactionState().equals(TransactionState.GRIDEN_RED)
                            || historyLog.getTransactionState().equals(TransactionState.TEKLIF_ALINDI)))) {
                completedTransactions.add(transaction);
            }
        });

        List<Transaction> grayCompletedTransactions = new ArrayList<>();
        completedTransactions.forEach(transaction -> {
            List<TransactionHistoryLog> historyLogs = transaction.getTransactionHistoryLogs();
            if (historyLogs.stream()
                    .anyMatch((historyLog -> historyLog.getTransactionState().equals(TransactionState.TAHSIS_DEGERLENDIRME)
                            || historyLog.getTransactionState().equals(TransactionState.ISTIHBARAT_DEGERLENDIRME)))) {
                grayCompletedTransactions.add(transaction);
            }
        });


        List<GrayApprovalPeriodReportModel> model = new ArrayList<>();
        for (Transaction transaction : grayCompletedTransactions) {
            GrayApprovalPeriodReportModel item = new GrayApprovalPeriodReportModel(
                    transaction.getToken().toString(),
                    transaction.getDealer().getName(),
                    "");

            List<TransactionHistoryLog> historyLogs = transaction.getTransactionHistoryLogs();
            Long grayTime = historyLogs.stream().filter(historyLog ->
                    historyLog.getTransactionState().equals(TransactionState.TAHSIS_DEGERLENDIRME)
                            || historyLog.getTransactionState().equals(TransactionState.ISTIHBARAT_DEGERLENDIRME))
                    .findFirst().get().getDate();

            Long responseTime = historyLogs.stream().filter(historyLog ->
                    historyLog.getTransactionState().equals(TransactionState.TEKLIF_ALINDI)
                            || historyLog.getTransactionState().equals(TransactionState.GRIDEN_RED))
                    .findFirst().get().getDate();

            LocalDate grayDate =
                    Instant.ofEpochMilli(grayTime).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate responseDate =
                    Instant.ofEpochMilli(responseTime).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDateTime grayDateTime =
                    Instant.ofEpochMilli(grayTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime responseDateTime =
                    Instant.ofEpochMilli(responseTime).atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (responseDate.isEqual(grayDate)) {
                item.setPeriod(calculatePeriod(grayDateTime, responseDateTime, 0));
                model.add(item);
                continue;
            }

            final int dayStartHour = 9;
            final int dayStartMin = 0;
            final int dayEndHour = 18;

            if (grayDate.plusDays(1).isEqual(responseDate)
                    && grayDateTime.getHour() >= dayEndHour
                    && responseDateTime.getHour() < dayStartHour) {
                item.setPeriod("0:0:15");
                model.add(item);
                continue;
            }

            if (grayDateTime.getHour() >= dayEndHour) {
                grayDateTime = grayDateTime.plusDays(1);
                grayDateTime = grayDateTime.withHour(dayStartHour);
                grayDateTime = grayDateTime.withMinute(dayStartMin);

                if (responseDateTime.getHour() < dayStartHour) {
                    responseDateTime = responseDateTime.withHour(dayStartHour);
                    responseDateTime = responseDateTime.withMinute(dayStartMin);
                }
            }

            if (grayDateTime.getHour() < dayStartHour) {
                grayDateTime = grayDateTime.withHour(dayStartHour);
                grayDateTime = grayDateTime.withMinute(dayStartMin);
            }

            int days = 0;
            while (!grayDateTime.toLocalDate().isEqual(responseDateTime.toLocalDate())) {
                days++;
                grayDateTime = grayDateTime.plusDays(1);
            }

            item.setPeriod(calculatePeriod(grayDateTime, responseDateTime, days));
            model.add(item);
        }

        model.add(new GrayApprovalPeriodReportModel(
                "Uyumluluk",
                "Oranı:",
                BackOfficeReportHelper.format(greaterThanThreshold * 100.0 / grayCompletedTransactions.size())));

        return model;
    }

    private String calculatePeriod(LocalDateTime begin, LocalDateTime end, int days) {
        long hours = begin.until(end, ChronoUnit.HOURS);
        long minutes = begin.until(end, ChronoUnit.MINUTES);
        minutes -= hours * 60;

        while (minutes < 0) {
            hours--;
            minutes = 60 + minutes;
        }

        while (hours < 0) {
            days--;
            hours = 24 + hours;
        }

        if (days > 0
                || hours > 0
                || minutes > threshold) {
            greaterThanThreshold++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(days)
                .append(":")
                .append(hours)
                .append(":")
                .append(minutes);

        return stringBuilder.toString();
    }
}
