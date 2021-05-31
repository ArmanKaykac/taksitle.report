package io.ngss.taksitle.report.customermanagement.database.customer;

import io.ngss.taksitle.report.customermanagement.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_kredilink_parameters")
public class CustomerKredilinkParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "contract_confirmation")
    private Boolean contractConfirmation;

    @Column(name = "contract_confirmation_date")
    private Date contractConfirmationDate;

    @Column(name = "contract_version")
    private String contractVersion;

    @Column(name = "kvk_confirmation_version")
    private String kvkConfirmationVersion;

    @Column(name = "kvk_confirmation")
    private Boolean kvkConfirmation;

    @Column(name = "kvk_confirmation_date")
    private Date kvkConfirmationDate;

    @OneToOne(mappedBy = "customerKredilinkParameters")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getContractConfirmation() {
        return contractConfirmation;
    }

    public void setContractConfirmation(Boolean contractConfirmation) {
        this.contractConfirmation = contractConfirmation;
    }

    public Date getContractConfirmationDate() {
        return contractConfirmationDate;
    }

    public void setContractConfirmationDate(Date contractConfirmationDate) {
        this.contractConfirmationDate = contractConfirmationDate;
    }

    public String getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(String contractVersion) {
        this.contractVersion = contractVersion;
    }

    public String getKvkConfirmationVersion() {
        return kvkConfirmationVersion;
    }

    public void setKvkConfirmationVersion(String kvkConfirmationVersion) {
        this.kvkConfirmationVersion = kvkConfirmationVersion;
    }

    public Boolean getKvkConfirmation() {
        return kvkConfirmation;
    }

    public void setKvkConfirmation(Boolean kvkConfirmation) {
        this.kvkConfirmation = kvkConfirmation;
    }

    public Date getKvkConfirmationDate() {
        return kvkConfirmationDate;
    }

    public void setKvkConfirmationDate(Date kvkConfirmationDate) {
        this.kvkConfirmationDate = kvkConfirmationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}