package io.ngss.taksitle.report.backoffice.helper;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import io.ngss.taksitle.report.transaction.database.Transaction;

public class TransactionHelper {

    public static Boolean isCar (Transaction transaction) {

        LoanRequest loanRequest = transaction.getLoanRequest();
        if (loanRequest != null && loanRequest.getLoanCategory() != null && (loanRequest.getLoanCategory().equals(LoanCategory.TASIT_SIFIR) || loanRequest.getLoanCategory().equals(LoanCategory.TASIT_IKINCI_EL)))
            return true;
        return false;
    }
}
