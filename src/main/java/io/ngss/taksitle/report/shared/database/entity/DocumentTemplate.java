package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.shared.database.enums.DocumentType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "documenttemplate")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DocumentTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] attachment;

    @Enumerated
    @Column(name = "documenttype")
    private DocumentType documentType;

    @ManyToOne
    @JsonBackReference
    private Bank bank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}