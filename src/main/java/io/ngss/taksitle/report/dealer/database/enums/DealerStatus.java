package io.ngss.taksitle.report.dealer.database.enums;

public enum DealerStatus {

    OPEN("ACIK"),
    CLOSED("KAPALI"),
    CANDIDATE("ADAY BAYII");

    private String definition;

    private DealerStatus(String definition){this.definition=definition;}

    public String getString(){return this.definition;}


}
