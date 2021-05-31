package io.ngss.taksitle.report.bank.database.enums;

public enum CreditType {

    CAR("TASIT"),
    PERSONAL_FINANCE("IHTIYAC");

    private String definition;

    private CreditType(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}