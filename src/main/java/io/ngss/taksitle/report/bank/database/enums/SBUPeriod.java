package io.ngss.taksitle.report.bank.database.enums;

public enum SBUPeriod {

    MONTHLY("AYLIK"),
    EVERY_THREE_MONTHS("UC AYLIK"),
    YEARLY("YILLIK");


    private String definition;

    private SBUPeriod(String definition){this.definition=definition;}

    public String getString(){return this.definition;}


}
