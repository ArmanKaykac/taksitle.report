package io.ngss.taksitle.report.backoffice.model.reports.search;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;

public class TransactionLastStateCountSearchModel extends BaseSearchModel {

    public Long startDate;
    public Long endDate;
    public Boolean groupedByDealer;
    public Boolean onlyDealer;
    public Long dealerId;
    public Integer financialTypeId;
}
