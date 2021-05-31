package io.ngss.taksitle.report.dealer.database.entity.dealer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.InterestRates;
import io.ngss.taksitle.report.bank.database.entity.TaksitleRelations;
import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import io.ngss.taksitle.report.dealer.database.enums.Channel;
import io.ngss.taksitle.report.dealer.database.enums.CompanyType;
import io.ngss.taksitle.report.dealer.database.enums.DealerType;
import io.ngss.taksitle.report.shared.database.entity.ProductCategory;
import io.ngss.taksitle.report.transaction.database.Transaction;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dealer")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dealer_type")
    private DealerType dealerType;

    @Column(name="legal_name")
    private String legalName;

    @Email
    @Column(name="email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "certificate_download_url")
    private String certificateDownloadUrl;

    @Column(name = "certificate_password")
    private String certificatePassword;

    @Enumerated(EnumType.STRING)
    @Column(name="CompanyType")
    private CompanyType companyType;

    @ManyToMany
    @CollectionTable(
            name="dealer_owner_dealer"
    )
    private Set<Dealer> ownerDealers;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "dealer"
    )
    private List<DealerUser> dealerUsers;

    @OneToOne(
            mappedBy = "dealer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private DealerAndSubDealerDetails dealerAndSubDealerDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taksitle_relations_id")
    private TaksitleRelations taksitleRelations;

    @OneToOne
    @JoinColumn(name = "bussiness_conditions_id")
    private BusinessConditions businessConditions;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "dealer2productcategory",
            joinColumns = {@JoinColumn(name = "dealerid")},
            inverseJoinColumns = {@JoinColumn(name = "productcategoryid")}
    )
    private Set<ProductCategory> productCategories;

    @OneToMany(mappedBy = "dealer")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "dealer")
    private List<DealerBankServices> dealerBankServices;

    @OneToMany(mappedBy = "dealer")
    private List<DealerVendorRelations> dealerVendorRelations;

    @ManyToMany(mappedBy = "dealers")
    private List<InterestRates> interestRates;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_finances_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private DealerFinances dealerFinances;

    @ManyToMany
    @CollectionTable(
            name="dealer_bank"
    )
    private Set<Bank> banksWithAgreement;

    @ElementCollection(targetClass= LoanCategory.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="dealer_loanCategories")
    @Column(name="loanCategories")
    Set<LoanCategory> loanCategories;

    @Column(name = "isVIP")
    private Boolean isVip;

    @Enumerated(EnumType.STRING)
    public Channel channel;


    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Set<LoanCategory> getLoanCategories() {
        return loanCategories;
    }

    public void setLoanCategories(Set<LoanCategory> loanCategories) {
        this.loanCategories = loanCategories;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<DealerUser> getDealerUsers() {
        return dealerUsers;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DealerAndSubDealerDetails getDealerAndSubDealerDetails() {
        return dealerAndSubDealerDetails;
    }

    public void setDealerAndSubDealerDetails(DealerAndSubDealerDetails dealerAndSubDealerDetails) {
        this.dealerAndSubDealerDetails = dealerAndSubDealerDetails;
    }

    public BusinessConditions getBusinessConditions() {
        return businessConditions;
    }

    public void setBusinessConditions(BusinessConditions businessConditions) {
        this.businessConditions = businessConditions;
    }

    public DealerType getDealerType() {
        return dealerType;
    }

    public void setDealerType(DealerType dealerType) {
        this.dealerType = dealerType;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public List<DealerVendorRelations> getDealerVendorRelations() {
        return dealerVendorRelations;
    }

    public void setDealerVendorRelations(List<DealerVendorRelations> dealerVendorRelations) {
        this.dealerVendorRelations = dealerVendorRelations;
    }

    public DealerFinances getDealerFinances() {
        return dealerFinances;
    }

    public void setDealerFinances(DealerFinances dealerFinances) {
        this.dealerFinances = dealerFinances;
    }

    public TaksitleRelations getTaksitleRelations() {
        return taksitleRelations;
    }

    public void setTaksitleRelations(TaksitleRelations taksitleRelations) {
        this.taksitleRelations = taksitleRelations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Bank> getBanksWithAgreement() {
        return banksWithAgreement;
    }

    public void setBanksWithAgreement(Set<Bank> banksWithAgreement) {
        this.banksWithAgreement = banksWithAgreement;
    }

    /*@JsonIgnore
    public Set<Long> getMainDealerIDs(){
        Set<Long> returnList = new HashSet<>();
        for(Dealer d:ownerDealers){
            returnList.add(d.getId());
        }
        return returnList;
    }*/

    public List<DealerBankServices> getDealerBankServices() {
        return dealerBankServices;
    }

    public void setDealerBankServices(List<DealerBankServices> dealerBankServices) {
        this.dealerBankServices = dealerBankServices;
    }

    public Set<Long> getBankIdswithAgreement(){
        Set<Long> ids = new HashSet<>();


        Iterator<Bank> it = this.banksWithAgreement.iterator();

        while(it.hasNext()) ids.add(it.next().getId());

        return ids;
    }

    public Set<Dealer> getOwnerDealers() {
        return ownerDealers;
    }

    public void setOwnerDealers(Set<Dealer> ownerDealers) {
        this.ownerDealers = ownerDealers;
    }

    public void setDealerUsers(List<DealerUser> dealerUsers) {
        this.dealerUsers = dealerUsers;
    }

    public String getCertificateDownloadUrl() {
        return certificateDownloadUrl;
    }

    public void setCertificateDownloadUrl(String certificateDownloadUrl) {
        this.certificateDownloadUrl = certificateDownloadUrl;
    }

    public String getCertificatePassword() {
        return certificatePassword;
    }

    public void setCertificatePassword(String certificatePassword) {
        this.certificatePassword = certificatePassword;
    }

    public List<InterestRates> getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(List<InterestRates> interestRates) {
        this.interestRates = interestRates;
    }

}
