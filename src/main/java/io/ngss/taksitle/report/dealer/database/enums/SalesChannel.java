package io.ngss.taksitle.report.dealer.database.enums;

public enum SalesChannel {

    STORE_3RD_PARTY("Mağaza-3. Şahıs"),
    STORE_DEALER_OWNED("Mağaza-Bayi-Kendi Noktası"),
    STORE_DEALER_AUTHORIZED("Mağaza-Bayi-Yetkili Bayi"),
    STORE_DEPTSTORE_OWNED("Mağaza-Dept-Store-Kendi Noktası"),
    STORE_DEPTSTORE_FRANCHISE("Mağaza-Dept-Store-Franchise"),
    WEB("WEB");

    private String definition;

    private SalesChannel(String definition){this.definition=definition;}

    public String getString(){return this.definition;}
}
