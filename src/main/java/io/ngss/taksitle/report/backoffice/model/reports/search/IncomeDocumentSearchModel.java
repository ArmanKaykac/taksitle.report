package io.ngss.taksitle.report.backoffice.model.reports.search;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;

public class IncomeDocumentSearchModel extends BaseSearchModel {

    public Long dealerId;
    public Long startDate;
    public Long endDate;
    public Long financialTypeId;
}
