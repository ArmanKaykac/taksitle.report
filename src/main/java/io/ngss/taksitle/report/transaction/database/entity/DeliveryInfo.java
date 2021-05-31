package io.ngss.taksitle.report.transaction.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ngss.taksitle.report.dealer.database.Address;
import io.ngss.taksitle.report.shared.DateAudit;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.enums.DeliveryType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deliveryInfo")
public class DeliveryInfo extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    String email;

    @Column(name = "employee")
    Boolean employee = false;

    @Column
    String description;

    @Column
    Date deliveryDate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DeliveryType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "deliveryInfo")
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "deliveryInfo")
    @JoinColumn(name = "address_id")
    private Address address;


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

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean  isDeliveryTypeHome() {
        return this.type == DeliveryType.HOMEADDRESS;
    }
}
