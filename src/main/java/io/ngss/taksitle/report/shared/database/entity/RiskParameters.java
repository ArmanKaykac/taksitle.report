package io.ngss.taksitle.report.shared.database.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.transaction.database.Transaction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "risk_parameters")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class RiskParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isEmployee")
    private Boolean isEmployee;

    @Column(name = "appBySameTckNoDifferentDealer")
    private Boolean appBySameTckNoDifferentDealer;

    @Column(name = "changeInGSMLast30Days")
    private Boolean changeInGSMLast30Days;

    @Column(name = "changeInMotherMaidenLast30Days")
    private Boolean changeInMotherMaidenLast30Days;

    @Column(name = "appBySameGSMNoLast30Days")
    private Boolean appBySameGSMNoLast30Days;

    @Column(name = "appBySameSurnameSameDealerLast7Days")
    private Boolean appBySameSurnameSameDealerLast7Days;

    @Column(name = "appForSamePlateChasisLast2Days")
    private Boolean appForSamePlateChasisLast2Days;

    @Column(name="cellPhoneOwnedByDealerOrEmployees")
    private Boolean cellPhoneOwnedByDealerOrEmployees;

    @Column(name="appForSamePlateChasisDiffDealerLast7Days")
    private Boolean appForSamePlateChasisDiffDealerLast7Days;

    @Column(name="surnameSameAsDealerWorkers")
    private Boolean surnameSameAsDealerWorkers;

    @Column(name="sameTaxNoMoreThan1TCKno")
    private Boolean sameTaxNoMoreThan1TCKno;

    @Column(name="sameTaxNoAsDealer")
    private Boolean sameTaxNoAsDealer;

    @OneToOne
    @JsonIgnore
    private Transaction transaction;

    @ManyToMany
    private Set<Transaction> bankReferenceTransactionList;

    @ManyToMany
    private Set<Transaction> finovatifReferenceTransactionList;

    public Set<Transaction> getBankReferenceTransactionList() {
        if(bankReferenceTransactionList==null){
            return new HashSet<>();
        }
        return bankReferenceTransactionList;
    }

    @JsonIgnore
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Set<Transaction> getFinovatifReferenceTransactionList() {
        if(finovatifReferenceTransactionList==null){
            return new HashSet<>();
        }
        return finovatifReferenceTransactionList;
    }


    public void setBankReferenceTransactionList(Set<Transaction> bankReferenceTransactionList) {
        this.bankReferenceTransactionList = bankReferenceTransactionList;
    }

    public void setFinovatifReferenceTransactionList(Set<Transaction> finovatifReferenceTransactionList) {
        this.finovatifReferenceTransactionList = finovatifReferenceTransactionList;
    }

    public void addTransactionToFinoRefSet(List<Transaction> transactions){
        Set<Transaction> tL = getFinovatifReferenceTransactionList();
        tL.addAll(transactions);
        this.setFinovatifReferenceTransactionList(tL);
//        getFinovatifReferenceTransactionList().addAll(transactions);
    }

    public void addTransactionToBankRefSet(List<Transaction> transactions){
        Set<Transaction> tL = getBankReferenceTransactionList();
        tL.addAll(transactions);
        this.setBankReferenceTransactionList(tL);
    }

    public Long getId() {
        return id;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Boolean getAppBySameTckNoDifferentDealer() {
        return appBySameTckNoDifferentDealer;
    }

    public void setAppBySameTckNoDifferentDealer(Boolean appBySameTckNoDifferentDealer) {
        this.appBySameTckNoDifferentDealer = appBySameTckNoDifferentDealer;
    }

    public Boolean getChangeInGSMLast30Days() {
        return changeInGSMLast30Days;
    }

    public void setChangeInGSMLast30Days(Boolean changeInGSMLast30Days) {
        this.changeInGSMLast30Days = changeInGSMLast30Days;
    }

    public Boolean getChangeInMotherMaidenLast30Days() {
        return changeInMotherMaidenLast30Days;
    }

    public void setChangeInMotherMaidenLast30Days(Boolean changeInMotherMaidenLast30Days) {
        this.changeInMotherMaidenLast30Days = changeInMotherMaidenLast30Days;
    }

    public Boolean getAppBySameGSMNoLast30Days() {
        return appBySameGSMNoLast30Days;
    }

    public void setAppBySameGSMNoLast30Days(Boolean appBySameGSMNoLast30Days) {
        this.appBySameGSMNoLast30Days = appBySameGSMNoLast30Days;
    }

    public Boolean getAppBySameSurnameSameDealerLast7Days() {
        return appBySameSurnameSameDealerLast7Days;
    }

    public void setAppBySameSurnameSameDealerLast7Days(Boolean appBySameSurnameSameDealerLast7Days) {
        this.appBySameSurnameSameDealerLast7Days = appBySameSurnameSameDealerLast7Days;
    }

    public Boolean getAppForSamePlateChasisLast2Days() {
        return appForSamePlateChasisLast2Days;
    }

    public void setAppForSamePlateChasisLast2Days(Boolean appForSamePlateChasisLast2Days) {
        this.appForSamePlateChasisLast2Days = appForSamePlateChasisLast2Days;
    }

    public Boolean getCellPhoneOwnedByDealerOrEmployees() {
        return cellPhoneOwnedByDealerOrEmployees;
    }

    public void setCellPhoneOwnedByDealerOrEmployees(Boolean cellPhoneOwnedByDealerOrEmployees) {
        this.cellPhoneOwnedByDealerOrEmployees = cellPhoneOwnedByDealerOrEmployees;
    }

    public Boolean getAppForSamePlateChasisDiffDealerLast7Days() {
        return appForSamePlateChasisDiffDealerLast7Days;
    }

    public void setAppForSamePlateChasisDiffDealerLast7Days(Boolean appForSamePlateChasisDiffDealerLast7Days) {
        this.appForSamePlateChasisDiffDealerLast7Days = appForSamePlateChasisDiffDealerLast7Days;
    }

    public Boolean getSurnameSameAsDealerWorkers() {
        return surnameSameAsDealerWorkers;
    }

    public void setSurnameSameAsDealerWorkers(Boolean surnameSameAsDealerWorkers) {
        this.surnameSameAsDealerWorkers = surnameSameAsDealerWorkers;
    }

    public Boolean getSameTaxNoMoreThan1TCKno() {
        return sameTaxNoMoreThan1TCKno;
    }

    public void setSameTaxNoMoreThan1TCKno(Boolean sameTaxNoMoreThan1TCKno) {
        this.sameTaxNoMoreThan1TCKno = sameTaxNoMoreThan1TCKno;
    }

    public Boolean getSameTaxNoAsDealer() {
        return sameTaxNoAsDealer;
    }

    public void setSameTaxNoAsDealer(Boolean sameTaxNoAsDealer) {
        this.sameTaxNoAsDealer = sameTaxNoAsDealer;
    }

    public void removeCurrentTransactionFromLists(Transaction t) {
        if(this.bankReferenceTransactionList!=null && this.bankReferenceTransactionList.size()>0 && this.bankReferenceTransactionList.contains(t)) this.bankReferenceTransactionList.remove(t);
        if(this.finovatifReferenceTransactionList!=null && this.finovatifReferenceTransactionList.size()>0 && this.finovatifReferenceTransactionList.contains(t)) this.finovatifReferenceTransactionList.remove(t);
    }
}
