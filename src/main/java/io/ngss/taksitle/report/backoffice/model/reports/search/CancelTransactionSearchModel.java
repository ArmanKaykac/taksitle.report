package io.ngss.taksitle.report.backoffice.model.reports.search;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;

public class CancelTransactionSearchModel extends BaseSearchModel {

    public Long dealerId;
    public Integer transactionToken;
}
