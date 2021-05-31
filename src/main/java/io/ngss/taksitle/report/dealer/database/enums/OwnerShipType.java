package io.ngss.taksitle.report.dealer.database.enums;

public enum OwnerShipType {

    RENT("KIRA"),
    OWNED("KENDINE/AILEYE AIT"),
    COMPANY("FIRMAYA AIT");

    private String definition;

    private OwnerShipType(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}
