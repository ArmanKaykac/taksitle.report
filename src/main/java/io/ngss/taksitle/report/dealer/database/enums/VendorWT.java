package io.ngss.taksitle.report.dealer.database.enums;

public enum VendorWT {

    MAIN_VENDOR("ANA_MARKA"),
    ALT_VENDOR("YAN_MARKA");

    private String definition;

    private VendorWT(String definition){this.definition=definition;}

    public String getString(){return this.definition;}

}
