package io.ngss.taksitle.report.bank.database.enums;

public enum LoanStatus {

    ONAY(1),
    RED(2),
    SISTEMSEL_SORUN(3);

    private int id;

    LoanStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static LoanStatus fromId(int id) {
        for (LoanStatus status: LoanStatus.values()) {
            if (id == status.id) {
                return status;
            }
        }
        return null;
    }
}
