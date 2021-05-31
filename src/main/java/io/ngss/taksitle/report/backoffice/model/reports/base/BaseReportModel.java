package io.ngss.taksitle.report.backoffice.model.reports.base;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public abstract class BaseReportModel<T extends ReportModel, K extends BaseSearchModel> {

    public abstract List<T> filterModel(K searchModel);
}
