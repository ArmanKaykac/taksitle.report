package io.ngss.taksitle.report.dealer.database.entity.dealer;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dealer_user_details")
public class DealerUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "tckno")
    private Long tckno;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "admin")
    private String admin;

    @Column(name = "permission")
    private String permission;

    @Column(name = "partnership_percentage")
    private Double parthnershipPercentange;

    @Column(name = "reason_of_making_inactive")
    private String reasonOfMakingInactive;

    @Column(name = "partial_blocking_reason")
    private Long partialBlockingReason;

    @Column(name = "start_date_of_employment")
    private Date startDateOfEmployment;

    @Column(name = "statue")
    private String statue;

    @OneToOne()
    @JoinColumn(name = "dealer_user_id")
    private DealerUser dealerUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTckno() {
        return tckno;
    }

    public void setTckno(Long tckno) {
        this.tckno = tckno;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Double getParthnershipPercentange() {
        return parthnershipPercentange;
    }

    public void setParthnershipPercentange(Double parthnershipPercentange) {
        this.parthnershipPercentange = parthnershipPercentange;
    }

    public String getReasonOfMakingInactive() {
        return reasonOfMakingInactive;
    }

    public void setReasonOfMakingInactive(String reasonOfMakingInactive) {
        this.reasonOfMakingInactive = reasonOfMakingInactive;
    }

    public Long getPartialBlockingReason() {
        return partialBlockingReason;
    }

    public void setPartialBlockingReason(Long partialBlockingReason) {
        this.partialBlockingReason = partialBlockingReason;
    }

    public Date getStartDateOfEmployment() {
        return startDateOfEmployment;
    }

    public void setStartDateOfEmployment(Date startDateOfEmployment) {
        this.startDateOfEmployment = startDateOfEmployment;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }
}
