package io.ngss.taksitle.report.dealer.database.enums;
public enum WhomToBuy {
    FAMILY(1),
    RELATIVES(2),
    CUSTOMER(3),
    ENGAGED(4),
    OTHER(5);

    private int sekerId;

    WhomToBuy(int sekerId) {
        this.sekerId = sekerId;
    }

    public int getSekerId() {
        return sekerId;
    }

    public void setSekerId(int sekerId) {
        this.sekerId = sekerId;
    }
}
