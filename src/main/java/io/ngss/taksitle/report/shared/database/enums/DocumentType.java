package io.ngss.taksitle.report.shared.database.enums;

import java.util.ArrayList;
import java.util.List;

import static io.ngss.taksitle.report.shared.database.enums.DocumenTypeType.*;

public enum DocumentType {
    BANKA_HIZMET_SOZLESMESI(BASVURU),
    KIMLIK_FOTOKOPI(BASVURU),
    TUKETICI_KREDI_SOZLESMESI(BASVURU),
    ODEME_PLANI(BASVURU),
    GELIR_BELGESI(BASVURU),
    TRANSFER_TALIMATI(BASVURU),
    SOZLESME_ONCESI_BILGI_FORMU(BASVURU),
    TEMEL_BANKACILIK_FORMU(BASVURU),
    PROFORMA_FATURA(BASVURU),
    REHIN_SOZLESMESI(REHIN),
    KULLANDIRMA_SOZLESMESI(SOZLESME);

    private DocumenTypeType docType;

    DocumentType(DocumenTypeType docType) {
        this.docType = docType;
    }

    public static List<DocumentType> getBasvuruDocumentsForTypeType(DocumenTypeType documentTypeType) {
        List<DocumentType> documentTypeList = new ArrayList<>();
        for (DocumentType dt : DocumentType.values()) {
            if (dt.docType == documentTypeType) {
                documentTypeList.add(dt);
            }
        }
        return documentTypeList;
    }
}