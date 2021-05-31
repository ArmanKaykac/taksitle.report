package io.ngss.taksitle.report.backoffice.manager.reports;

import com.google.common.util.concurrent.AtomicDouble;
import io.ngss.taksitle.report.backoffice.manager.BackOfficeReportHelper;
import io.ngss.taksitle.report.backoffice.model.reports.SalesTrackingReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.base.BaseReportModel;
import io.ngss.taksitle.report.backoffice.model.reports.search.SalesTrackingReportSearchModel;
import io.ngss.taksitle.report.bank.database.entity.InterestRates;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.City;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerAndSubDealerDetails;
import io.ngss.taksitle.report.dealer.database.repository.CityRepository;
import io.ngss.taksitle.report.dealer.database.repository.DealerAndSubDealerDetailsRepository;
import io.ngss.taksitle.report.dealer.database.repository.DealerRepository;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import io.ngss.test.CityRepositoryNewDB;
import io.ngss.test.DealerAndSubDealerDetailsRepositoryNewDB;
import io.ngss.test.DealerRepositoryNewDB;
import io.ngss.test.TransactionHistoryLogRepositoryNewDB;
import io.ngss.test.helper.BackOfficeReportHelperNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class SalesTrackingReportModelManager extends BaseReportModel<SalesTrackingReportModel, SalesTrackingReportSearchModel> {

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    TransactionState[] offerReceivedStates = new TransactionState[]{
            TransactionState.TEKLIF_ALINDI
    };
    TransactionState[] bankSelectedStates = new TransactionState[]{
            TransactionState.BANKA_SECILDI
    };
    @Autowired
    private DealerRepositoryNewDB dealerRepository;
    @Autowired
    private DealerAndSubDealerDetailsRepositoryNewDB dealerAndSubDealerDetailsRepository;
    @Autowired
    private CityRepositoryNewDB cityRepository;
    @Autowired
    private TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepository;
    @Autowired
    private BackOfficeReportHelperNewDB backOfficeReportHelper;

    @Override
    public List<SalesTrackingReportModel> filterModel(SalesTrackingReportSearchModel searchModel) {
        if (searchModel == null)
            return null;

        if (searchModel.startDate == null)
            return null;

        if (searchModel.endDate == null)
            searchModel.endDate = System.currentTimeMillis();

        List<TransactionHistoryLog> transactionHistoryLogList = transactionHistoryLogRepository.findAllByDateIsBetween(searchModel.startDate, searchModel.endDate);
        if (transactionHistoryLogList == null)
            return null;

        List<Transaction> tcknTransactionList = backOfficeReportHelper.getTcknTransactions(searchModel.startDate, searchModel.endDate);
        if (tcknTransactionList == null)
            return null;

        tcknTransactionList = tcknTransactionList.stream().filter(transaction ->
                transaction.getCreatedAt().after(new Date(searchModel.startDate))).collect(Collectors.toList());

        if (searchModel.endDate != null) {
            tcknTransactionList = tcknTransactionList.stream().filter(transaction ->
                    transaction.getCreatedAt().before(new Date(searchModel.endDate))).collect(Collectors.toList());
        }

        Map<Integer, Transaction> transactionMap = new HashMap<>();
        transactionHistoryLogList.stream().forEach(t -> transactionMap.put(t.getTransaction().getToken(), t.getTransaction()));
        Map<Dealer, List<Transaction>> dealerTransactionMap = transactionMap.values().stream().collect(Collectors.groupingBy(Transaction::getDealer));

        Map<Integer, Transaction> tcknTransactionMap = new HashMap<>();
        tcknTransactionList.stream().forEach(transaction -> tcknTransactionMap.put(transaction.getToken(), transaction));
        Map<Dealer, List<Transaction>> dealerTcknTransactionMap = tcknTransactionMap.values().stream().collect(Collectors.groupingBy(Transaction::getDealer));

        List<Dealer> dealers = dealerRepository.findAll();
        if (dealers == null)
            return null;

        List<SalesTrackingReportModel> model = new ArrayList<>();

        for (Dealer dealer : dealers) {
            try {
                if (dealer == null)
                    continue;

                DealerAndSubDealerDetails dealerAndSubDealerDetails = dealerAndSubDealerDetailsRepository.findOneByDealerId(dealer.getId());
                SalesTrackingReportModel.Builder item = new SalesTrackingReportModel.Builder()
                        .dealerName(dealer.getName())
                        .dealerCode(dealer.getId())
                        .dealerCreateDate(dealerAndSubDealerDetails == null || dealerAndSubDealerDetails.getDefinitionDate() == null ?
                                "N/A" : formatDate(dealerAndSubDealerDetails.getDefinitionDate().getTime()));

                List<InterestRates> interestRates = dealer.getInterestRates();
                List<String> financialTypes = new ArrayList<>();
                interestRates.forEach(interestRate -> {
                    financialTypes.add(interestRate.getLoanCategory().getDisplayName());
                });
                item.financialTypes(String.join(",", financialTypes));

                List<Transaction> dealerTransactionList = dealerTransactionMap.get(dealer);
                if (dealerTransactionList == null || dealerTransactionList.isEmpty()) {
                    if (dealerAndSubDealerDetails != null) {
                        if (dealerAndSubDealerDetails.getSellerCity() != null) {
                            Optional<City> city = cityRepository.findById(dealerAndSubDealerDetails.getSellerCity().getId());
                            if (city.isPresent())
                                item.city(city.get().getName());
                        }

                        item.source(dealerAndSubDealerDetails.getSource());
                        item.portfolioManager(dealerAndSubDealerDetails.getPortfolioManager());
                    }

                    model.add(item.build());
                    continue;
                }

                List<Transaction> dealerTcknTransactionList = dealerTcknTransactionMap.get(dealer);
                if (dealerTcknTransactionList != null) {
                    Map<Customer, List<Transaction>> customerTcknTransactionMap = dealerTcknTransactionList.stream().collect(Collectors.groupingBy(Transaction::getCustomer));
                    item.uniqueCustomerQuantity(customerTcknTransactionMap.size());
                }

                AtomicInteger canceledAfterOfferQty = new AtomicInteger();
                AtomicInteger canceledAfterBankSelectedQty = new AtomicInteger();

                List<Transaction> cancelTransactionList = new ArrayList<>();
                dealerTransactionList.stream().forEach(dealerTransaction -> {
                    dealerTransaction.getTransactionHistoryLogs().stream().forEach(transactionHistoryLog -> {
                        if (transactionHistoryLog.getTransactionState() == TransactionState.IPTAL
                                && BackOfficeReportHelper.isBetweenDates(transactionHistoryLog, searchModel.startDate, searchModel.endDate))
                            cancelTransactionList.add(dealerTransaction);
                    });
                });

                List<Transaction> cancelTransactions = cancelTransactionList.stream().distinct().collect(Collectors.toList());
                if (cancelTransactions != null) {
                    for (Transaction cancelTransaction : cancelTransactions) {
                        if (cancelTransaction == null)
                            continue;
                        List<TransactionHistoryLog> historyLogs = cancelTransaction.getTransactionHistoryLogs();
                        if (historyLogs == null)
                            continue;
                        Collections.sort(historyLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
                        for (TransactionHistoryLog transactionHistoryLog : historyLogs) {
                            if (transactionHistoryLog == null || transactionHistoryLog.getTransactionState() == null)
                                continue;

                            if (transactionHistoryLog.getTransactionState().equals(TransactionState.BANKA_SECILDI)
                                    && BackOfficeReportHelper.isBetweenDates(transactionHistoryLog, searchModel.startDate, searchModel.endDate)) {
                                canceledAfterBankSelectedQty.addAndGet(1);
                                canceledAfterOfferQty.addAndGet(1);
                                cancelTransaction.setTransactionState(TransactionState.BANKA_SECILDI);
                                break;
                            }

                            if (transactionHistoryLog.getTransactionState().equals(TransactionState.TEKLIF_ALINDI)
                                    && BackOfficeReportHelper.isBetweenDates(transactionHistoryLog, searchModel.startDate, searchModel.endDate)) {
                                canceledAfterOfferQty.addAndGet(1);
                                cancelTransaction.setTransactionState(TransactionState.TEKLIF_ALINDI);
                                break;
                            }
                        }
                    }
                }

                Collections.sort(dealerTransactionList, Collections.reverseOrder(Comparator.comparing(Transaction::getCreatedAt)));
                item.dealerLastTransactionDate(formatDate((dealerTransactionList.get(0).getCreatedAt().getTime())));

                //Map<TransactionState, List<Transaction>> stateTransactionMap = dealerTransactionList.stream().collect(Collectors.groupingBy(Transaction::getTransactionState));
                Integer offerReceivedQuantity = 0;
                List<Transaction> offerReceivedTransactions = new ArrayList<>();

                for (TransactionState offerReceivedState : offerReceivedStates) {
                    List<Transaction> transactionList = dealerTransactionList;
                    if (transactionList == null)
                        continue;

                    List<Transaction> filteredTransactions = BackOfficeReportHelper.filterTransactions(offerReceivedState,
                            transactionList, searchModel.startDate, searchModel.endDate);
                    offerReceivedQuantity += filteredTransactions.size();
                    offerReceivedTransactions.addAll(filteredTransactions);
                }

                AtomicDouble amount = new AtomicDouble();
                for (Transaction transaction : offerReceivedTransactions) {
                    List<LoanOffer> loanOffers = transaction.getLoanOffers();
                    if (loanOffers == null || loanOffers.isEmpty())
                        continue;

                    Double maxAmount = 0.0;
                    for (LoanOffer loanOffer : loanOffers) {
                        if (loanOffer == null)
                            continue;

                        if (loanOffer.getOfferStatus() == LoanOfferStatus.OK
                                && loanOffer.getApproverAmount() > maxAmount) {
                            maxAmount = loanOffer.getApproverAmount();
                        }
                    }

                    try {
                        amount.addAndGet(maxAmount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                item.acceptedAmount(amount.get());
                if (!offerReceivedTransactions.isEmpty()) {

                    item.offerReceivedQuantity(offerReceivedQuantity);
                    item.cancellationFormOfferQuantity(canceledAfterOfferQty.get());

                    Collections.sort(offerReceivedTransactions, Comparator.comparing(Transaction::getCreatedAt));
                    try {
                        final long[] lastOfferDt = new long[1];
                        offerReceivedTransactions.get(offerReceivedTransactions.size() - 1).getTransactionHistoryLogs()
                                .stream().forEach(transactionHistoryLog -> {
                            if (transactionHistoryLog.getTransactionState() == TransactionState.TEKLIF_ALINDI) {
                                lastOfferDt[0] = transactionHistoryLog.getDate().longValue();
                            }
                        });
                        item.lastOfferDate(formatDate(lastOfferDt[0]));
                    } catch (Exception e) {
                        item.lastOfferDate(formatDate(offerReceivedTransactions.get(offerReceivedTransactions.size() - 1).getCreatedAt().getTime()));
                    }
                }

                Integer bankSelectedQuantity = 0;
                for (TransactionState bankSelectedState : bankSelectedStates) {
                    List<Transaction> transactionList = dealerTransactionList;
                    if (transactionList == null)
                        continue;

                    bankSelectedQuantity += BackOfficeReportHelper.filterTransactions(bankSelectedState, transactionList,
                            searchModel.startDate, searchModel.endDate).size();
                }

                item.bankSelectedQuantity(bankSelectedQuantity);
                item.cancellationFormBankSelectedQuantity(canceledAfterBankSelectedQty.get());

                List<Transaction> useOfLoanTransactions = dealerTransactionList;
                useOfLoanTransactions = BackOfficeReportHelper.filterTransactions(TransactionState.KREDI_KULLANDIRILDI,
                        useOfLoanTransactions, searchModel.startDate, searchModel.endDate);
                if (useOfLoanTransactions != null) {
                    item.useOfLoanQuantity(useOfLoanTransactions.size());
                    Double useOfLoanAmount = 0D;
                    for (Transaction useOfLoanTransaction : useOfLoanTransactions) {
                        if (useOfLoanTransaction == null || useOfLoanTransaction.getLoanOffers() == null)
                            continue;

                        List<LoanOffer> loanOffers = useOfLoanTransaction.getLoanOffers();
                        Optional<LoanOffer> selectedLoanOffer = loanOffers.stream().filter(loanOffer -> loanOffer.isSelectedAndNonCanceled()).findFirst();
                        if (selectedLoanOffer.isPresent()) {
                            useOfLoanAmount += selectedLoanOffer.get().getApproverAmount();
                        }
                    }
                    item.useOfLoanAmount(useOfLoanAmount);
                }

                try {
                    if (dealerAndSubDealerDetails != null) {
                        if (dealerAndSubDealerDetails.getSellerCity() != null) {
                            Optional<City> city = cityRepository.findById(dealerAndSubDealerDetails.getSellerCity().getId());
                            if (city.isPresent())
                                item.city(city.get().getName());
                        }

                        item.source(dealerAndSubDealerDetails.getSource());
                        item.portfolioManager(dealerAndSubDealerDetails.getPortfolioManager());
                    }
                } catch (Exception e) {

                }

                model.add(item.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(model, Comparator.comparing(SalesTrackingReportModel::getDealerCode));
        return model;
    }

    private synchronized String formatDate(long l) {
        return format.format(new Date(l));
    }
}
