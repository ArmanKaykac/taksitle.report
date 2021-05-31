package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateLogModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.OtosorReportSearchModel;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.test.helper.BackOfficeReportHelperNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TcknReportManager extends BaseReportModel<TransactionStateLogModel, OtosorReportSearchModel> {

    @Autowired
    private BackOfficeReportHelperNewDB backOfficeReportHelper;


    public List<TransactionStateLogModel> filterModel(OtosorReportSearchModel searchModel) {
        List<Transaction> tcknTransactions = backOfficeReportHelper.getTcknTransactions(searchModel.startDate, searchModel.endDate);
        if (tcknTransactions == null)
            return null;

        return backOfficeReportHelper.prepareLogReport(tcknTransactions);
    }
}
