package io.ngss.taksitle.report.bank.ononay.model;

public enum ProblemReason {

    Iliskli_Dolayli_basvuru(PreApplicationResponseCode.RET, "1"),
    Ticari_finansman_müsteri(PreApplicationResponseCode.RET, "2"),
    Ticari_finansman_suphesi_musteri(PreApplicationResponseCode.RET, "3"),
    Ticari_finansmana_konu_isyerinde_calisiyor_galerici_rentacar_vb(PreApplicationResponseCode.RET, "4"),
    Karar_Destek_Sistem_Reddi(PreApplicationResponseCode.RET, "5"),
    Ticari_finansman_suphesi_bayi(PreApplicationResponseCode.RET, "6"),
    Gelir_belgesi_supheli(PreApplicationResponseCode.RET, "7"),
    Sistemin_farkli_amaclar_icin_kullanilmasi(PreApplicationResponseCode.RET, "8"),
    Musteri_dogrulama_sonucu_uygun_degil(PreApplicationResponseCode.RET, "9"),
    Riskli_urun_adedi(PreApplicationResponseCode.RET, "10"),
    Arac_yuksek_hasarli(PreApplicationResponseCode.RET, "11"),
    Esin_tasit_kredileri_yuksek_adette(PreApplicationResponseCode.RET, "12"),
    Esin_ticari_finansmani_suphesi(PreApplicationResponseCode.RET, "13"),
    //Olumsuz_sektor_gelismesi(PreApplicationResponseCode.RET, "14"),
    //Yasal_mevzuat(PreApplicationResponseCode.RET, "15"),
    //Isyeri_dogrulanamadı(PreApplicationResponseCode.RET, "16"),
    //SGK_Sorgusu_Olumsuz(PreApplicationResponseCode.RET, "17"),
    //Iliskili_Dolayli_Basvuru(PreApplicationResponseCode.RET, "18"),
    //Garantorluk_kabul_edilmedi(PreApplicationResponseCode.RET, "19"),

    KPS_calısmıyor(PreApplicationResponseCode.TEKNIK_ARIZA, "1"),
    APS_calısmiyor(PreApplicationResponseCode.TEKNIK_ARIZA, "2"),
    KKB_calismiyor(PreApplicationResponseCode.TEKNIK_ARIZA, " 3"),
    Sistemsel_sorun(PreApplicationResponseCode.TEKNIK_ARIZA, "4"),
    Tanimsiz_urun(PreApplicationResponseCode.TEKNIK_ARIZA, "5"), //Bankanın dönüslerinde karsılanamayan problem reason
    Dis_kurum(PreApplicationResponseCode.TEKNIK_ARIZA, "6"),
    Musteri_yaratmada_hata(PreApplicationResponseCode.TEKNIK_ARIZA, "7"),
    Bayi_bulunmuyor(PreApplicationResponseCode.TEKNIK_ARIZA, "8"),
    Bayiye_tanimli_kampanya_yok_veya_birden_fazla_tanimli(PreApplicationResponseCode.TEKNIK_ARIZA, "9"),
    PC_call_hatasi(PreApplicationResponseCode.TEKNIK_ARIZA, "10"),
    Skorlama_hatasi(PreApplicationResponseCode.TEKNIK_ARIZA, "11"),
    Tanimli_risk_veya_tavsiye_kodu_yok(PreApplicationResponseCode.TEKNIK_ARIZA, "12"),
    Vade_veya_tutar_minmax_deger_araliklarinin_disinda(PreApplicationResponseCode.TEKNIK_ARIZA, "13");

    private String problemCode;
    private PreApplicationResponseCode responseCode;

    ProblemReason(PreApplicationResponseCode responseCode, String problemCode) {
        this.problemCode = problemCode;
        this.responseCode = responseCode;
    }

    public static ProblemReason getByProblemCodeAndResponseCode(String problemCode, PreApplicationResponseCode responseCode) {
        for (ProblemReason pr : ProblemReason.values()) {

            if (problemCode.equals(pr.problemCode) && responseCode.equals(pr.responseCode)) {
                return pr;
            }
        }
        return ProblemReason.Tanimsiz_urun;
    }

}
