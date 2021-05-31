package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class TransactionStateBaseDealerModel extends ReportModel {

    public String dealerName;
    public Long otherStateCount = 0l;
    public Long onDegerlendirmeOlumsuz = 0l;
    public Long siparisCount = 0l;
    public Long teklifAlindiCount = 0l;
    public Long bankaSecildiCount = 0l;
    public Long redCount = 0l;
    public Long kullandirildiCount = 0l;
    public Long taslakCount = 0l;
    public String source;
    public String portfolioManager;
    public Long code;
    public String city;
}
