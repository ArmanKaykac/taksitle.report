package io.ngss.taksitle.report.transaction.enums;


public enum DeliveryType {
    HOMEADDRESS(1),
    WORKADDRESS(2),
    CUSTOMER(3),
    OTHERADDRESS(4);

    private int sekerId;

    DeliveryType(int sekerId) {
        this.sekerId = sekerId;
    }

    public int getSekerId() {
        return sekerId;
    }

    public void setSekerId(int sekerId) {
        this.sekerId = sekerId;
    }
}
