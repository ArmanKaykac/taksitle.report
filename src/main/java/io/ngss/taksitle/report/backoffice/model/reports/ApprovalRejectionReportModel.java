package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class ApprovalRejectionReportModel extends ReportModel {

    private String key;
    private Object value;

    public ApprovalRejectionReportModel(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
