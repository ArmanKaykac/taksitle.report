package io.ngss.taksitle.report.dealer.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.dealer.database.enums.AddressTypes;
import io.ngss.taksitle.report.transaction.database.entity.DeliveryInfo;

import javax.persistence.*;

@Entity
@Table(name = "address")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="district_id")
    private District district;

    @Column(name = "address_description")
    private String addressDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressTypes addressTypes;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToOne
    @JsonIgnoreProperties(value = "address")
    @JoinColumn(name = "deliveryInfo_id")
    private DeliveryInfo deliveryInfo;

    private Long phone;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Address() {
    }

    public Address(Customer customer, District district, City city, String description) {
        this.customer = customer;
        this.district = district;
        this.city = city;
        this.addressDescription = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public AddressTypes getAddressTypes() {
        return addressTypes;
    }
    public void setAddressTypes(AddressTypes addressTypes) {

        this.addressTypes = addressTypes;
    }


    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

}
