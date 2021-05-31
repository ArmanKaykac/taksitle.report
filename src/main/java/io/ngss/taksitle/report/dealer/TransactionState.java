package io.ngss.taksitle.report.dealer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TransactionState {

    TASLAK(0, 0, "99"),
    ON_DEGERLENDIRME_OLUMLU(8, 1, "1"),
    ON_DEGERLENDIRME_OLUMSUZ(9, 2, "2"),
    SIPARIS_GIRILIYOR(10, 3, "19"),
    BASVURU_YAPILDI(20, 4, "12"),
    TEKLIF_ALINDI(25, 5, "20"),
    BANKA_SECILDI(30, 6, "3"),
    EVRAK_BEKLENIYOR(35, 7, "6"),
    EVRAKLAR_TAMAMLANDI(60, 8, "81"),
    KREDI_KULLANDIRILDI(70, 9, "10"),
    URUN_KARGOYA_VERILDI(80, 10, "82"),
    URUN_TESLIM_EDILDI(90, 11, "15"),
    URUNLER_STOKTA_YOK(100, 12, "83"),
    EVRAK_MUSTERI_OLMA_SURECI_TAMAMLANDI(50, 13, "9"),
    EVRAK_KONTROL_SURECI(40, 14, "7"),
    IPTAL(1, 15, "13"),
    GECERSIZ(2, 16, "84"),
    ZAMAN_ASIMI(3, 17, "14"),
    REDDEDILDI(4, 18, "21"),
    IADE_TALEBI(5, 19, "22"),
    IADE_TAMAMLANDI(6, 20, "23"),
    ALL(7, 21, "84"),
    ILK_DOKUMAN_SETI_ONAYLI(45, 22, "85"),
    EVRAKLAR_BANKA_ONAYLI(46, 23, "86"),
    TEKNIK_ARIZA(55, 24, "25"),
    GRIDEN_RED(56, 25, "26"),
    TAHSIS_DEGERLENDIRME(57, 26, "100"),
    ISTIHBARATTAN_RED(58, 27, "27"),
    ISTIHBARAT_DEGERLENDIRME(59, 28, "28"),
    DEGERLENDIRME(60, 1000000, ""),
    SIPARIS_TAMAMLANDI(11, 30, ""),
    IPTAL_TALEBI(61, 31, "");

    private int id;
    private int order;
    private String sekerId;

    private TransactionState(int id, int order, String sekerId) {
        this.id = id;
        this.order = order;
        this.sekerId = sekerId;
    }

    public static TransactionState fromId(int id) {
        for (TransactionState type : TransactionState.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static TransactionState fromOrder(int order) {
        for (TransactionState type : TransactionState.values()) {
            if (type.getOrder() == order) {
                return type;
            }
        }
        return null;
    }

    public static TransactionState fromSekerId(String sekerId) {
        for (TransactionState type : TransactionState.values()) {
            if (type.getSekerId().equals(sekerId)) {
                return type;
            }
        }
        return null;
    }

    public static ArrayList<String> getSekerIdsRedStates() {
        ArrayList<String> sekerIds = new ArrayList<String>();
        sekerIds.add(TransactionState.GRIDEN_RED.sekerId);
        sekerIds.add(TransactionState.ISTIHBARATTAN_RED.sekerId);
        return sekerIds;
    }

    public static List<TransactionState> preApprovedCancelStates() {
        TransactionState[] preApprovedCancel = new TransactionState[]{
                ON_DEGERLENDIRME_OLUMLU,
                SIPARIS_GIRILIYOR,
                SIPARIS_TAMAMLANDI,
                TAHSIS_DEGERLENDIRME,
                ISTIHBARAT_DEGERLENDIRME,
                IPTAL_TALEBI
        };

        return Arrays.asList(preApprovedCancel);
    }

    public static List<TransactionState> offeredCancelStates() {
        TransactionState[] offeredCancel = new TransactionState[]{
                BANKA_SECILDI,
                TEKLIF_ALINDI,
                IPTAL_TALEBI,
                TAHSIS_DEGERLENDIRME,
                ISTIHBARAT_DEGERLENDIRME,
                EVRAK_BEKLENIYOR,
                EVRAKLAR_TAMAMLANDI
        };

        return Arrays.asList(offeredCancel);
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getSekerId() {
        return sekerId;
    }
}
