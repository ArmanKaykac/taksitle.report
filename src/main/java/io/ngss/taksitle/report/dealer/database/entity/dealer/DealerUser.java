package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.dealer.database.enums.AuthorizedUserType;
import io.ngss.taksitle.report.shared.database.Role;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "dealer_user")
public class DealerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="authorized_user_type")
    private AuthorizedUserType userType;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "gsmno")
    private String gsmno;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "education_password")
    private String educationPassword;

    @Column(name = "otp_expire_time")
    private Long otpExpireTime;

    @Column(name = "education_otp_expire_time")
    private Long educationOtpExpireTime;

    @Column(name = "enabled")
    private Boolean isEnabled = true;

    @Column(name="expirationtime")
    private Long expirationTime;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_user_details_id")
    private DealerUserDetails dealerUserDetails;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "dealerUser")
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "modifiedDealerUser")
    private List<TransactionHistoryLog> transactionHistoryLogList;

    private Integer failedLoginCount = 0;
    public Boolean getEnabled() {
        return isEnabled;
    }

    public Integer getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(Integer failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Long getOtpExpireTime() {
        return otpExpireTime;
    }

    public void setOtpExpireTime(Long otpExpireTime) {
        this.otpExpireTime = otpExpireTime;
    }

    public Long getId() {
        return id;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGsmno() {
        return gsmno;
    }

    public void setGsmno(String gsmno) {
        this.gsmno = gsmno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }


    public DealerUserDetails getDealerUserDetails() {
        return dealerUserDetails;
    }

    public void setDealerUserDetails(DealerUserDetails dealerUserDetails) {
        this.dealerUserDetails = dealerUserDetails;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public List<TransactionHistoryLog> getTransactionHistoryLogList() {
        return transactionHistoryLogList;
    }

    public void setTransactionHistoryLogList(List<TransactionHistoryLog> transactionHistoryLogList) {
        this.transactionHistoryLogList = transactionHistoryLogList;
    }

    public boolean isExpired(){
        if (this.expirationTime == 0L) return true;

        else {
            if (this.expirationTime < System.currentTimeMillis()) return false;
        }

        return true;
    }

    public AuthorizedUserType getUserType() {
        return userType;
    }

    public void setUserType(AuthorizedUserType userType) {
        this.userType = userType;
    }

    public String getEducationPassword() {
        return educationPassword;
    }

    public void setEducationPassword(String educationPassword) {
        this.educationPassword = educationPassword;
    }

    public Long getEducationOtpExpireTime() {
        return educationOtpExpireTime;
    }

    public void setEducationOtpExpireTime(Long educationOtpExpireTime) {
        this.educationOtpExpireTime = educationOtpExpireTime;
    }
}
