package io.ngss.taksitle.report.customermanagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import io.ngss.taksitle.report.customermanagement.enums.HomeOwnership;
import io.ngss.taksitle.report.customermanagement.enums.WorkingType;
import io.ngss.taksitle.report.dealer.database.Address;
import io.ngss.taksitle.report.dealer.database.City;
import io.ngss.taksitle.report.dealer.database.District;
import io.ngss.taksitle.report.shared.database.enums.Education;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.service.TimeRange;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "customerhistory")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CustomerHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String company;
    private String companyVDCity;
    private String companyVDNo;
    private String companyVDName;
    private String name;

    @ManyToOne
    @JsonBackReference(value="transactionCustomerHistory")
    @JoinColumn(name="transaction_id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "id_serial_and_sequence_no")
    private String idSerialAndSequenceNo;
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
    @Column(name = "transaction_date")
    private Long transactionDate;
    @Enumerated(value = EnumType.ORDINAL)
    private TimeRange lastEmploymentTime;
    @Column(name="motherMaidenName")
    private String motherMaidenName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthdate")
    private Date birthdate;

    @NotNull
    @Column(name = "gsmno")
    private Long gsmno;

    private Long workPhoneNo;

    @ManyToOne
    private City homeCity;

    @ManyToOne
    private District homeDistrict;

    @Column(name = "home_address_description")
    private String homeAddressDescription;

    @ManyToOne
    private City workCity;

    @ManyToOne
    private District workDistrict;

    @Column(name = "work_address_description")
    private String workAddressDescription;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public CustomerHistory(Transaction transaction, Customer customer, @Email String email, String idSerialAndSequenceNo, Education educationLevel, WorkingType workingType, String occupation, Long salary, HomeOwnership homeOwnership, String company, String companyVDCity, String companyVDNo, String companyVDName, TimeRange lastEmploymentTime, @NotNull Long gsmno, City homeCity, District homeDistrict, String homeAddressDescription, Date birthdate, City workCity, District workDistrict, String workAddressDescription,String motherMaidenName,Long workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
        this.transaction = transaction;
        this.motherMaidenName=motherMaidenName;
        this.customer = customer;
        this.email = email;
        this.idSerialAndSequenceNo = idSerialAndSequenceNo;
        this.educationLevel = educationLevel;
        this.workingType = workingType;
        this.occupation = occupation;
        this.salary = salary;
        this.homeOwnership = homeOwnership;
        this.company = company;
        this.companyVDCity = companyVDCity;
        this.companyVDNo = companyVDNo;
        this.companyVDName = companyVDName;
        this.lastEmploymentTime = lastEmploymentTime;
        this.gsmno = gsmno;
        this.homeCity = homeCity;
        this.homeDistrict = homeDistrict;
        this.homeAddressDescription = homeAddressDescription;
        this.birthdate=birthdate;
        this.workCity=workCity;
        this.workDistrict=workDistrict;
        this.workAddressDescription=workAddressDescription;
    }

    public CustomerHistory(Customer customer, CustomerDetails customerDetails, Transaction transaction) {
        this.transaction = transaction;
        this.customer = customer;
        this.email = customerDetails.getEmail();
        this.idSerialAndSequenceNo = customerDetails.getIdSerialAndSequenceNo();
        this.educationLevel = customerDetails.getEducationLevel();
        this.workingType = customerDetails.getWorkingType();
        this.occupation = customerDetails.getOccupation();
        this.salary = customerDetails.getSalary();
        this.homeOwnership = customerDetails.getHomeOwnership();
        this.company = customerDetails.getCompany();
        this.companyVDCity = customerDetails.getCompanyVDCity();
        this.companyVDNo = customerDetails.getCompanyVDNo();
        this.companyVDName = customerDetails.getCompanyVDName();
        this.lastEmploymentTime = customerDetails.getLastEmploymentTime();
        this.gsmno = customer.getGsmno();
        this.transactionDate = transaction.getInitialDate();
        this.birthdate=customerDetails.getBirthdate();
        this.motherMaidenName=customerDetails.getMothersMaidenName();
        if(customer.getWorkAddress()!=null) {
            this.workPhoneNo = customer.getWorkAddress().getPhone();
        }
    }

    public CustomerHistory(Customer customer, Transaction transaction) {
        this.transaction = transaction;
        this.customer = customer;
        this.gsmno = customer.getGsmno();
        this.transactionDate = transaction.getInitialDate();
        this.birthdate = customer.getCustomerDetails().getBirthdate();
        this.name = customer.getCustomerDetails().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWorkPhoneNo() {
        return workPhoneNo;
    }

    public void setWorkPhoneNo(Long workPhoneNo) {
        this.workPhoneNo = workPhoneNo;
    }

    public CustomerHistory() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdSerialAndSequenceNo() {
        return idSerialAndSequenceNo;
    }

    public void setIdSerialAndSequenceNo(String idSerialAndSequenceNo) {
        this.idSerialAndSequenceNo = idSerialAndSequenceNo;
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

    public HomeOwnership getHomeOwnership() {
        return homeOwnership;
    }

    public void setHomeOwnership(HomeOwnership homeOwnership) {
        this.homeOwnership = homeOwnership;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
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

    public Long getGsmno() {
        return gsmno;
    }

    public void setGsmno(Long gsmno) {
        this.gsmno = gsmno;
    }

    public City getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(City homeCity) {
        this.homeCity = homeCity;
    }

    public District getHomeDistrict() {
        return homeDistrict;
    }

    public void setHomeDistrict(District homeDistrict) {
        this.homeDistrict = homeDistrict;
    }

    public String getHomeAddressDescription() {
        return homeAddressDescription;
    }

    public void setHomeAddressDescription(String homeAddressDescription) {
        this.homeAddressDescription = homeAddressDescription;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCustomerHistoryAddressforTransaction(Address address) {

        switch (address.getAddressTypes()) {
            case HOME:
                this.homeCity = address.getCity();
                this.homeDistrict = address.getDistrict();
                this.homeAddressDescription = address.getAddressDescription();
                break;
            case WORK:
                this.workPhoneNo = address.getPhone();
                this.workCity = address.getCity();
                this.workDistrict = address.getDistrict();
                this.workAddressDescription = address.getAddressDescription();
                break;
            default:
                break;
        }

    }

    public City getWorkCity() {
        return workCity;
    }

    public void setWorkCity(City workCity) {
        this.workCity = workCity;
    }

    public District getWorkDistrict() {
        return workDistrict;
    }

    public void setWorkDistrict(District workDistrict) {
        this.workDistrict = workDistrict;
    }

    public String getWorkAddressDescription() {
        return workAddressDescription;
    }

    public void setWorkAddressDescription(String workAddressDescription) {
        this.workAddressDescription = workAddressDescription;
    }

    public void updateInformation(Customer customer, CustomerDetails customerDetails, Transaction transaction) {
        this.transaction = transaction;
        this.customer = customer;
        this.email = customerDetails.getEmail();
        this.idSerialAndSequenceNo = customerDetails.getIdSerialAndSequenceNo();
        this.educationLevel = customerDetails.getEducationLevel();
        this.workingType = customerDetails.getWorkingType();
        this.occupation = customerDetails.getOccupation();
        this.salary = customerDetails.getSalary();
        this.homeOwnership = customerDetails.getHomeOwnership();
        this.company = customerDetails.getCompany();
        this.companyVDCity = customerDetails.getCompanyVDCity();
        this.companyVDNo = customerDetails.getCompanyVDNo();
        this.companyVDName = customerDetails.getCompanyVDName();
        this.lastEmploymentTime = customerDetails.getLastEmploymentTime();
        this.gsmno = customer.getGsmno();
        this.transactionDate = transaction.getInitialDate();
        this.birthdate = customerDetails.getBirthdate();
        this.motherMaidenName = customerDetails.getMothersMaidenName();
        if (customer.getWorkAddress() != null) {
            this.workPhoneNo = customer.getWorkAddress().getPhone();
        }

    }
}