package io.ngss.taksitle.report.transaction.database;

import com.fasterxml.jackson.annotation.*;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import io.ngss.taksitle.report.bank.database.entity.PreApplicationRequest;
import io.ngss.taksitle.report.bank.integration.model.CauseOfReject;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.customermanagement.CustomerHistory;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUser;
import io.ngss.taksitle.report.shared.DateAudit;
import io.ngss.taksitle.report.shared.database.entity.Document;
import io.ngss.taksitle.report.shared.database.entity.RiskParameters;
import io.ngss.taksitle.report.shared.database.entity.SMSHistory;
import io.ngss.taksitle.report.transaction.database.entity.DeliveryInfo;
import io.ngss.taksitle.report.transaction.database.entity.RefundRequest;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "transaction")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Transaction extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private Integer token;

    @Column(name = "client_params")
    private String clientParams;

    @Enumerated
    @Column(name = "transaction_state")
    private TransactionState transactionState;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("customerTransactions")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "transactionLoanOffer")
    @JsonIgnoreProperties("transaction")
    private List<LoanOffer> loanOffers;

    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "transactionHistoryLogs")
    @JsonIgnoreProperties("transaction")
    private List<TransactionHistoryLog> transactionHistoryLogs;

    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference(value = "transactionSMSHistory")
    private List<SMSHistory> smsHistoryList;

    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RefundRequest> refundRequests;

    @OneToMany(
            mappedBy = "transaction",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PreApplicationRequest> preApplicationRequests;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference(value = "customerLoanRequest")
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "loanrequest_id")
    private LoanRequest loanRequest;

    @OneToOne
    @JsonIgnoreProperties(value = "transaction")
    @JoinColumn(name = "delivery_info_id")
    private DeliveryInfo deliveryInfoS;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_parameters")
    private RiskParameters riskParameters;

    @OneToMany(
            mappedBy = "transaction", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Document> documents;

    @ManyToOne(fetch = FetchType.EAGER)
    private Dealer dealer;

    @ManyToOne(fetch = FetchType.LAZY)
    private DealerUser dealerUser;

    @Column(name = "initialDate")
    private Long initialDate;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<CustomerHistory> customerHistory;

    @Column(name = "cancellationreason")
    private String cancellationReason;

    @Column(name = "state_update_date")
    private Date stateUpdateDate;

    @Enumerated
    @Column(name = "cause_of_reject")
    private CauseOfReject causeOfReject;

    @Column(name = "cancelExplanation")
    public String cancelExplanation;


    public List<PreApplicationRequest> getPreApplicationRequests() {
        return preApplicationRequests;
    }

    public void setPreApplicationRequests(List<PreApplicationRequest> preApplicationRequests) {
        this.preApplicationRequests = preApplicationRequests;
    }

    public RiskParameters getRiskParameters() {
        return riskParameters;
    }

    public void setRiskParameters(RiskParameters riskParameters) {
        this.riskParameters = riskParameters;
    }

    public CauseOfReject getCauseOfReject() {
        return causeOfReject;
    }

    public void setCauseOfReject(CauseOfReject causeOfReject) {
        this.causeOfReject = causeOfReject;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Long getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Long initialDate) {
        this.initialDate = initialDate;
    }

    public DealerUser getDealerUser() {
        return dealerUser;
    }

    public void setDealerUser(DealerUser dealerUser) {
        this.dealerUser = dealerUser;
    }

    public List<CustomerHistory> getCustomerHistory() {
        return customerHistory;
    }

    public void setCustomerHistory(List<CustomerHistory> customerHistory) {
        this.customerHistory = customerHistory;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getClientParams() {
        return clientParams;
    }

    public void setClientParams(String clientParams) {
        this.clientParams = clientParams;
    }

    public TransactionState getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LoanOffer> getLoanOffers() {
        return loanOffers;
    }

    public void setLoanOffers(List<LoanOffer> loanOffers) {
        this.loanOffers = loanOffers;
    }

    public List<SMSHistory> getSmsHistoryList() {
        return smsHistoryList;
    }

    public void setSmsHistoryList(List<SMSHistory> smsHistoryList) {
        this.smsHistoryList = smsHistoryList;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public DeliveryInfo getDeliveryInfoS() {
        return deliveryInfoS;
    }

    public void setDeliveryInfoS(DeliveryInfo deliveryInfoS) {
        this.deliveryInfoS = deliveryInfoS;
    }

    public List<TransactionHistoryLog> getTransactionHistoryLogs() {
        return transactionHistoryLogs;
    }

    public void setTransactionHistoryLogs(List<TransactionHistoryLog> transactionHistoryLogs) {
        this.transactionHistoryLogs = transactionHistoryLogs;
    }

    public Date getStateUpdateDate() {
        return stateUpdateDate;
    }

    public void setStateUpdateDate(Date stateUpdateDate) {
        this.stateUpdateDate = stateUpdateDate;
    }

    public void addDocument(Document doc) {

        Iterator<Document> it = this.getDocuments().iterator();

        while (it.hasNext()) {
            Document d = it.next();
            if (d.getBankDefinition().equalsIgnoreCase(doc.getBankDefinition())) {
                it.remove();
            }
        }

        this.getDocuments().add(doc);

    }

    public List<RefundRequest> getRefundRequests() {
        return refundRequests;
    }

    public void setRefundRequests(List<RefundRequest> refundRequests) {
        this.refundRequests = refundRequests;
    }

    @JsonIgnore
    public RefundRequest getActiveRefundRequest() {
        for (RefundRequest rr : this.refundRequests) {
            if (rr.getActive()) return rr;
        }
        return null;
    }

    @JsonIgnore
    public LoanOffer getSelectedLoanOffer() {
        for (LoanOffer lo : this.getLoanOffers()) {
            if (lo.isSelectedAndNonCanceled()) return lo;
        }
        return null;
    }

    public Document getDocumentByBankDefinition(String bankDefinition) {

        for (Document d : this.getDocuments()) {
            if (d.getBankDefinition().equalsIgnoreCase(bankDefinition)) return d;
        }
        return null;
    }

    public void removeDocumentByBankDefinition(String bankDefinition) {

        Iterator<Document> it = this.getDocuments().iterator();
        while (it.hasNext()) {
            Document d = it.next();
            if (d.getBankDefinition().equalsIgnoreCase(bankDefinition)) it.remove();
        }
    }

    public boolean isValid() {
        return this != null && this.getLoanRequest() != null && this.getLoanRequest().getLoanCategory() != null
                && this.getCustomer() != null && this.getCustomer().getCustomerDetails() != null && this.getDealer() != null
                && this.getDealer().getDealerAndSubDealerDetails() != null;
    }
}