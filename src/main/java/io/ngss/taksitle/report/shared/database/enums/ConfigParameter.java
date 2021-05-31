package io.ngss.taksitle.report.shared.database.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ConfigParameter {

    MAX_ALLOWED_FAILED_LOGIN_FOR_CUSTOMER("Max Allowed Login Failures For customer"),
    MAX_ALLOWED_FAILED_LOGIN_FOR_DEALER_USER("Max Allowed Login Failures For dealer User"),
    SEKERBANK_TIMOUT_PARAMETER_FOR_LOANOFFER("Time out parameter for Sekerbank Loan Offers"),
    ING_TIMOUT_PARAMETER_FOR_LOANOFFER("Time out parameter for ING bank Loan Offers"),
    QNB_TIMOUT_PARAMETER_FOR_LOANOFFER("Time out parameter for QNB Loan Offers"),
    TEB_TIMOUT_PARAMETER_FOR_LOANOFFER("Time out parameter for TEB Loan Offers"),
    LOAN_CATEGORY_AND_CHANNEL_BASED_LOAN_OFFER_TIMEOUT("Loan Category and Channel Based Loan Offer Timeout"),
    BACKOFFICE_USER_PASSWORD_EXPIRATION_IN_DAYS("BackOfficeUser Password Expiration in Days");


    private final String friendlyName;

    ConfigParameter(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @JsonProperty("friendlyName")
    public String getName() {
        return this.friendlyName;
    }

}
