package io.ngss.taksitle.report.dealer.database.entity.backoffice;

import io.ngss.taksitle.report.shared.database.Role;
import io.ngss.taksitle.report.shared.database.enums.ConfigParameter;
import io.ngss.taksitle.report.shared.database.repository.ConfigurationRepository;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "backoffice_user")
public class BackOfficeUser {

    public Long password_change_time;
    @OneToMany(mappedBy = "backOfficeUserId")
    public List<BackOfficeUserLoginInfo> backOfficeUserLoginInfoList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "gsmno")
    private String gsmno;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "expirationtime")
    private Long expirationTime;
    @ManyToMany
    @JoinTable(
            name = "bofficeusers_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @Column(name = "enabled")
    private Boolean isEnabled = true;
    @Column(name = "otp_expire_time")
    private Long otpExpireTime;
    @OneToMany(mappedBy = "modifiedBackOfficeUser")
    private List<TransactionHistoryLog> transactionHistoryLogList;
    @Column(name = "unsuccessful_login_attempt")
    private Integer unsuccessfulLoginAttempt;

    public boolean isPasswordExpired(ConfigurationRepository configurationRepository) {
        if (password_change_time == null || password_change_time <= 0)
            return true;

        int days = 90; // default
        long aDay = 86_400_000;
        if (configurationRepository.findByConfigName(ConfigParameter.BACKOFFICE_USER_PASSWORD_EXPIRATION_IN_DAYS).isPresent()) {
            days = Integer.parseInt(configurationRepository.findByConfigName(ConfigParameter
                    .BACKOFFICE_USER_PASSWORD_EXPIRATION_IN_DAYS).get().getConfigValue());
        }

        if (System.currentTimeMillis() - password_change_time >= (days * aDay))
            return true;

        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Long getOtpExpireTime() {
        return otpExpireTime;
    }

    public void setOtpExpireTime(Long otpExpireTime) {
        this.otpExpireTime = otpExpireTime;
    }

    public List<TransactionHistoryLog> getTransactionHistoryLogList() {
        return transactionHistoryLogList;
    }

    public void setTransactionHistoryLogList(List<TransactionHistoryLog> transactionHistoryLogList) {
        this.transactionHistoryLogList = transactionHistoryLogList;
    }

    public Integer getUnsuccessfulLoginAttempt() {
        return unsuccessfulLoginAttempt;
    }

    public void setUnsuccessfulLoginAttempt(Integer unsuccessfulLoginAttempt) {
        this.unsuccessfulLoginAttempt = unsuccessfulLoginAttempt;
    }
}

