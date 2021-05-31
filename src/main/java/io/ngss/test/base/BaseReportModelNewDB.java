package io.ngss.test.base;

import io.ngss.taksitle.report.backoffice.model.reports.base.BaseSearchModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BaseReportModelNewDB<T extends ReportModelNewDB, K extends BaseSearchModelNewDB> {

    public abstract List<T> filterModel(K searchModel);
}
