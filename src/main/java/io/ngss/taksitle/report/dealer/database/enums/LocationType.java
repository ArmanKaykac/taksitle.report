package io.ngss.taksitle.report.dealer.database.enums;

public enum LocationType {
    MALL("AVM"),
    ONSTREET("CADDE UZERI"),
    INDUSTRY_AREA("SANAYI SITESI"),
    BRANCH_ROAD("ARA SOKAK");

    private String definition;

    private LocationType(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}
