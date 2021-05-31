package io.ngss.taksitle.report.transaction.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.backoffice.BackOfficeUser;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUser;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "transaction_history_log")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TransactionHistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_state")
    private TransactionState transactionState;

    @Column(name = "transcation_date")
    private Long date;

    @Column(name = "ip_address_v4")
    private String ipAddressV4;

    @Column(name = "is_exceptional_state")
    private Boolean isExceptionalState;

    @Column(name = "exceptional_state_name")
    private String exceptionalStateName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="modified_dealer_user_id")
    private DealerUser modifiedDealerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="modified_backoffice_user_id")
    private BackOfficeUser modifiedBackOfficeUser;

    public TransactionHistoryLog(){}

    public TransactionHistoryLog(Transaction transaction, TransactionState transactionState, Long date,String ipAddressV4, Boolean isExceptionalState, String exceptionalStateName) {
        this.transaction = transaction;
        this.transactionState = transactionState;
        this.date = date;
        this.ipAddressV4 = ipAddressV4;
        this.isExceptionalState = isExceptionalState;
        this.exceptionalStateName = exceptionalStateName;
    }

    public TransactionHistoryLog(Transaction transaction, TransactionState transactionState, Long date,String ipAddressV4, DealerUser modifiedDealerUser, Boolean isExceptionalState, String exceptionalStateName) {
        this.transaction = transaction;
        this.transactionState = transactionState;
        this.date = date;
        this.ipAddressV4 = ipAddressV4;
        this.modifiedDealerUser = modifiedDealerUser;
        this.isExceptionalState = isExceptionalState;
        this.exceptionalStateName = exceptionalStateName;
    }
    public TransactionHistoryLog(Transaction transaction, TransactionState transactionState, Long date,String ipAddressV4, BackOfficeUser modifiedBackOfficeUser, Boolean isExceptionalState, String exceptionalStateName) {
        this.transaction = transaction;
        this.transactionState = transactionState;
        this.date = date;
        this.ipAddressV4 = ipAddressV4;
        this.modifiedBackOfficeUser = modifiedBackOfficeUser;
        this.isExceptionalState = isExceptionalState;
        this.exceptionalStateName = exceptionalStateName;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getIpAddressV4() {
        return ipAddressV4;
    }

    public void setIpAddressV4(String ipAddressV4) {
        this.ipAddressV4 = ipAddressV4;
    }

    public DealerUser getModifiedDealerUser() {
        return modifiedDealerUser;
    }

    public void setModifiedDealerUser(DealerUser modifiedDealerUser) {
        this.modifiedDealerUser = modifiedDealerUser;
    }

    public BackOfficeUser getModifiedBackOfficeUser() {
        return modifiedBackOfficeUser;
    }

    public void setModifiedBackOfficeUser(BackOfficeUser modifiedBackOfficeUser) {
        this.modifiedBackOfficeUser = modifiedBackOfficeUser;
    }

    public Boolean getExceptionalState() {
        return isExceptionalState;
    }

    public void setExceptionalState(Boolean exceptionalState) {
        isExceptionalState = exceptionalState;
    }

    public String getExceptionalStateName() {
        return exceptionalStateName;
    }

    public void setExceptionalStateName(String exceptionalStateName) {
        this.exceptionalStateName = exceptionalStateName;
    }
}
