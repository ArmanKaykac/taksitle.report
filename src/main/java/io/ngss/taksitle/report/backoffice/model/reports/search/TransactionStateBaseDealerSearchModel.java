package io.ngss.taksitle.report.backoffice.model.reports.search;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;

public class TransactionStateBaseDealerSearchModel extends BaseSearchModel {

    public Long startDate;
    public Long endDate;
    public Long dealerId;
    public Long financialTypeId;
}
