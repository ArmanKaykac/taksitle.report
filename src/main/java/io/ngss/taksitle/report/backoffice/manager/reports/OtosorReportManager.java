package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.model.reports.OtosorReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.OtosorReportSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.entity.CartProduct;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.test.TransactionRepositoryNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OtosorReportManager extends BaseReportModel<OtosorReportModel, OtosorReportSearchModel> {

    @Autowired
    private TransactionRepositoryNewDB transactionRepository;

    @Override
    public List<OtosorReportModel> filterModel(OtosorReportSearchModel searchModel) {

        if (searchModel == null)
            return null;

        List<Transaction> transactionList = transactionRepository.findAllByCreatedAtIsAfter(new Date(searchModel.startDate));
        transactionList = transactionList.stream().filter(x -> x.getLoanRequest() != null && x.getLoanRequest().getLoanCategory() != null && (x.getLoanRequest().getLoanCategory() == LoanCategory.TASIT_SIFIR || x.getLoanRequest().getLoanCategory() == LoanCategory.TASIT_IKINCI_EL)).collect(Collectors.toList());
        if (transactionList == null) return null;

        if (searchModel.endDate != null)
            transactionList = transactionList.stream().filter(x -> x.getCreatedAt().before(new Date(searchModel.endDate))).collect(Collectors.toList());

        transactionList = transactionList.stream().filter(x -> x.getLoanRequest().getCart() != null && x.getLoanRequest().getCart().getCartProducts() != null).collect(Collectors.toList());

        List<OtosorReportModel> report = new ArrayList<>();
        transactionList.forEach(x -> {

            /*String bankParams = "";
            if (x.getLoanOffers() != null && x.getLoanOffers().size() > 0)
                bankParams = x.getLoanOffers().get(0).getOfferParameters();
            */
            CartProduct cp = x.getLoanRequest().getCart().getCartProducts().get(0);
            if (cp == null)
                return;

            Map<String, String> additionalParameters = cp.getAdditionalParameters();

            OtosorReportModel model = new OtosorReportModel(
                    x.getToken(),
                    additionalParameters.get("sahibindenBodyType"),
                    additionalParameters.get("brandName"),
                    additionalParameters.get("sahibindenFuelType"),
                    cp.getKm(),
                    additionalParameters.get("sahibindenModelName"),
                    cp.getModelYear(),
                    additionalParameters.get("sahibindenGearType"),
                    cp.getHullInsuranceAmount(),
                    cp.getLiquidity(),
                    String.valueOf(cp.getLiquidityBank()),
                    cp.getAveragePrice(),
                    String.valueOf(cp.getAveragePriceBank()),
                    cp.getPrice()
            );
            report.add(model);
        });
        return report;
    }
}
