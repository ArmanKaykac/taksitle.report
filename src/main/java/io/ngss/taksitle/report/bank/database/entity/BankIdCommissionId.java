package io.ngss.taksitle.report.bank.database.entity;

import io.ngss.taksitle.report.dealer.database.entity.CommissionMonthDefinition;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BankIdCommissionId implements Serializable {

    private Long bankId;

    private Long commissionId;

    public BankIdCommissionId(){}

    public BankIdCommissionId(Bank bank,
                              CommissionMonthDefinition commissionMonthDefinition){
        this.bankId = bank.getId();
        this.commissionId=commissionMonthDefinition.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BankIdCommissionId that = (BankIdCommissionId) o;
        return Objects.equals(commissionId, that.commissionId) &&
                Objects.equals(bankId, that.bankId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, commissionId);
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Long commissionId) {
        this.commissionId = commissionId;
    }
}