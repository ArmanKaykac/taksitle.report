package io.ngss.taksitle.report.backoffice.model.reports;

import io.ngss.taksitle.report.backoffice.model.reports.base.ReportModel;

public class OtosorReportModel extends ReportModel {

    public Integer transactionToken;
    public String body;
    public String brand;
    public String fuel_type;
    public Integer km;
    public String model;
    public Integer production_year;
    public String transmission;
    public Integer hullInsuranceValue;
    public Double liquidity;
    public String liquidityBank;
    public Double averagePrice;
    public String averagePriceBank;
    public Double salePrice;

    public OtosorReportModel(Integer transactionToken, String body, String brand, String fuel_type, Integer km, String model, Integer production_year, String transmission, Integer hullInsuranceValue, Double liquidity, String liquidityBank, Double averagePrice, String averagePriceBank, Double salePrice) {
        this.transactionToken = transactionToken;
        this.body = body;
        this.brand = brand;
        this.fuel_type = fuel_type;
        this.km = km;
        this.model = model;
        this.production_year = production_year;
        this.transmission = transmission;
        this.hullInsuranceValue = hullInsuranceValue;
        this.liquidity = liquidity;
        this.liquidityBank = liquidityBank;
        this.averagePrice = averagePrice;
        this.averagePriceBank = averagePriceBank;
        this.salePrice = salePrice;
    }
}
