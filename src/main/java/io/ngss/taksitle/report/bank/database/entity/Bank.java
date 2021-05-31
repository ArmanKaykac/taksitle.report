package io.ngss.taksitle.report.bank.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ngss.taksitle.report.bank.database.enums.Banks;
import io.ngss.taksitle.report.dealer.database.Address;
import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import io.ngss.taksitle.report.shared.database.entity.DocumentTemplate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "tax_office")
    private String taxOffice;

    @Column(name = "tax_office_no")
    private Long taxOfficeNo;

    @Column(name = "definition_date")
    private Date definitionDate;

    @Column(name = "baseUrl")
    private String baseURL;

    @Lob
    @Column(name = "images")
    private byte[] images;

    @Column(name = "credentials")
    private String credentials;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "bank_representative_person")
    private String bankRepresentativePerson;

    @Column(name = "bank_representative_phone_no")
    private Long bankRepresentativePhoneNO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(
            mappedBy = "bank",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BankRelations> bankRelations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "bank", orphanRemoval = true)
    private List<DocumentTemplate> documentTemplates;

    @OneToOne
    @JoinColumn(name = "business_conditions_id")
    private BusinessConditions businessConditions;

    @Column(name="IBAN")
    private String IBAN;

    @Enumerated(EnumType.STRING)
    private Banks banks;

    @Column(name="pre_approval_required")
    private Boolean preApprovalRequired;


    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public Long getTaxOfficeNo() {
        return taxOfficeNo;
    }

    public void setTaxOfficeNo(Long taxOfficeNo) {
        this.taxOfficeNo = taxOfficeNo;
    }

    public Date getDefinitionDate() {
        return definitionDate;
    }

    public void setDefinitionDate(Date definitionDate) {
        this.definitionDate = definitionDate;
    }

    @JsonIgnore
    public List<DocumentTemplate> getDocumentTemplates() {
        return documentTemplates;
    }

    public void setDocumentTemplates(List<DocumentTemplate> documentTemplates) {
        this.documentTemplates = documentTemplates;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBankRepresentativePerson() {
        return bankRepresentativePerson;
    }

    public void setBankRepresentativePerson(String bankRepresentativePerson) {
        this.bankRepresentativePerson = bankRepresentativePerson;
    }

    public Long getBankRepresentativePhoneNO() {
        return bankRepresentativePhoneNO;
    }

    public void setBankRepresentativePhoneNO(Long bankRepresentativePhoneNO) {
        this.bankRepresentativePhoneNO = bankRepresentativePhoneNO;
    }

    @JsonIgnore
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonIgnore
    public List<BankRelations> getBankRelations() {
        return bankRelations;
    }

    public void setBankRelations(List<BankRelations> bankRelations) {
        this.bankRelations = bankRelations;
    }

    @JsonIgnore
    public BusinessConditions getBusinessConditions() {
        return businessConditions;
    }

    public void setBusinessConditions(BusinessConditions businessConditions) {
        this.businessConditions = businessConditions;
    }

    public Banks getBanks() {
        return banks;
    }

    public void setBanks(Banks banks) {
        this.banks = banks;
    }

    public Boolean getPreApprovalRequired() {
        return preApprovalRequired;
    }

    public void setPreApprovalRequired(Boolean preApprovalRequired) {
        this.preApprovalRequired = preApprovalRequired;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", preApprovalRequired=" + preApprovalRequired +
                '}';
    }
}

