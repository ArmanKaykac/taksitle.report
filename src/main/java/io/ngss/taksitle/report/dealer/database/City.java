package io.ngss.taksitle.report.dealer.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.CustomerHistory;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerAndSubDealerDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class City {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "homeCity")
    private List<CustomerHistory> customerHistories;

    @OneToMany(mappedBy = "city")
    private List<District> districtList;

    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

    @OneToOne
    @PrimaryKeyJoinColumn
    private DealerAndSubDealerDetails dealerAndSubDealerDetail;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomerHistory> getCustomerHistories() {
        return customerHistories;
    }

    public void setCustomerHistories(List<CustomerHistory> customerHistories) {
        this.customerHistories = customerHistories;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    @JsonIgnore
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonIgnore
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}