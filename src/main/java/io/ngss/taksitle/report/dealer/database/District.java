package io.ngss.taksitle.report.dealer.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.ngss.taksitle.report.customermanagement.CustomerHistory;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "district")
@XmlRootElement
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = true)
    @Column(name = "name")
    private String name;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "homeDistrict")
    private List<CustomerHistory> customerHistories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}