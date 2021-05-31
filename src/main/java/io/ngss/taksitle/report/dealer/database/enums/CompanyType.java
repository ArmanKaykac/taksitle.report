package io.ngss.taksitle.report.dealer.database.enums;

public enum CompanyType {
    PERSONAL("KISISEL"),
    UNLIMITED("KOLLEKTIF SIRKET"),
    COMMANDITE("KOMANDIT"),
    LIMITED("LIMITED"),
    INCORPORATED("ANONIM");

    private String definition;

    private CompanyType(String definition){this.definition=definition;}

    public String getString(){return this.definition;}

}
