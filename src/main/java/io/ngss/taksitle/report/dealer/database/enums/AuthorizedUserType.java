package io.ngss.taksitle.report.dealer.database.enums;

public enum AuthorizedUserType {

    OWNER("BAYI SAHIBI"),
    GENERAL_MANAGER("GENEL MD/YRD"),
    SALES_REPRESENTATIVE("SATIS TEMSILCISI"),
    CREDIT_AUTHORITY("KREDI YETKILISI"),
    OTHER("DIGER"),
    ADMIN("ADMIN"),
    USER("KULLANICI");

    private String definition;

    private AuthorizedUserType(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}
