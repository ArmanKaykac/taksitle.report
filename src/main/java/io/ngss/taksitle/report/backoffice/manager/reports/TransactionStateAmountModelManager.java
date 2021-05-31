package io.ngss.taksitle.report.backoffice.manager.reports;

import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateAmountModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionStateBaseDealerSearchModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.repository.LoanOfferRepository;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionStateAmountModelManager extends BaseReportModel<TransactionStateAmountModel, TransactionStateBaseDealerSearchModel> {

    private static final TransactionState[] transactionStateForFilter = new TransactionState[]{
            TransactionState.TEKLIF_ALINDI,
            TransactionState.BANKA_SECILDI,
            TransactionState.EVRAK_BEKLENIYOR,
            TransactionState.EVRAKLAR_TAMAMLANDI,
            TransactionState.EVRAK_KONTROL_SURECI,
            TransactionState.EVRAKLAR_BANKA_ONAYLI,
            TransactionState.ILK_DOKUMAN_SETI_ONAYLI,
            TransactionState.KREDI_KULLANDIRILDI,
            TransactionState.IPTAL
    };

    @Autowired
    private LoanOfferRepository loanOfferRepository;

    @Autowired
    private TransactionHistoryLogRepository transactionHistoryLogRepository;


    @Override
    public List<TransactionStateAmountModel> filterModel(TransactionStateBaseDealerSearchModel searchModel) {
        if (searchModel == null)
            return null;

        if (searchModel.startDate == null)
            return null;

        if (searchModel.endDate == null)
            searchModel.endDate = System.currentTimeMillis();

        List<TransactionHistoryLog> transactionHistoryLogList = transactionHistoryLogRepository.findAllByDateIsBetween(searchModel.startDate, searchModel.endDate);
        if (transactionHistoryLogList == null)
            return null;

        List<Transaction> transactions = new ArrayList<>();
        transactionHistoryLogList.stream().forEach(transactionHistoryLog -> transactions.add(transactionHistoryLog.getTransaction()));

        List<Integer> tokens = new ArrayList<>();
        transactions.stream().forEach(transaction -> tokens.add(transaction.getToken()));
        List<LoanOffer> loanOfferList = loanOfferRepository.findAllByTransactionTransactionStateInAndTransactionTokenIn(transactionStateForFilter,
                tokens.stream().distinct().collect(Collectors.toList()));
        if (loanOfferList == null)
            return null;

        if (searchModel.dealerId != null && searchModel.dealerId > 0)
            loanOfferList = loanOfferList.stream().filter(x -> x.getTransaction().getDealer().getId().equals(searchModel.dealerId)).collect(Collectors.toList());

        if (searchModel.financialTypeId != null && searchModel.financialTypeId > 0) {
            loanOfferList = loanOfferList.stream().filter(x -> x.getTransaction().getLoanRequest() != null
                    && x.getTransaction().getLoanRequest().getLoanCategory() != null
                    && x.getTransaction().getLoanRequest().getLoanCategory().equals(LoanCategory
                    .getBySekerbankValue(searchModel.financialTypeId.toString()))).collect(Collectors.toList());
        }

        Map<Bank, List<LoanOffer>> loanOfferListGroupedByDealer = loanOfferList.stream().collect(Collectors.groupingBy(LoanOffer::getBank));
        List<TransactionStateAmountModel> model = new ArrayList<>();
        //dealer
        loanOfferListGroupedByDealer.forEach((bank, loanOffers) -> {
            String bankName = bank.getName();

            Map<Dealer, List<Transaction>> transactionDealerMap = getTransactionListFromLoanOffers(loanOffers);

            transactionDealerMap.forEach((dealer, transactions1) -> {
                TransactionStateAmountModel m = new TransactionStateAmountModel();
                m.bankName = bankName;
                m.dealerName = dealer.getName();

                List<Integer> loanOfferAddedTokens = new ArrayList<>();
                List<LoanOffer> tempList = loanOffers.stream().filter(x -> x.getBank().getId() == bank.getId() && x.getTransaction().getDealer().getId() == dealer.getId()).collect(Collectors.toList());
                tempList.forEach(loanOffer -> {
                    if (loanOffer.getApproverAmount() != null) {
                        if (!BackOfficeReportHelper.filterTransactions(TransactionState.KREDI_KULLANDIRILDI,
                                Arrays.asList(loanOffer.getTransaction()), searchModel.startDate, searchModel.endDate).isEmpty()) {
                            m.kullandirildiAmount += loanOffer.getApproverAmount();
                            if (!BackOfficeReportHelper.filterTransactions(TransactionState.TEKLIF_ALINDI,
                                    Arrays.asList(loanOffer.getTransaction()), searchModel.startDate, searchModel.endDate).isEmpty()
                                    && !loanOfferAddedTokens.contains(loanOffer.getTransaction().getToken())) {
                                m.teklifAlindiAmount += loanOffer.getApproverAmount();
                                loanOfferAddedTokens.add(loanOffer.getTransaction().getToken());
                            }
                        } else {
                            if (!BackOfficeReportHelper.filterTransactions(TransactionState.TEKLIF_ALINDI,
                                    Arrays.asList(loanOffer.getTransaction()), searchModel.startDate, searchModel.endDate).isEmpty()
                                    && !loanOfferAddedTokens.contains(loanOffer.getTransaction().getToken())) {
                                m.teklifAlindiAmount += loanOffer.getApproverAmount();
                                loanOfferAddedTokens.add(loanOffer.getTransaction().getToken());
                            }
                        }
                    }
                });

                try {
                    if (dealer != null && dealer.getDealerAndSubDealerDetails() != null) {
                        m.source = dealer.getDealerAndSubDealerDetails().getSource();
                        m.portfolioManager = dealer.getDealerAndSubDealerDetails().getPortfolioManager();
                        m.city = dealer.getDealerAndSubDealerDetails().getSellerCity() == null ? ""
                                : dealer.getDealerAndSubDealerDetails().getSellerCity().getName();
                        m.code = dealer.getDealerAndSubDealerDetails().getDealer().getId();
                    }
                } catch (Exception e) {

                }
                if (m.teklifAlindiAmount > 0.00 || m.kullandirildiAmount > 0.00) {
                    model.add(m);
                }
            });
        });

        calculateTotalAndAddToList(model);

        return model;
    }

    private Map<Dealer, List<Transaction>> getTransactionListFromLoanOffers(List<LoanOffer> loanOffers) {
        if (loanOffers == null)
            return null;
        List<Transaction> transactions = new ArrayList<>();

        loanOffers.forEach(x -> {
            if (!transactions.contains(x.getTransaction()))
                transactions.add(x.getTransaction());
        });

        return transactions.stream().collect(Collectors.groupingBy(Transaction::getDealer));
    }

    private void calculateTotalAndAddToList(List<TransactionStateAmountModel> modelList) {
        if (modelList == null) return;
        TransactionStateAmountModel totalModel = new TransactionStateAmountModel();

        totalModel.dealerName = "Total";
        totalModel.kullandirildiAmount = modelList.stream().mapToDouble(i -> i.kullandirildiAmount).sum();
        totalModel.teklifAlindiAmount = modelList.stream().mapToDouble(i -> i.teklifAlindiAmount).sum();
        modelList.add(totalModel);

    }
}
