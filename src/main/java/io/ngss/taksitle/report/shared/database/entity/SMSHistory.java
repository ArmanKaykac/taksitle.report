package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;

@Entity
@Table(name = "sms_history")
public class SMSHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gsmno")
    private Long gsmno;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "transactionSMSHistory")
    private Transaction transaction;

    public SMSHistory() {
    }

    public SMSHistory(Transaction transaction, Long gsmno, String content) {
        this.transaction = transaction;
        this.gsmno = gsmno;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getGsmno() {
        return gsmno;
    }

    public void setGsmno(Long gsmno) {
        this.gsmno = gsmno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
