package io.ngss.taksitle.report.bank.database.enums;

import io.ngss.taksitle.report.bank.ononay.model.PreApplicationResponseCode;

import java.util.Arrays;
import java.util.List;

public enum BankTransactionState {

    ON_DEGERLENDIRME_OLUMLU,
    ON_DEGERLENDIRME_OLUMSUZ,
    ON_DEGERLENDIRME_YOK,

    TAHSIS_DEGERLENDIRME,
    ISTIHBARAT_DEGERLENDIRME,

    ONAY,

    RED,
    GRI_ALANDAN_RED,
    ISTIHBARATTAN_RED,

    TEKNIK_ARIZA,

    TEKLIF_VERILDI,
    TEKLIF_KABUL_EDILDI,
    BANKA_SECILDI,
    EKSIK_EVRAK,
    KULLANDIRILDI,

    IPTAL;


    public static BankTransactionState getBankStateByResponseCode(PreApplicationResponseCode responseCode) {
        if (responseCode == PreApplicationResponseCode.OK)
            return ON_DEGERLENDIRME_OLUMLU;

//        if (responseCode == PreApplicationResponseCode.ON_DEGERLENDIRME_YOK)
//            return ON_DEGERLENDIRME_YOK;

        if (responseCode == PreApplicationResponseCode.RET)
            return ON_DEGERLENDIRME_OLUMSUZ;

        return TEKNIK_ARIZA;
    }

    public static boolean isOffered(BankTransactionState bankTransactionState) {
        List<BankTransactionState> states = Arrays.asList(TEKLIF_VERILDI,
                TEKLIF_KABUL_EDILDI,
                EKSIK_EVRAK,
                KULLANDIRILDI);

        return states.contains(bankTransactionState);
    }

    public static boolean isCancelable(BankTransactionState bankTransactionState) {
        List<BankTransactionState> states = Arrays.asList(
                ON_DEGERLENDIRME_OLUMSUZ,
                RED,
                GRI_ALANDAN_RED,
                ISTIHBARATTAN_RED,
                TEKNIK_ARIZA,
                KULLANDIRILDI);

        return !states.contains(bankTransactionState);
    }
}
