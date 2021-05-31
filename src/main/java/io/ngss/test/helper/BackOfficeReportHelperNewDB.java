package io.ngss.test.helper;

import io.ngss.taksitle.report.backoffice.helper.TransactionHelper;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionLastStateCountModel;
import io.ngss.taksitle.report.backoffice.model.reports.TransactionStateLogModel;
import io.ngss.taksitle.report.bank.database.entity.LoanOffer;
import io.ngss.taksitle.report.bank.database.entity.PaymentPlanDetails;
import io.ngss.taksitle.report.bank.database.entity.PreApplicationRequest;
import io.ngss.taksitle.report.bank.database.enums.LoanOfferStatus;
import io.ngss.taksitle.report.bank.ononay.model.PreApplicationResponseCode;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.Cart;
import io.ngss.taksitle.report.dealer.database.entity.CartProduct;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.shared.database.entity.Document;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.TransactionHistoryLogRepository;
import io.ngss.test.TransactionHistoryLogRepositoryNewDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class BackOfficeReportHelperNewDB {

    @Autowired
    private TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepository;

    private static Map<TransactionState, List<TransactionState>> stateGroups = new HashMap<>();

    private TransactionState[] notInStates = new TransactionState[]{
            TransactionState.TASLAK,
            TransactionState.TEKNIK_ARIZA,
            TransactionState.ON_DEGERLENDIRME_OLUMLU
    };

    public List<Transaction> getCustomerBasedTransactions(List<Transaction> transactions, Boolean requiredPreviousStateForCancellations) {

        List<TransactionState[]> stateArrayList = new LinkedList<>();
        TransactionState[] primaryStates = new TransactionState[]{
                TransactionState.KREDI_KULLANDIRILDI,
                TransactionState.EVRAKLAR_BANKA_ONAYLI,
                TransactionState.ILK_DOKUMAN_SETI_ONAYLI,
                TransactionState.EVRAK_KONTROL_SURECI,
                TransactionState.EVRAKLAR_TAMAMLANDI,
                TransactionState.EVRAK_BEKLENIYOR
        };

        stateArrayList.add(new TransactionState[]{
                TransactionState.BANKA_SECILDI
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.TEKLIF_ALINDI
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.ISTIHBARAT_DEGERLENDIRME,
                TransactionState.TAHSIS_DEGERLENDIRME
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.REDDEDILDI,
                TransactionState.ISTIHBARATTAN_RED,
                TransactionState.GRIDEN_RED
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.SIPARIS_GIRILIYOR,
                TransactionState.SIPARIS_TAMAMLANDI
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.ON_DEGERLENDIRME_OLUMSUZ
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.ON_DEGERLENDIRME_OLUMLU
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.TEKNIK_ARIZA
        });
        stateArrayList.add(new TransactionState[]{
                TransactionState.TASLAK
        });

        List<Transaction> transactionListLast = new ArrayList<>();

        Map<Customer, List<Transaction>> groupedByCustomer = transactions.stream().collect(Collectors.groupingBy(Transaction::getCustomer));
        groupedByCustomer.forEach((customer, transactionList) -> {

            Collections.sort(transactionList, Collections.reverseOrder(Comparator.comparing(Transaction::getCreatedAt)));

            List<Transaction> cancelTransactionss = transactionList.stream().filter(x -> x.getTransactionState().equals(TransactionState.IPTAL)).collect(Collectors.toList());
            if (requiredPreviousStateForCancellations) {
                cancelTransactionss.forEach(x -> {

                    List<TransactionHistoryLog> historyLogs = x.getTransactionHistoryLogs();
                    Collections.sort(historyLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
                    TransactionState previousState = historyLogs.get(0).getTransactionState();
                    x.setTransactionState(previousState);
                });
            }
            List<Transaction> availableTransactionsByCustomer = transactionList.stream().filter(sd -> Arrays.stream(primaryStates).filter(x -> x.equals(sd.getTransactionState())).findAny().isPresent()).collect(Collectors.toList());
            if (!availableTransactionsByCustomer.isEmpty()) {
                transactionListLast.addAll(availableTransactionsByCustomer);
                if (requiredPreviousStateForCancellations) {
                    cancelTransactionss.forEach(x -> {

                        List<TransactionHistoryLog> historyLogs = x.getTransactionHistoryLogs();
                        Collections.sort(historyLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
                        TransactionState currentState = historyLogs.get(0).getTransactionState();
                        x.setTransactionState(currentState);
                    });
                }
                return;
            }

            for (TransactionState[] transactionStates : stateArrayList) {

                boolean contiune = getAvailableTransactions(transactionList, transactionStates, transactionListLast);
                if (contiune) {
                    if (requiredPreviousStateForCancellations) {
                        cancelTransactionss.forEach(x -> {

                            List<TransactionHistoryLog> historyLogs = x.getTransactionHistoryLogs();
                            Collections.sort(historyLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
                            TransactionState currentState = historyLogs.get(0).getTransactionState();
                            x.setTransactionState(currentState);
                        });
                    }
                    return;
                }
            }
        });
        return transactionListLast;
    }

    // utilization
    private boolean getAvailableTransactions(List<Transaction> transactionList, TransactionState[] states, List<Transaction> model) {

        List<Transaction> transactionsByStateFilter = transactionList.stream().filter(sd -> Arrays.stream(states).filter(x -> x.equals(sd.getTransactionState())).findAny().isPresent()).collect(Collectors.toList());
        if (transactionsByStateFilter.isEmpty())
            return false;
        Collections.sort(transactionsByStateFilter, Collections.reverseOrder(Comparator.comparing(Transaction::getCreatedAt)));
        Transaction lastTransaction = transactionsByStateFilter.get(0);
        model.add(lastTransaction);
        return true;
    }

    public List<TransactionStateLogModel> prepareLogReport(List<Transaction> transactions) {

        List<TransactionStateLogModel> model = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        prapareStateGroupMap();

        transactions.forEach(x -> {
            try {
                List<TransactionHistoryLog> transactionHistoryLogs = x.getTransactionHistoryLogs();
                if (transactionHistoryLogs == null) return;

                Map<String, String> stateMap = new HashMap<>();
                transactionHistoryLogs.forEach(log -> {
                    if (log.getExceptionalState() != null && log.getExceptionalState().equals(true) && log.getTransactionState() != TransactionState.IPTAL)
                        stateMap.put(log.getExceptionalStateName(), df.format(new Date(log.getDate())));
                    else
                        stateMap.put(log.getTransactionState().toString(), df.format(new Date(log.getDate())));
                });
                String approvedAmount = "";
                String term = "";
                String kkbScore = "";
                Double installmentAmount = null;
                String brand = "";
                String modelCar = "";
                Integer modelYear = null;
                Integer hullInsuranceAmount = null;
                Double salesAmount = null;
                TransactionState previousState = null;

                Collections.sort(transactionHistoryLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
                previousState = transactionHistoryLogs.size() >= 2 ? transactionHistoryLogs.get(1).getTransactionState() : x.getTransactionState();

                if (x.getPreApplicationRequests() != null) {

                    Optional<PreApplicationRequest> preApplicationRequest = x.getPreApplicationRequests().stream().filter(p -> p.getPreApplicationResponseCode().equals(PreApplicationResponseCode.OK)).findFirst();
                    if (preApplicationRequest.isPresent()) {

                        PreApplicationRequest value = preApplicationRequest.get();
                        kkbScore = value.getKkbScore();
                    }
                }
                if (TransactionHelper.isCar(x)) {

                    Cart cart = x.getLoanRequest().getCart();
                    if (cart != null) {
                        CartProduct cartProduct = cart.getCartProducts() != null && cart.getCartProducts().size() > 0 ? cart.getCartProducts().get(0) : null;
                        if (cartProduct != null) {
                            brand = cartProduct.getProduct().getBrand().getName();
                            modelCar = cartProduct.getProduct().getName();
                            modelYear = cartProduct.getModelYear();
                            hullInsuranceAmount = cartProduct.getHullInsuranceAmount();
                            salesAmount = cartProduct.getPrice();
                        }
                    }
                }
                if (x.getLoanOffers() != null) {

                    Optional<LoanOffer> acceptedLoanOffer = x.getLoanOffers().stream().filter(l -> l.getOfferStatus().equals(LoanOfferStatus.OK)).findFirst();
                    if (acceptedLoanOffer.isPresent()) {
                        LoanOffer loanOffer = acceptedLoanOffer.get();
                        PaymentPlanDetails paymentPlanDetail = loanOffer.getPaymentPlan().getPaymentPlanDetails().get(0);
                        installmentAmount = paymentPlanDetail != null ? paymentPlanDetail.getInstallmentAmount() : null;
                        if (loanOffer != null) {
                            if (loanOffer.getApproverAmount() != null) {
                                approvedAmount = loanOffer.getApproverAmount().toString();
                            }
                            if (loanOffer.getTerm() != null) {
                                term = loanOffer.getTerm().toString();
                            }
                        }
                    }
                }

                TransactionState tempPreviousState = previousState;
                TransactionState lastState = stateGroups.entrySet()
                        .stream()
                        .filter(element ->
                                element.getValue()
                                        .stream()
                                        .anyMatch(
                                                value -> value.equals(x.getTransactionState().equals(TransactionState.IPTAL) ? tempPreviousState : x.getTransactionState())))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

                TransactionStateLogModel item = new TransactionStateLogModel.Builder()
                        .transactionToken(x.getToken())
                        .dealerName(x.getDealer() != null ? x.getDealer().getName() : "")
                        .tckno(x.getCustomer() != null ? x.getCustomer().getTckno().toString() : "")
                        .requestAmount(x.getLoanRequest() != null ? x.getLoanRequest().getRequestedLoanAmount().toString() : "")
                        .acceptedAmount(approvedAmount)
                        .term(term)
                        .transactionStateDateMap(stateMap)
                        .customerName(x.getCustomer() != null ? x.getCustomer().getCustomerDetails().getName() : "")
                        .kkbScore(kkbScore)
                        .installmentAmount(installmentAmount)
                        .brand(brand)
                        .model(modelCar)
                        .modelYear(modelYear)
                        .hullInsuranceAmount(hullInsuranceAmount)
                        .salesAmount(salesAmount)
                        .previousState(x.getTransactionState().equals(TransactionState.IPTAL) ? previousState : null)
                        .lastState(lastState)
                        .build();

                model.add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return model;
    }

    // utilization
    private void prapareStateGroupMap() {
        if (!stateGroups.isEmpty())
            return;

        TransactionState[] offerReceivedStates = new TransactionState[]{
                TransactionState.TEKLIF_ALINDI,
                TransactionState.EVRAKLAR_BANKA_ONAYLI,
                TransactionState.ILK_DOKUMAN_SETI_ONAYLI,
                TransactionState.EVRAK_KONTROL_SURECI,
                TransactionState.EVRAKLAR_TAMAMLANDI,
                TransactionState.EVRAK_BEKLENIYOR,
                TransactionState.BANKA_SECILDI
        };
        TransactionState[] preAppPositiveStates = new TransactionState[]{
                TransactionState.TAHSIS_DEGERLENDIRME,
                TransactionState.ISTIHBARAT_DEGERLENDIRME,
                TransactionState.SIPARIS_GIRILIYOR,
                TransactionState.SIPARIS_TAMAMLANDI,
                TransactionState.ON_DEGERLENDIRME_OLUMLU
        };
        TransactionState[] rejectStates = new TransactionState[]{
                TransactionState.GRIDEN_RED,
                TransactionState.ISTIHBARATTAN_RED
        };
        TransactionState[] allRejectStates = new TransactionState[]{
                TransactionState.REDDEDILDI
        };
        TransactionState[] acceptedState = new TransactionState[]{
                TransactionState.KREDI_KULLANDIRILDI
        };
        TransactionState[] preAppNegativeState = new TransactionState[]{
                TransactionState.ON_DEGERLENDIRME_OLUMSUZ
        };
        TransactionState[] technicalProblemState = new TransactionState[]{
                TransactionState.TEKNIK_ARIZA
        };
        TransactionState[] startState = new TransactionState[]{
                TransactionState.TASLAK
        };
        stateGroups.put(TransactionState.TEKLIF_ALINDI, new ArrayList<>(Arrays.asList(offerReceivedStates)));
        stateGroups.put(TransactionState.ON_DEGERLENDIRME_OLUMLU, Arrays.asList(preAppPositiveStates));
        stateGroups.put(TransactionState.KREDI_KULLANDIRILDI, Arrays.asList(acceptedState));
        stateGroups.put(TransactionState.ON_DEGERLENDIRME_OLUMSUZ, Arrays.asList(preAppNegativeState));
        stateGroups.put(TransactionState.TEKNIK_ARIZA, Arrays.asList(technicalProblemState));
        stateGroups.put(TransactionState.GRIDEN_RED, Arrays.asList(rejectStates));
        stateGroups.put(TransactionState.REDDEDILDI, Arrays.asList(allRejectStates));
        stateGroups.put(TransactionState.TASLAK, Arrays.asList(startState));
    }

    public Boolean existIncomeDocument(List<Document> documents) {
        return documents.stream().anyMatch(x -> x.getDocumentName().equals("Gelir Belgesi"));
    }

    public List<TransactionLastStateCountModel> prepareTransactionStateList(List<Transaction> transactions, Boolean onlyDealer) {

        List<TransactionLastStateCountModel> model = new ArrayList<>();
        List<TransactionLastStateCountModel> transactionModel = new ArrayList<>();

        Map<TransactionState, List<Transaction>> transactionStateListMap = transactions.stream().collect(Collectors.groupingBy(Transaction::getTransactionState));
        AtomicInteger totalIncomeDocumentQuantity = new AtomicInteger();
        transactionStateListMap.forEach(((state, transactionList) -> {

            Integer incomeDocumentQuantity = countContainsIncomeDocument(transactionList);

            if (!onlyDealer) {
                TransactionLastStateCountModel item = new TransactionLastStateCountModel(state.toString(), transactionList.size());
                item.setIncomeDocumentContainsQuantity(incomeDocumentQuantity);
                transactionModel.add(item);
            }
            Integer newTotalIncomeDocumentQuantity = totalIncomeDocumentQuantity.intValue();
            totalIncomeDocumentQuantity.set(newTotalIncomeDocumentQuantity + incomeDocumentQuantity);

        }));
        model.addAll(transactionModel);
        return model;
    }

    public TransactionLastStateCountModel prepareTotalValuesByTransactions(List<Transaction> transactions, String totalRowTitle, Dealer dealer, boolean summary) {

        Integer transactionLength = countNotInThisStates(transactions, notInStates);

        TransactionLastStateCountModel totalValues = new TransactionLastStateCountModel(totalRowTitle, transactionLength);
        if (transactions.size() <= 0)
            return totalValues;

        Map<TransactionState, List<Transaction>> transactionStateListMap = transactions.stream().collect(Collectors.groupingBy(Transaction::getTransactionState));

        AtomicInteger totalIncomeDocumentQuantity = new AtomicInteger();
        transactionStateListMap.forEach(((state, transactionList) -> {

            Integer incomeDocumentQuantity = countContainsIncomeDocument(transactionList);
            Integer newTotalIncomeDocumentQuantity = totalIncomeDocumentQuantity.intValue();
            totalIncomeDocumentQuantity.set(newTotalIncomeDocumentQuantity + incomeDocumentQuantity);

        }));

        totalValues.setIncomeDocumentContainsQuantity(totalIncomeDocumentQuantity.intValue());

        Integer offerReceivedQuantity = transactionStateListMap.get(TransactionState.TEKLIF_ALINDI) != null ? transactionStateListMap.get(TransactionState.TEKLIF_ALINDI).size() : 0;
        Integer useOfLoanQuantity = transactionStateListMap.get(TransactionState.KREDI_KULLANDIRILDI) != null ? transactionStateListMap.get(TransactionState.KREDI_KULLANDIRILDI).size() : 0;
        Integer acceptedTransactionCount = offerReceivedQuantity + useOfLoanQuantity;
        double useOfLoanRate = acceptedTransactionCount > 0 ? (useOfLoanQuantity * 100.0 / acceptedTransactionCount) : 0;
        totalValues.setUseOfLoanRate(format(useOfLoanRate));

        double acceptedRate = transactionLength > 0 ? (acceptedTransactionCount * 100.0 / transactionLength) : 0;
        totalValues.setAcceptedRate(format(acceptedRate));

        double incomeDocumentRate = acceptedTransactionCount > 0 ? (totalIncomeDocumentQuantity.doubleValue() * 100.0 / acceptedTransactionCount) : 0;
        totalValues.setIncomeDocumentRate(format(incomeDocumentRate));

        try {
            if (dealer != null && dealer.getDealerAndSubDealerDetails() != null) {
                totalValues.setSource(dealer.getDealerAndSubDealerDetails().getSource());
                totalValues.setPortfolioManager(dealer.getDealerAndSubDealerDetails().getPortfolioManager());
                totalValues.setCity(dealer.getDealerAndSubDealerDetails().getSellerCity() == null ? ""
                        : dealer.getDealerAndSubDealerDetails().getSellerCity().getName());
                totalValues.setCode(dealer.getDealerAndSubDealerDetails().getDealer().getId());
            }
        } catch (Exception e) {

        }

        return totalValues;
    }

    public List<Transaction> getTransactionsByHistoryLogState(List<Transaction> transactions, TransactionState state) {

        List<Transaction> availableTransactions = new ArrayList<>();
        transactions.forEach(transaction -> {

            List<TransactionHistoryLog> historyLogs = transaction.getTransactionHistoryLogs();
            Boolean isInclude = containsStateInHistoryLogs(historyLogs, state);
            if (isInclude)
                availableTransactions.add(transaction);

        });
        return availableTransactions;
    }

    public Boolean containsStateInHistoryLogs(List<TransactionHistoryLog> historyLogs, TransactionState state) {

        if (historyLogs == null || state == null)
            return false;

        return historyLogs.stream().anyMatch(historyLog -> historyLog.getTransactionState().equals(state));
    }

    public void changeTransactionsStateByLastState(List<Transaction> transactions) {
        prapareStateGroupMap();
        List<Transaction> transactionsToRemove = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionState lastState = stateGroups.entrySet()
                    .stream()
                    .filter(element ->
                            element.getValue()
                                    .stream()
                                    .anyMatch(
                                            value -> value.equals(transaction.getTransactionState())))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (Arrays.asList(notInStates).contains(lastState)) {
                transactionsToRemove.add(transaction);
            } else if (lastState != null) {
                transaction.setTransactionState(lastState);
            }
        }

        transactions.removeAll(transactionsToRemove);
    }

    public Integer countNotInThisStates(List<Transaction> transactions, TransactionState[] states) {
        prapareStateGroupMap();
        Integer quantity = 0;

        for (Transaction transaction : transactions) {
            TransactionState lastState = stateGroups.entrySet()
                    .stream()
                    .filter(element ->
                            element.getValue()
                                    .stream()
                                    .anyMatch(
                                            value -> value.equals(transaction.getTransactionState())))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);
            if (!Arrays.asList(states).contains(lastState))
                quantity++;
        }
        return quantity;
    }

    public Integer countContainsIncomeDocument(List<Transaction> transactions) {
        Integer count = 0;
        for (Transaction transaction : transactions) {
            if (transaction == null)
                continue;

            List<Document> documents = transaction.getDocuments();
            if (documents != null && documents.size() > 0 && existIncomeDocument(documents))
                count++;
        }

        return count;
    }

    public List<Transaction> getTransactionsByState(List<Transaction> transactions, TransactionState[] state) {

        return transactions.stream().filter(t -> Arrays.asList(state).contains(t.getTransactionState())).collect(Collectors.toList());
    }

    public Integer countTransactionsPreviousStateByCurrentState(List<Transaction> transactions, TransactionState previousState) {

        Integer quantity = 0;
        for (Transaction transaction : transactions) {

            List<TransactionHistoryLog> transactionHistoryLogs = transaction.getTransactionHistoryLogs();
            if (transactionHistoryLogs.stream().anyMatch(transactionHistoryLog -> transactionHistoryLog.getTransactionState().equals(previousState)))
                quantity++;
        }
        return quantity;
    }

    public List<Transaction> getTcknTransactions(Long start, Long end) {
        if (start == null)
            return null;

        if (end == null)
            end = System.currentTimeMillis();

        List<TransactionHistoryLog> transactionHistoryLogList = transactionHistoryLogRepository.findAllByDateIsBetween(start, end);
        if (transactionHistoryLogList == null)
            return null;

        Map<Integer, Transaction> transactionMap = new HashMap<>();
        transactionHistoryLogList.stream().forEach(t -> transactionMap.put(t.getTransaction().getToken(), t.getTransaction()));

        List<Transaction> transactions = transactionMap.values().stream().collect(Collectors.toList());
        transactions.stream().filter(t -> t.getTransactionState().equals(TransactionState.IPTAL)).forEach(cancelTransaction -> {
            List<TransactionHistoryLog> historyLogs = cancelTransaction.getTransactionHistoryLogs();
            Collections.sort(historyLogs, Collections.reverseOrder(Comparator.comparing(TransactionHistoryLog::getDate)));
            TransactionState previousState = historyLogs.get(0).getTransactionState();
            cancelTransaction.setTransactionState(previousState);
        });

        return getCustomerBasedTransactions(transactions, true);
    }

    public static String format(double value) {
        return String.format("%.2f", value).concat(" %");
    }

    public static List<Transaction> filterTransactions(TransactionState state,
                                                       List<Transaction> transactionList,
                                                       Long start,
                                                       Long end) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        if (transactionList == null || transactionList.isEmpty())
            return filteredTransactions;

        for (Transaction transaction : transactionList) {
            if (transaction == null || transaction.getTransactionHistoryLogs() == null)
                continue;

            Optional<TransactionHistoryLog> transactionHistoryLogOpt = transaction.getTransactionHistoryLogs()
                    .stream().filter(transactionHistoryLog -> transactionHistoryLog.getTransactionState() == state).findFirst();
            if (transactionHistoryLogOpt.isPresent() && isBetweenDates(transactionHistoryLogOpt.get(), start, end))
                filteredTransactions.add(transaction);
        }

        return filteredTransactions;
    }

    public static boolean isBetweenDates(TransactionHistoryLog transactionHistoryLog, Long start, Long end) {
        if (transactionHistoryLog == null || transactionHistoryLog.getDate() == null)
            return false;

        return transactionHistoryLog.getDate() >= start
                && transactionHistoryLog.getDate() <= end;
    }
}
