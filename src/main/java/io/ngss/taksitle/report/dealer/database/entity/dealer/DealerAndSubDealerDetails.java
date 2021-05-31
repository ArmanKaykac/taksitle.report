package io.ngss.taksitle.report.dealer.database.entity.dealer;

import io.ngss.taksitle.report.dealer.database.City;
import io.ngss.taksitle.report.dealer.database.enums.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dealer_and_subdealer_details")
public class DealerAndSubDealerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main_firm_name")
    private String mainFirmName;

    @Column(name = "area")
    private String area;

    @Column(name = "segment")
    private String segment;

    @Column(name = "code")
    private Long code;

    @Column(name = "title")
    private String title;

    @Column(name = "tax_office")
    private String taxOffice;

    @Column(name = "tax_id_number")
    private Long taxIdNumber;

    @OneToOne
    @JoinColumn(name = "seller_city_id")
    private City sellerCity;

    @OneToOne
    @JoinColumn(name = "tax_id_city_id")
    private City taxIdNumberCity;

    @Column(name = "trade_register_number")
    private Long tradeRegisterNumber;

    @Column(name = "definition_date")
    private Date definitionDate;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "establishment_date")
    private Long establishmentDate;

    @Column(name = "dealerBank")
    private String dealerAccountBank;

    @Column(name = "account_iban")
    private Long accountIBAN;

    @Column(name = "monthly_average_sales_volume")
    private Long monthlyAverageSalesVolume;

    @Column(name = "monthly_average_sales_number")
    private Long monthlyAverageSalesNumber;

    @Column(name = "monthly_average_loan_request_number")
    private Long monthlyAverageLoanRequestNumber;

    @Column(name = "monthly_average_loan_utilization_number")
    private Long monthlyAverageLoanUtilizationNumber;

    @Column(name = "dealer_score")
    private Long dealerScore;

    @Column(name = "dealer_status")
    private DealerStatus dealerStatus;

    @Column(name = "address")
    private String address;

    @Column(name = "expectedNPL")
    private Double expectedNPL;

    @Column(name = "experienceInMonths")
    private Integer experienceInMonths;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    @Enumerated(EnumType.STRING)
    @Column(name = "salesChannel")
    private SalesChannel salesChannel;

    @Column(name = "initial_investment")
    private Long initialInvestment;

    @Enumerated(EnumType.STRING)
    @Column(name = "locationType")
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "priceStrategy")
    private PriceStrategy priceStrategy;

    @Column(name = "area_m2")
    private Integer areaM2;

    @Enumerated(EnumType.STRING)
    @Column(name = "ownership")
    private OwnerShipType ownerShipType;

    @Column(name = "noOfBranches")
    private Integer noOfBranches;

    @Column(name = "noOfemployees")
    private Integer noOfemployees;

    @Column(name = "doesHaveRoyaltySolution")
    private Boolean doesHaveRoyaltySolution;

    @Enumerated(EnumType.STRING)
    @Column(name = "dealer_category")
    private DealerCategory dealerCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "totalSalesPersonal")
    private Integer totalSalesPersonal;

    @Column(name = "cosigneeWorkingRatio")
    private Double consigneeWorkingRatio;

    @Column(name = "totalWorkingDurationInExistAddress")
    private Double totalWorkingDurationInExistAddress;

    @ManyToOne
    private CancellationReasons cancellationReason;

    @Column(name = "source")
    private String source;

    @Column(name = "portfolio_manager")
    private String portfolioManager;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainFirmName() {
        return mainFirmName;
    }

    public void setMainFirmName(String mainFirmName) {
        this.mainFirmName = mainFirmName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public Long getTaxIdNumber() {
        return taxIdNumber;
    }

    public void setTaxIdNumber(Long taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    public Date getDefinitionDate() {
        return definitionDate;
    }

    public void setDefinitionDate(Date definitionDate) {
        this.definitionDate = definitionDate;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Long getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(Long accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public City getTaxIdNumberCity() {
        return taxIdNumberCity;
    }

    public void setTaxIdNumberCity(City taxIdNumberCity) {
        this.taxIdNumberCity = taxIdNumberCity;
    }

    public Long getMonthlyAverageSalesVolume() {
        return monthlyAverageSalesVolume;
    }

    public void setMonthlyAverageSalesVolume(Long monthlyAverageSalesVolume) {
        this.monthlyAverageSalesVolume = monthlyAverageSalesVolume;
    }

    public Long getMonthlyAverageSalesNumber() {
        return monthlyAverageSalesNumber;
    }

    public void setMonthlyAverageSalesNumber(Long monthlyAverageSalesNumber) {
        this.monthlyAverageSalesNumber = monthlyAverageSalesNumber;
    }

    public Long getMonthlyAverageLoanRequestNumber() {
        return monthlyAverageLoanRequestNumber;
    }

    public void setMonthlyAverageLoanRequestNumber(Long monthlyAverageLoanRequestNumber) {
        this.monthlyAverageLoanRequestNumber = monthlyAverageLoanRequestNumber;
    }

    public Long getMonthlyAverageLoanUtilizationNumber() {
        return monthlyAverageLoanUtilizationNumber;
    }

    public void setMonthlyAverageLoanUtilizationNumber(Long monthlyAverageLoanUtilizationNumber) {
        this.monthlyAverageLoanUtilizationNumber = monthlyAverageLoanUtilizationNumber;
    }

    public Long getTradeRegisterNumber() {
        return tradeRegisterNumber;
    }

    public void setTradeRegisterNumber(Long tradeRegisterNumber) {
        this.tradeRegisterNumber = tradeRegisterNumber;
    }

    public Long getDealerScore() {
        return dealerScore;
    }

    public void setDealerScore(Long dealerScore) {
        this.dealerScore = dealerScore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Long getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Long establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Integer getExperienceInMonths() {
        return experienceInMonths;
    }

    public void setExperienceInMonths(Integer experienceInMonths) {
        this.experienceInMonths = experienceInMonths;
    }

    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Long getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(Long initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Integer getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(Integer areaM2) {
        this.areaM2 = areaM2;
    }

    public OwnerShipType getOwnerShipType() {
        return ownerShipType;
    }

    public void setOwnerShipType(OwnerShipType ownerShipType) {
        this.ownerShipType = ownerShipType;
    }

    public Integer getNoOfBranches() {
        return noOfBranches;
    }

    public void setNoOfBranches(Integer noOfBranches) {
        this.noOfBranches = noOfBranches;
    }

    public Integer getNoOfemployees() {
        return noOfemployees;
    }

    public void setNoOfemployees(Integer noOfemployees) {
        this.noOfemployees = noOfemployees;
    }

    public Boolean getDoesHaveRoyaltySolution() {
        return doesHaveRoyaltySolution;
    }

    public void setDoesHaveRoyaltySolution(Boolean doesHaveRoyaltySolution) {
        this.doesHaveRoyaltySolution = doesHaveRoyaltySolution;
    }

    public DealerStatus getDealerStatus() {
        return dealerStatus;
    }

    public void setDealerStatus(DealerStatus dealerStatus) {
        this.dealerStatus = dealerStatus;
    }

    public DealerCategory getDealerCategory() {
        return dealerCategory;
    }

    public void setDealerCategory(DealerCategory dealerCategory) {
        this.dealerCategory = dealerCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CancellationReasons getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(CancellationReasons cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Double getExpectedNPL() {
        return expectedNPL;
    }

    public void setExpectedNPL(Double expectedNPL) {
        this.expectedNPL = expectedNPL;
    }

    public String getDealerAccountBank() {
        return dealerAccountBank;
    }

    public void setDealerAccountBank(String dealerAccountBank) {
        this.dealerAccountBank = dealerAccountBank;
    }

    public PriceStrategy getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public Integer getTotalSalesPersonal() {
        return totalSalesPersonal;
    }

    public void setTotalSalesPersonal(Integer totalSalesPersonal) {
        this.totalSalesPersonal = totalSalesPersonal;
    }

    public Double getConsigneeWorkingRatio() {
        return consigneeWorkingRatio;
    }

    public void setConsigneeWorkingRatio(Double consigneeWorkingRatio) {
        this.consigneeWorkingRatio = consigneeWorkingRatio;
    }

    public City getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(City sellerCity) {
        this.sellerCity = sellerCity;
    }

    public Double getTotalWorkingDurationInExistAddress() {
        return totalWorkingDurationInExistAddress;
    }

    public void setTotalWorkingDurationInExistAddress(Double totalWorkingDurationInExistAddress) {
        this.totalWorkingDurationInExistAddress = totalWorkingDurationInExistAddress;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPortfolioManager() {
        return portfolioManager;
    }

    public void setPortfolioManager(String portfolioManager) {
        this.portfolioManager = portfolioManager;
    }
}

