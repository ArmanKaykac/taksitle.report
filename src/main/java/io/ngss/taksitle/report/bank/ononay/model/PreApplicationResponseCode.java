package io.ngss.taksitle.report.bank.ononay.model;

public enum PreApplicationResponseCode {

    OK(200),
    ON_DEGERLENDIRME_YOK(300),
    RET(400),
    TEKNIK_ARIZA(500);

    private int id;

    PreApplicationResponseCode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
