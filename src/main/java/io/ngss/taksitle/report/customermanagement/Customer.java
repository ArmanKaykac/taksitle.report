package io.ngss.taksitle.report.customermanagement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import io.ngss.taksitle.report.customermanagement.database.customer.CustomerKredilinkParameters;
import io.ngss.taksitle.report.dealer.database.Address;
import io.ngss.taksitle.report.dealer.database.enums.AddressTypes;
import io.ngss.taksitle.report.shared.DateAudit;
import io.ngss.taksitle.report.transaction.database.Transaction;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "tckno"
        })
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Customer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NaturalId
    @Column(name = "tckno", unique=true)
    private Long tckno;

    @NotNull
    @Column(name = "gsmno")
    @NaturalId(mutable = true)
    private Long gsmno;

    private String otp;

    private Long otpExpire = 0l;

    private Long loginExpire = 0l;

    @NotNull
    @Column(name = "bringin_company_name", updatable = false)
    private String bringInCompanyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("customer")
    private List<Transaction> customerTransactions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_details_id")
    private CustomerDetails customerDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "kredilink_params_id")
    private CustomerKredilinkParameters customerKredilinkParameters;

    @OneToMany(mappedBy = "customer")
    private List<CustomerHistory> customerHistory;

    private Boolean isEnabled = true;

    private Integer currentFailedLoginCount = 0;

    @Column(name = "gsmno_banned")
    private Boolean gsmnoBanned;

    @Column(name = "email_banned")
    private Boolean emailBanned;

    public Customer() {

    }

    public Integer getCurrentFailedLoginCount() {
        return currentFailedLoginCount;
    }

    public void setCurrentFailedLoginCount(Integer currentFailedLoginCount) {
        this.currentFailedLoginCount = currentFailedLoginCount;
    }

    public List<CustomerHistory> getCustomerHistory() {
        return customerHistory;
    }

    public void setCustomerHistory(List<CustomerHistory> customerHistory) {
        this.customerHistory = customerHistory;
    }

    public Long getTckno() {
        return tckno;
    }

    public void setTckno(Long tckno) {
        this.tckno = tckno;
    }

    public String getBringInCompanyName() {
        return bringInCompanyName;
    }

    public void setBringInCompanyName(String bringInCompanyName) {
        this.bringInCompanyName = bringInCompanyName;
    }

    public List<Transaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<Transaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

    public CustomerKredilinkParameters getCustomerKredilinkParameters() {
        return customerKredilinkParameters;
    }

    public void setCustomerKredilinkParameters(CustomerKredilinkParameters customerKredilinkParameters) {
        this.customerKredilinkParameters = customerKredilinkParameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGsmno() {
        return gsmno;
    }

    public void setGsmno(Long gsmno) {
        this.gsmno = gsmno;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getOtpExpire() {
        return otpExpire;
    }

    public void setOtpExpire(Long otpExpire) {
        this.otpExpire = otpExpire;
    }

    public Long getLoginExpire() {
        return loginExpire;
    }

    public void setLoginExpire(Long loginExpire) {
        this.loginExpire = loginExpire;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getGsmnoBanned() {
        return gsmnoBanned;
    }

    public void setGsmnoBanned(Boolean gsmnoBanned) {
        this.gsmnoBanned = gsmnoBanned;
    }

    public Boolean getEmailBanned() {
        return emailBanned;
    }

    public void setEmailBanned(Boolean emailBanned) {
        this.emailBanned = emailBanned;
    }

    public void addFailedLogin() {
        this.setCurrentFailedLoginCount(this.getCurrentFailedLoginCount() + 1);
    }


    public Address getWorkAddress(){

        for(Address address:addresses){
            if(address.getAddressTypes()==AddressTypes.WORK) return address;
        }
        return null;
    }

    public Address getHomeAddress(){

        for(Address address:addresses){
            if(address.getAddressTypes()==AddressTypes.HOME) return address;
        }
        return null;
    }

    public Address getDeliveryAddress(){

        for(Address address:addresses){
            if(address.getAddressTypes()==AddressTypes.DELIVERY) return address;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", tckno=" + tckno +
                ", gsmno=" + gsmno +
                '}';
    }
}