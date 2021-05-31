package io.ngss.taksitle.report.bank.database.enums;

//kesin degerlendirme icin
public enum LoanOfferProblemReason {

    Iliskli_Dolayli_basvuru(LoanOfferStatus.RET, "1"),
    Ticari_finansman_müsteri(LoanOfferStatus.RET, "2"),
    Ticari_finansman_suphesi_musteri(LoanOfferStatus.RET, "3"),
    Ticari_finansmana_konu_isyerinde_calisiyor_galerici_rentacar_vb(LoanOfferStatus.RET, "4"),
    Karar_Destek_Sistem_Reddi(LoanOfferStatus.RET, "5"),
    Ticari_finansman_suphesi_bayi(LoanOfferStatus.RET, "6"),
    Gelir_belgesi_supheli(LoanOfferStatus.RET, "7"),
    Sistemin_farkli_amaclar_icin_kullanilmasi(LoanOfferStatus.RET, "8"),
    Musteri_dogrulama_sonucu_uygun_degil(LoanOfferStatus.RET, "9"),
    Riskli_urun_adedi(LoanOfferStatus.RET, "10"),
    Arac_yuksek_hasarli(LoanOfferStatus.RET, "11"),
    Esin_tasit_kredileri_yuksek_adette(LoanOfferStatus.RET, "12"),
    Esin_ticari_finansmani_suphesi(LoanOfferStatus.RET, "13"),

    Dis_kurum(LoanOfferStatus.TEKNIK_ARIZA, "6"),
    Musteri_yaratmada_hata(LoanOfferStatus.TEKNIK_ARIZA, "7"),
    Bayi_bulunmuyor(LoanOfferStatus.TEKNIK_ARIZA, "8"),
    Bayiye_tanimli_kampanya_yok_veya_birden_fazla_tanimli(LoanOfferStatus.TEKNIK_ARIZA, "9"),
    PC_call_hatasi(LoanOfferStatus.TEKNIK_ARIZA, "10"),
    Skorlama_hatasi(LoanOfferStatus.TEKNIK_ARIZA, "11"),
    Tanimli_risk_veya_tavsiye_kodu_yok(LoanOfferStatus.TEKNIK_ARIZA, "12"),
    Vade_veya_tutar_minmax_deger_araliklarinin_disinda(LoanOfferStatus.TEKNIK_ARIZA, "13"),
    Karsılıgı_olmayan_problem(LoanOfferStatus.TEKNIK_ARIZA, "14532156465"); //Bankanın donudslerinde karsılanamayan problem reason

    private LoanOfferStatus loanOfferStatus;
    private String problemCode;

    LoanOfferProblemReason(LoanOfferStatus loanOfferStatus, String problemCode) {
        this.problemCode = problemCode;
        this.loanOfferStatus = loanOfferStatus;
    }

    public static LoanOfferProblemReason getByProblemCodeAndResponseCode(String problemCode, LoanOfferStatus loanOfferStatus) {
        for (LoanOfferProblemReason pr : LoanOfferProblemReason.values()) {

            if (problemCode.equals(pr.problemCode) && loanOfferStatus.equals(pr.loanOfferStatus)) {
                return pr;
            }
        }
        return LoanOfferProblemReason.Karsılıgı_olmayan_problem;
    }
}

