package io.ngss.taksitle.report.backoffice.model.reports.search;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;

public class TransactionStateLogSearchModel extends BaseSearchModel {

    public Integer transactionToken;
    public Long dealerId;
    public Long startDate;
    public Long endDate;
    public Integer financialTypeId;
}
