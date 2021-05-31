package io.ngss.taksitle.report.security.model;

public class SecurityTokenModel {
    public SecurityTokenModel(Long token, boolean isUsed) {
        this.token = token;
        this.isUsed = isUsed;
    }

    public Long token;
    public boolean isUsed;
}
