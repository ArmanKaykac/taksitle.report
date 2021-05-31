package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;
import io.ngss.taksitle.report.dealer.TransactionState;

public class TransactionStateCountModel extends ReportModel {

    public TransactionStateCountModel(Long count, TransactionState transactionState) {
        this.count = count;
        this.transactionState = transactionState;
    }

    public Long count;
    public TransactionState transactionState;
}
