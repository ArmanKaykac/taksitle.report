package io.ngss.taksitle.report.bank;

public enum LoanCategory {
    IHTIYAC_KREDISI("1", "İhtiyaç Kredisi"),
    TASIT_SIFIR("2", "0 Km"),
    TASIT_IKINCI_EL("3", "2. El Taşıt");

    private String sekerbankValue;

    private String displayName;

    private LoanCategory(String sekerbankValue, String displayName){
        this.sekerbankValue = sekerbankValue;
        this.displayName = displayName;
    }

    public String getSekerbankValue(){return this.sekerbankValue;}

    public String getDisplayName() { return this.displayName; }

    public boolean isForCarLoan() {
        return this == LoanCategory.TASIT_SIFIR || this == LoanCategory.TASIT_IKINCI_EL;
    }

    public static LoanCategory getBySekerbankValue (String sekerbankValue) {
        for (LoanCategory lc : LoanCategory.values()) {
            if (lc.sekerbankValue.equals(sekerbankValue)) {
                return lc;
            }
        }
        return null;
    }
}