package io.ngss.taksitle.report.backoffice.model.reports.base;

import io.ngss.taksitle.report.bank.database.entity.Bank;

public class CountByBankModel {
    private Bank bank;
    private Long count;

    public CountByBankModel(Bank bank, Long count) {
        this.bank = bank;
        this.count = count;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
