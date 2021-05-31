package io.ngss.taksitle.report.bank.integration.model;
public enum DocumentRejectCode {

    IMZA_EKSIK("İmza Eksik",1,0),
    BELGE_OKUNMUYOR("Belge Okunmuyor",2,1),
    BORDRO_KASE_IMZA_YOK("Bordro Kaşe/İmza Yok",3,2),
    BELGE_ESKI("Belge Eski",4,3),
    BILGILER_UYUMSUZ("Bilgiler Uyumsuz",5,4),
    BELGEDE_SILINTI_KAZINTI_SUPHESI("Belgede silinti/kazıntı şüphesi",6,5),
    BELGEDEKI_GUVENLIK_UNSURLARI_SORUNLU("Belgedeki güvenlik unsurları sorunlu",7,6),
    BASKA_KISIYE_AIT_BELGE("Başka kişiye ait belge",8,7),
    BASKA_URUN_ARACA_AIT_BELGE("Başka ürün/araca ait belge",9,8),
    SOZLESME_TUM_SAYFALARDA_BORCLU_IMZASI_OLMALI("Sözleşme tüm sayfalarda borçlu imzası olmalı",10,9),
    SOZLESME_SAYFALARI_EKSIK("Sözleşme sayfaları eksik",11,10),
    SOZLESME_ONCESI_FORMDA_IMZA_EKSIK("Sözleşme öncesi bilgi formunun her sayfasında borçlu imzası olmalı",12,11),
    SOZLESME_ONCESI_SAYFA_EKSIK("Sözleşme öncesi bilgi formu sayfaları eksik",13,12),
    ODEME_PLANI_HER_SAYFA_IMZA_EKSIK("Ödeme planında her sayfada borçlu ve şube yetkilisi/bayi imzası eksik",14,13),
    ODEME_PLANI_SAYFA_EKSIK("Ödeme planı sayfaları eksik",15,14),
    GARANTORLUK_BELGE_FIRMA_KASE_EKSIK("Garantörlük belgesinde firma kaşesi yok",16,15),
    GARANTORLUK_BELGE_MOTOR_SASI_OKUNAKSIZ("Garantörlük belgesinde motor ve şasi bilgileri okunaksız",17,16),
    PROFORMA_FATURA_MODEL_RENK_OKUNAKSIZ("Proforma faturada model ve renk bilgisi okunaksız/eksik",18,17),
    PROFORMA_FATURA_MOTOR_SASI_OKUNAKSIZ("Proforma faturada motor ve şasi bilgileri okunaksız/eksik",19,18),
    PROFORMA_FATURA_TUTAR_OKUNAKSIZ("Proforma faturada tutar bilgisi okunaksız/eksik",20,19),
    MUSTERI_BEYAN_EL_YAZISI_EKSIK("Müşteri beyanı kendi el yazısı ile olmalıdır.",21,20),
    BAYI_KASE_IMZA_EKSIK("Bayi kaşe imza eksikliği",22,21),
    DUMMY("Banka hata kodu bildirmedi",20,19);
    private String definition;
    private int id;
    private int order;

    DocumentRejectCode(String definition,int id,int order){
        this.definition=definition;
        this.id=id;
        this.order=order;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public static DocumentRejectCode fromId(int id) {
        for (DocumentRejectCode type : DocumentRejectCode.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static DocumentRejectCode fromOrder(int order) {
        for (DocumentRejectCode type : DocumentRejectCode.values()) {
            if (type.getOrder() == order) {
                return type;
            }
        }
        return null;
    }
}
