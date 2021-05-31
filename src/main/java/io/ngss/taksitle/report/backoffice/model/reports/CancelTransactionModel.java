package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.transaction.database.Transaction;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class CancelTransactionModel extends ReportModel {
    public Integer token;
    public Date transactionCreatedAt;
    public String dealerName;
    public Long dealerId;
    public String customerName;
    public String bankName;
    public TransactionState transactionState;
    public String cancellationReason;
    public String cancelExplanation;

    public CancelTransactionModel(Integer token,
                                  Date transactionCreatedAt,
                                  String dealerName,
                                  Long dealerId,
                                  String customerName,
                                  String bankName,
                                  TransactionState transactionState,
                                  String cancellationReason,
                                  String cancelExplanation) {
        this.token = token;
        this.transactionCreatedAt = transactionCreatedAt;
        this.dealerName = dealerName;
        this.dealerId = dealerId;
        this.customerName = customerName;
        this.bankName = bankName;
        this.transactionState = transactionState;
        this.cancellationReason = cancellationReason;
        this.cancelExplanation = cancelExplanation;
    }

    public static CancelTransactionModel convertToCancelTransactionModel(Transaction transaction) {
        if (transaction == null || transaction.getDealer() == null) return null;
        Dealer dealer = transaction.getDealer();

        AtomicReference<String> selectedBankName = new AtomicReference<>("");
        if (transaction.getLoanOffers() != null) {
            transaction.getLoanOffers().stream().filter(x -> x.isSelectedAndNonCanceled()).findFirst().ifPresent(x -> {
                if (x.getBank() != null)
                    selectedBankName.set(x.getBank().getName());
            });
        }
        return new CancelTransactionModel(transaction.getToken(),
                transaction.getCreatedAt(),
                dealer.getName(),
                dealer.getId(),
                transaction.getCustomer().getCustomerDetails().getName(),
                selectedBankName.get(),
                transaction.getTransactionState(),
                transaction.getCancellationReason(),
                transaction.cancelExplanation);
    }
}
