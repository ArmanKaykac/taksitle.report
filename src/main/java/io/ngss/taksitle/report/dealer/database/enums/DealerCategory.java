package io.ngss.taksitle.report.dealer.database.enums;

public enum DealerCategory {

    UPPER_SEGMENT("UST SEGMENT BAYI"),
    GOOD_PLUS_SEGMENT("IYI BAYI (+)"),
    GOOD_MINUS_SEGMENT("IYI BAYI (-)"),
    MEDIUM_PLUS_SEGMENT("ORTA BAYI (+)"),
    MEDIUM_MINUS_SEGMENT("ORTA BAYI (-)"),
    NEGATIVE("CALISILMAYACAK");

    private String definition;

    private DealerCategory(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}