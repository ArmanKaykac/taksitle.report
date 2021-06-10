package io.ngss.taksitle.report.dealer.database.entity.dealer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.dealer.database.entity.FinancialCompanies;
import io.ngss.taksitle.report.shared.util.NGPair;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "dealer_finances")
public class DealerFinances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Dealer dealer;

    @Column(name = "fiscal_year")
    private Integer fiscalYear;

    @Column(name = "tax_base")
    private Double taxBase;

    @Column(name = "capital")
    private Double capital;

    @Column(name = "sales_last_year")
    private Double salesLastYear;

    @Column(name = "is_sales_increasing_in_last_three_years")
    private Boolean isSalesIncreasingInLastThreeYears = false;

    @Column(name = "cash_sales")
    private Double cashSales;

    @Column(name = "credit_card_sales")
    private Double creditCardSales;

    @Column(name = "bond_sales")
    private Double bondSales;

    @Column(name = "check_sales")
    private Double checkSales;

    @Column(name = "credit_sales")
    private Double creditSales;

    @Column(name = "credit_card_avg_maturity")
    private Integer creditCardAvgMaturity;

    @Column(name = "bond_avg_maturity")
    private Integer bondAvgMaturity;

    @Column(name = "check_avg_maturity")
    private Integer checkAvgMaturity;

    @Column(name = "credit_avg_maturity")
    private Integer creditAvgMaturity;

    @ElementCollection(targetClass = FinancialCompanies.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name = "dealer_financialCompanies")
    @Column(name = "financeCampanies")
    Collection<FinancialCompanies> financeCampanies;

    @OneToMany
    @Column(name = "banks")
    private Set<Bank> bank;

    @Column(name = "capital_financed")
    private Double capitalFinanced;

    @Column(name = "cash_blockage_financed")
    private Double cashBlockageFinanced;

    @Column(name = "letter_of_guarantee_financed")
    private Double letterOfGuaranteeFinanced;

    @Column(name = "hypothec_financed")
    private Double hypothecFinanced;

    @Column(name = "check_bond_financed")
    private Double checkBondFinanced;

    @Column(name = "cash_credit_financed")
    private Double cashCreditFinanced;

    @Column(name = "stock_financed")
    private Double stockFinanced;

    @Column(name = "account_financed")
    private Double accountFinanced;

    @Column(name = "expected_credit_volume")
    private Double expectedCreditVolume;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Double getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(Double taxBase) {
        this.taxBase = taxBase;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getSalesLastYear() {
        return salesLastYear;
    }

    public void setSalesLastYear(Double salesLastYear) {
        this.salesLastYear = salesLastYear;
    }

    public Boolean getSalesIncreasingInLastThreeYears() {
        return isSalesIncreasingInLastThreeYears;
    }

    public void setSalesIncreasingInLastThreeYears(Boolean salesIncreasingInLastThreeYears) {
        isSalesIncreasingInLastThreeYears = salesIncreasingInLastThreeYears;
    }

    public Double getCashSales() {
        return cashSales;
    }

    public void setCashSales(Double cashSales) {
        this.cashSales = cashSales;
    }

    public Double getCreditCardSales() {
        return creditCardSales;
    }

    public void setCreditCardSales(Double creditCardSales) {
        this.creditCardSales = creditCardSales;
    }

    public Double getBondSales() {
        return bondSales;
    }

    public void setBondSales(Double bondSales) {
        this.bondSales = bondSales;
    }

    public Double getCheckSales() {
        return checkSales;
    }

    public void setCheckSales(Double checkSales) {
        this.checkSales = checkSales;
    }

    public Double getCreditSales() {
        return creditSales;
    }

    public void setCreditSales(Double creditSales) {
        this.creditSales = creditSales;
    }

    public Integer getCreditCardAvgMaturity() {
        return creditCardAvgMaturity;
    }

    public void setCreditCardAvgMaturity(Integer creditCardAvgMaturity) {
        this.creditCardAvgMaturity = creditCardAvgMaturity;
    }

    public Integer getBondAvgMaturity() {
        return bondAvgMaturity;
    }

    public void setBondAvgMaturity(Integer bondAvgMaturity) {
        this.bondAvgMaturity = bondAvgMaturity;
    }

    public Integer getCheckAvgMaturity() {
        return checkAvgMaturity;
    }

    public void setCheckAvgMaturity(Integer checkAvgMaturity) {
        this.checkAvgMaturity = checkAvgMaturity;
    }

    public Integer getCreditAvgMaturity() {
        return creditAvgMaturity;
    }

    public void setCreditAvgMaturity(Integer creditAvgMaturity) {
        this.creditAvgMaturity = creditAvgMaturity;
    }

    public Collection<FinancialCompanies> getFinanceCampanies() {
        return financeCampanies;
    }

    public void setFinanceCampanies(Collection<FinancialCompanies> financeCampanies) {
        this.financeCampanies = financeCampanies;
    }

    public Set<Bank> getBank() {
        return bank;
    }

    public void setBank(Set<Bank> bank) {
        this.bank = bank;
    }

    public Double getCapitalFinanced() {
        return capitalFinanced;
    }

    public void setCapitalFinanced(Double capitalFinanced) {
        this.capitalFinanced = capitalFinanced;
    }

    public Double getCashBlockageFinanced() {
        return cashBlockageFinanced;
    }

    public void setCashBlockageFinanced(Double cashBlockageFinanced) {
        this.cashBlockageFinanced = cashBlockageFinanced;
    }

    public Double getLetterOfGuaranteeFinanced() {
        return letterOfGuaranteeFinanced;
    }

    public void setLetterOfGuaranteeFinanced(Double letterOfGuaranteeFinanced) {
        this.letterOfGuaranteeFinanced = letterOfGuaranteeFinanced;
    }

    public Double getHypothecFinanced() {
        return hypothecFinanced;
    }

    public void setHypothecFinanced(Double hypothecFinanced) {
        this.hypothecFinanced = hypothecFinanced;
    }

    public Double getCheckBondFinanced() {
        return checkBondFinanced;
    }

    public void setCheckBondFinanced(Double checkBondFinanced) {
        this.checkBondFinanced = checkBondFinanced;
    }

    public Double getCashCreditFinanced() {
        return cashCreditFinanced;
    }

    public void setCashCreditFinanced(Double cashCreditFinanced) {
        this.cashCreditFinanced = cashCreditFinanced;
    }

    public Double getStockFinanced() {
        return stockFinanced;
    }

    public void setStockFinanced(Double stockFinanced) {
        this.stockFinanced = stockFinanced;
    }

    public Double getAccountFinanced() {
        return accountFinanced;
    }

    public void setAccountFinanced(Double accountFinanced) {
        this.accountFinanced = accountFinanced;
    }

    public Double getExpectedCreditVolume() {
        return expectedCreditVolume;
    }

    public void setExpectedCreditVolume(Double expectedCreditVolume) {
        this.expectedCreditVolume = expectedCreditVolume;
    }

    @JsonIgnore
    public Set<NGPair> getBankServicesList() {
        Set<NGPair> returnValue = new HashSet();

        for (DealerBankServices dealerBankServices : this.getDealer().getDealerBankServices()) {
            List<String> bankServiceList = new ArrayList<>();
            dealerBankServices.getBankServices().forEach(x -> bankServiceList.add(x.getName()));
            returnValue.add(new NGPair(dealerBankServices.getBank().getName(), bankServiceList));

            // returnValue.add(new NGPair(dealerBankServices.getDealer().getId(),dealerBankServices.getBank().getId()));
        }
        return returnValue;
    }

  /*  @JsonIgnore
    public Set<BankModel> getBankList(){
        Set<BankModel> bankList = new HashSet<>();

        Iterator<Bank> it = this.getBank().iterator();

        while(it.hasNext()) bankList.add(new BankModel(it.next().getId(), it.next().getName()));

        return bankList;
    }*/

}
