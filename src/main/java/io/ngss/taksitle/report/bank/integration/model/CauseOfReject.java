package io.ngss.taksitle.report.bank.integration.model;

public enum CauseOfReject {

    BANKAMIZ_KANUNI_TAKIP_KAYDI("Bankamız kanuni takip kaydı",1,0),
    OLUMSUZ_ISTIHBARAT_ALINMASI("Olumsuz istihbarat alınması",2,1),
    POLITIKA_NEDENIYLE_RED("Politika nedeniyle red",3,2),
    KREDIBILITESI_YETERSIZ("Kredibilitesi yetersiz",4,3),
    KARAR_DESTEK_SISTEM_REDDI("Karar Dstek Sistem Reddi",5,4),
    TEMINAT_EKSIKLIGI("Teminat eksikliği",6,5),
    MUSTERI_DOGRULANAMADI("Müşteri doğrulanamadı",7,6),
    MEVCUT_BASVURU_NEDENIYLE_OTOMATIK_IPTAL("Mevcut Başvuru nedeniyle otomatik iptal",8,7),
    KIMLIK_TEYIDI_OLUMSUZ("Kimlik Teyidi Olumsuz",9,8),
    MALI_VERI_VE_GELIR_YETERSIZLIGI("Mali veri ve gelir yetersizliği",10,9),
    NEGATIF_RM_KAYDI("Negatif RM kaydı",11,10),
    OLUMSUZ_KKB_KAYDI("Olumsuz KKB kaydı",12,11),
    BORCLULUGU_YUKSEK("Borçluluğu yüksek",13,12),
    OLUMSUZ_SEKTOR_GELISMESI("Olumsuz sektör gelişmesi",14,13),
    YASAL_MEVZUAT("Yasal mevzuat",15,14),
    ISYERI_DOGRULANAMADI("İşyeri doğrulanamadı",16,15),
    SGK_SORGUSU_OLUMSUZ("SGK Sorgusu Olumsuz",17,16),
    ILISKILI_DOLAYLI_BASVURU("İlişkili/Dolaylı Başvuru",18,17),
    GARANTORLUK_KABUL_EDILMEDI("Garantörlük kabul edilmedi",19,18),
    MUSACG_LISTESINDE_OLDUGU_ICIN_RED("MUSACG listesinde olduğu için red",20,19);
    private String definition;
    private int id;
    private int order;

    CauseOfReject(String definition,int id,int order){
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

    public static CauseOfReject fromId(int id) {
        for (CauseOfReject type : CauseOfReject.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static CauseOfReject fromOrder(int order) {
        for (CauseOfReject type : CauseOfReject.values()) {
            if (type.getOrder() == order) {
                return type;
            }
        }
        return null;
    }
}
