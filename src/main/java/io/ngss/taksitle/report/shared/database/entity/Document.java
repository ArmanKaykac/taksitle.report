package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.bank.database.enums.Banks;
import io.ngss.taksitle.report.bank.integration.model.DocumentRejectCode;
import io.ngss.taksitle.report.transaction.database.Transaction;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "document")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Document {

    @Enumerated
    @Column(name = "cause_of_reject")
    private DocumentRejectCode causeOfReject;

    @Column(name = "documentorder")
    private int order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documentName")
    private String documentName;

    private Boolean confirmed = false;

    private String bankDefinition;

    @ManyToOne
    private Transaction transaction;

    @Column(name = "bank_acceptance_status")
    private boolean bankAcceptanceStatus = false;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] file;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] documentTemplate;

    @Column(name="hash")
    private int hash;

    @Enumerated(EnumType.STRING)
    private Banks banks;


    public void setId(Long id) {
        this.id = id;
    }

    public int getHash() {
        return hash;
    }

    public void setHash() {
        this.hash = getFile().hashCode();
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    private String description;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isBankAcceptanceStatus() {
        return bankAcceptanceStatus;
    }

    public Document(String templateFile, Transaction transaction, String bankDefinition) {
        this.documentTemplate = templateFile.getBytes();
        this.transaction = transaction;
        this.bankDefinition = bankDefinition;
        this.confirmed = false;
    }

    public String getBankDefinition() {
        return bankDefinition;
    }

    public void setBankDefinition(String bankDefinition) {
        this.bankDefinition = bankDefinition;
    }

    public boolean getBankAcceptanceStatus() {
        return bankAcceptanceStatus;
    }

    public void setBankAcceptanceStatus(boolean bankAcceptanceStatus) {
        this.bankAcceptanceStatus = bankAcceptanceStatus;
    }

    public DocumentRejectCode getCauseOfReject() {
        return causeOfReject;
    }

    public void setCauseOfReject(DocumentRejectCode causeOfReject) {
        this.causeOfReject = causeOfReject;
    }

    public Document() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public byte[] getDocumentTemplate() {
        return documentTemplate;
    }

    public void setDocumentTemplate(byte[] documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

    public Banks getBanks() {
        return banks;
    }

    public void setBanks(Banks banks) {
        this.banks = banks;
    }
}
