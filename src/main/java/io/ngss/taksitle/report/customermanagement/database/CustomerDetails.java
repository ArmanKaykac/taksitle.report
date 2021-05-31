package io.ngss.taksitle.report.customermanagement.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.customermanagement.enums.HomeOwnership;
import io.ngss.taksitle.report.customermanagement.enums.WorkingType;
import io.ngss.taksitle.report.shared.database.enums.Education;
import io.ngss.taksitle.report.transaction.service.TimeRange;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "customer_details")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "id_serial_and_sequence_no")
    private String idSerialAndSequenceNo;

    @Column(name = "mothers_maiden_name")
    private String mothersMaidenName;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_level")
    private Education educationLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "workingtype")
    private WorkingType workingType;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "salary")
    private Long salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "home_ownership")
    private HomeOwnership homeOwnership;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private String company;
    private String companyVDCity;
    private String companyVDNo;
    private String companyVDName;

    @Enumerated(value = EnumType.ORDINAL)
    private TimeRange lastEmploymentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName;
    }

    public Education getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Education educationLevel) {
        this.educationLevel = educationLevel;
    }

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdSerialAndSequenceNo() {
        return idSerialAndSequenceNo;
    }

    public void setIdSerialAndSequenceNo(String idSerialAndSequenceNo) {
        this.idSerialAndSequenceNo = idSerialAndSequenceNo;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public HomeOwnership getHomeOwnership() {
        return homeOwnership;
    }

    public void setHomeOwnership(HomeOwnership homeOwnership) {
        this.homeOwnership = homeOwnership;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyVDCity() {
        return companyVDCity;
    }

    public void setCompanyVDCity(String companyVDCity) {
        this.companyVDCity = companyVDCity;
    }

    public String getCompanyVDNo() {
        return companyVDNo;
    }

    public void setCompanyVDNo(String companyVDNo) {
        this.companyVDNo = companyVDNo;
    }

    public String getCompanyVDName() {
        return companyVDName;
    }

    public void setCompanyVDName(String companyVDName) {
        this.companyVDName = companyVDName;
    }

    public TimeRange getLastEmploymentTime() {
        return lastEmploymentTime;
    }

    public void setLastEmploymentTime(TimeRange lastEmploymentTime) {
        this.lastEmploymentTime = lastEmploymentTime;
    }


}