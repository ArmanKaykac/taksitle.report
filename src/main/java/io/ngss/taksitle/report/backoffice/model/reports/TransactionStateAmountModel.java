package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.CountByBankModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;
import io.ngss.taksitle.report.dealer.TransactionState;

import java.util.Map;

public class TransactionStateAmountModel extends ReportModel {
    public String dealerName;
    public String bankName;
    public Double teklifAlindiAmount = 0d;
    public Double kullandirildiAmount = 0d;
    public String source;
    public String portfolioManager;
    public Long code;
    public String city;

    private Map<TransactionState, CountByBankModel> bankMap;

    public TransactionStateAmountModel() {
    }

    public void setBankMap(Map<TransactionState, CountByBankModel> bankMap) {
        this.bankMap = bankMap;
        //this.teklifAlindiCount = this.transactionStateLongMap.get(TransactionState.TEKLIF_ALINDI).longValue();
        //this.kullandirildiCount = this.transactionStateLongMap.get(TransactionState.KREDI_KULLANDIRILDI).longValue();
    }
}
