package io.ngss.taksitle.report.backoffice.service;


import io.ngss.taksitle.report.backoffice.customquery.CustomQueryRequest;
import io.ngss.taksitle.report.backoffice.manager.reports.*;
import io.ngss.taksitle.report.backoffice.model.TransactionModelBO;
import io.ngss.taksitle.report.backoffice.model.TransactionQueryType;
import io.ngss.taksitle.report.backoffice.model.reports.GrayApprovalPeriodReportModelManager;
import io.ngss.taksitle.report.backoffice.model.reports.search.*;
import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.entity.LoanRequest;
import io.ngss.taksitle.report.bank.database.entity.PreApplicationRequest;
import io.ngss.taksitle.report.bank.database.entity.TaksitleRelations;
import io.ngss.taksitle.report.bank.database.repository.BankRepository;
import io.ngss.taksitle.report.bank.database.repository.TaksitleRelationsRepository;
import io.ngss.taksitle.report.bank.ononay.model.ProblemReason;
import io.ngss.taksitle.report.bank.ononay.repository.PreApplicationRequestRepository;
import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.customermanagement.database.CustomerDetails;
import io.ngss.taksitle.report.customermanagement.repository.CustomerDetailsRepository;
import io.ngss.taksitle.report.customermanagement.repository.CustomerRepository;
import io.ngss.taksitle.report.dealer.TransactionState;
import io.ngss.taksitle.report.dealer.database.entity.BusinessConditions;
import io.ngss.taksitle.report.dealer.database.entity.Cart;
import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerFinances;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUser;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUserDetails;
import io.ngss.taksitle.report.dealer.database.repository.*;
import io.ngss.taksitle.report.transaction.database.Transaction;
import io.ngss.taksitle.report.transaction.database.entity.DeliveryInfo;
import io.ngss.taksitle.report.transaction.database.entity.TransactionHistoryLog;
import io.ngss.taksitle.report.transaction.repository.DeliveryInfoRepository;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.taksitle.report.transaction.service.model.TransactionModel;
import io.ngss.test.*;
import io.ngss.test.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@EnableTransactionManagement
@RestController
@RequestMapping("/backoffice/reports/")
@Slf4j
public class BackOfficeReportService {
    @Autowired
    private GrayApprovalPeriodReportModelManager grayApprovalPeriodReportModelManager;
    @Autowired
    private TransactionLastStateCountModelManager transactionLastStateCountModelManager;
    @Autowired
    private SalesTrackingReportModelManager salesTrackingReportModelManager;
    @Autowired
    private TcknReportManager tcknReportManager;
    @Autowired
    private OtosorReportManager otosorReportManager;
    @Autowired
    private TransactionStateCountModelManager stateCountModelManager;
    @Autowired
    private TransactionStateBaseDealerModelManager transactionStateBaseDealerModelManager;
    @Autowired
    private TransactionStateAmountModelManager transactionStateAmountModelManager;
    @Autowired
    private IncomeDocumentModelManager incomeDocumentModelManager;
    @Autowired
    private TransactionStateLogModelManager transactionStateLogModelManager;
    @Autowired
    private ApprovalRejectionReportModelManager approvalRejectionReportModelManager;
    @Autowired
    private CancelTransactionModelManager cancelTransactionModelManager;
    @Autowired
    private TransactionRepositoryNewDB transactionRepositoryNewDB;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartRepositoryNewDB cartRepositoryNewDB;
    @Autowired
    private LoanRequestRepositoryNewDB loanRequestRepositoryNewDB;
    @Autowired
    private BusinessConditionsRepository businessConditionsRepository;
    @Autowired
    private BusinessConditionsRepositoryNewDB businessConditionsRepositoryNewDB;
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private DealerRepositoryNewDB dealerRepositoryNewDB;
    @Autowired
    private DealerFinancesRepository dealerFinancesRepository;
    @Autowired
    private DealerFinancesRepositoryNewDB dealerFinancesRepositoryNewDB;
    @Autowired
    private TaksitleRelationsRepository taksitleRelationsRepository;
    @Autowired
    private TaksitleRelationsRepositoryNewDB taksitleRelationsRepositoryNewDB;
    @Autowired
    private DealerUserDetailsRepository dealerUserDetailsRepository;
    @Autowired
    private DealerUserDetailsRepositoryNewDB dealerUserDetailsRepositoryNewDB;
    @Autowired
    private DealerUserRepository dealerUserRepository;
    @Autowired
    private DealerUserRepositoryNewDB dealerUserRepositoryNewDB;
    @Autowired
    private DeliveryInfoRepository deliveryInfoRepository;
    @Autowired
    private DeliveryInfoRepositoryNewDB deliveryInfoRepositoryNewDB;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerRepositoryNewDB customerRepositoryNewDB;
    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    private CustomerDetailsRepositoryNewDB customerDetailsRepositoryNewDB;
    @Autowired
    private PreApplicationRequestRepository preApplicationRequestRepository;
    @Autowired
    private PreApplicationRequestRepositoryNewDB preApplicationRequestRepositoryNewDB;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankRepositoryNewDB bankRepositoryNewDB;


    @PostMapping(value = "/grayResponsePeriodReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    @Transactional(value = "transactionManager2", readOnly = true)
    public ResponseEntity getGrayResponsePeriodReport(@Valid @RequestBody GrayApprovalPeriodReportSearchModel searchModel) {
        return new ResponseEntity(grayApprovalPeriodReportModelManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/transactionLastStateReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    @Transactional("transactionManager2")
    public ResponseEntity getTransactionLastStateCountReport(@Valid @RequestBody TransactionLastStateCountSearchModel searchModel) {
        return new ResponseEntity(transactionLastStateCountModelManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/salesTrackingReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    @Transactional("transactionManager2")
    public ResponseEntity getSalesTrackingReport(@Valid @RequestBody SalesTrackingReportSearchModel searchModel) {

        return new ResponseEntity(salesTrackingReportModelManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/tcknReport")
    @Transactional("transactionManager2")
    public ResponseEntity getTcknReport(@Valid @RequestBody OtosorReportSearchModel searchModel) {
        return new ResponseEntity(tcknReportManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/otosorReport")
    @Transactional(value = "transactionManager2", readOnly = true)
    public ResponseEntity getOtosorReport(@Valid @RequestBody OtosorReportSearchModel otosorReportSearchModel) {
        return new ResponseEntity(otosorReportManager.filterModel(otosorReportSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/getDailyTransactionState")
    @Transactional(value = "transactionManager", readOnly = true)
    public ResponseEntity getDailyTransactionState(@Valid @RequestBody TransactionStateCountSearchModel transactionStateCountSearchModel) {
        return new ResponseEntity(stateCountModelManager.filterModelWithState(transactionStateCountSearchModel, false), HttpStatus.OK);
    }

    //istenmiyor
//    @PostMapping(value = "/countsByDealer")
//    @Transactional("inmemoryDatabaseTransactionManager")
//    public ResponseEntity getTransactionStateBaseDealer(@Valid @RequestBody TransactionStateBaseDealerSearchModel searchModel) {
//        return new ResponseEntity(transactionStateBaseDealerModelManager.filterModel(searchModel), HttpStatus.OK);
//    }

    @PostMapping(value = "/amountsByDealer")
    @Transactional(value = "transactionManager", readOnly = true)
    public ResponseEntity getTransactionStateAmount(@Valid @RequestBody TransactionStateBaseDealerSearchModel transactionStateBaseDealerSearchModel) {
        return new ResponseEntity(transactionStateAmountModelManager.filterModel(transactionStateBaseDealerSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/incomeDocument")
    //@Transactional("inmemoryDatabaseTransactionManager")
    @Transactional(value = "transactionManager2", readOnly = true)
    public ResponseEntity getIncomeDocumentReport(@Valid @RequestBody IncomeDocumentSearchModel incomeDocumentSearchModel) {
        return new ResponseEntity(incomeDocumentModelManager.filterModel(incomeDocumentSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/transactionStateLogs")
    @Transactional(value = "transactionManager2", readOnly = true)
    public ResponseEntity getTransactionStateBaseDealer(@Valid @RequestBody TransactionStateLogSearchModel transactionStateLogSearchModel) {
        return new ResponseEntity(transactionStateLogModelManager.filterModel(transactionStateLogSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/approvalRejectionReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    @Transactional(value = "transactionManager2", readOnly = true)
    public ResponseEntity getApprovalRejectionReport(@Valid @RequestBody ApprovalRejectionReportSearchModel searchModel) {
        return new ResponseEntity(approvalRejectionReportModelManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/cancelReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    public ResponseEntity getTransaction(@Valid @RequestBody CancelTransactionSearchModel cancelTransactionSearchModel) {
        return new ResponseEntity(cancelTransactionModelManager.filterModel(cancelTransactionSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/testDB")
    public void saveDB() {
        Long A_DAY_IN_LONG = 86400000L;
        Date selectedDate = new Date(new Date().getTime() - A_DAY_IN_LONG * 1);
        List<Transaction> transactions = transactionRepository.findAllByStateUpdateDateAfter(selectedDate);
        System.out.println(transactions.size());
        for (Transaction transaction : transactions) {
            LoanRequest loanRequest = transaction.getLoanRequest();
            loanRequest.setCart(null);
            loanRequest.setTransaction(null);
            loanRequestRepositoryNewDB.save(loanRequest);
            Cart cart = cartRepository.findByLoanRequestId(loanRequest.getId());
            if (cart != null) {
                if (cart.getLoanRequest().getId().equals(loanRequest.getId())) {
                    loanRequest.setCart(cart);
                    cartRepositoryNewDB.save(cart);

                }
            }
            loanRequestRepositoryNewDB.save(loanRequest);
            Dealer dealer = transaction.getDealer();

            BusinessConditions businessConditions = businessConditionsRepository.findByDealerId(dealer.getId());
            businessConditionsRepositoryNewDB.save(businessConditions);

            DealerFinances dealerFinances = dealerFinancesRepository.findByDealerId(dealer.getId());

            dealerFinances.setDealer(null);

            dealerFinancesRepositoryNewDB.save(dealerFinances);

            TaksitleRelations taksitleRelations = taksitleRelationsRepository.findByDealerId(dealer.getId());
            taksitleRelations.setDealer(null);
            taksitleRelationsRepositoryNewDB.save(taksitleRelations);
            dealer.setTaksitleRelations(taksitleRelations);
            dealer.setDealerFinances(dealerFinances);
            dealerRepositoryNewDB.save(dealer);
            dealerFinancesRepositoryNewDB.save(dealerFinances);
            taksitleRelations.setDealer(dealer);
            taksitleRelationsRepositoryNewDB.save(taksitleRelations);

            List<DealerUser> dealerUsers = dealerUserRepository.findAllByDealerId(dealer.getId());
            for (DealerUser dealerUser : dealerUsers) {
                dealerUser.setDealerUserDetails(null);
                dealerUserRepositoryNewDB.save(dealerUser);
                DealerUserDetails dealerUserDetails = dealerUserDetailsRepository.findByDealerUserId(dealerUser.getId());
                if (dealerUserDetails != null) {
                    dealerUserDetailsRepositoryNewDB.save(dealerUserDetails);
                    dealerUser.setDealerUserDetails(dealerUserDetails);
                }

                dealerUserRepositoryNewDB.save(dealerUser);
            }

            DeliveryInfo deliveryInfo = deliveryInfoRepository.findByTransactionId(transaction.getId());
            if (deliveryInfo != null) {
//                deliveryInfo.setTransaction(null);
                transaction.setDeliveryInfoS(null);
                transaction.setCustomer(null);


                for (PreApplicationRequest preApplicationRequest : transaction.getPreApplicationRequests()) {
                    if(preApplicationRequest.getProblemReason() == null){
                        preApplicationRequest.setProblemReason(ProblemReason.NotExist);
                    }
                }
                transactionRepositoryNewDB.save(transaction);
                deliveryInfoRepositoryNewDB.save(deliveryInfo);
            }
            Customer customer = customerRepository.findByTckno(transaction.getCustomer().getTckno());
            CustomerDetails customerDetails = customerDetailsRepository.getById(customer.getCustomerDetails().getId());
            customerDetails.setCustomer(null);
            customerRepositoryNewDB.save(customer);
            customerDetails.setCustomer(customer);
            customerDetailsRepositoryNewDB.save(customerDetails);
            List<Bank> banks = bankRepository.findAll();
            banks.stream().forEach(bank1 -> bankRepositoryNewDB.save(bank1));
            List<PreApplicationRequest> preApplicationRequests = preApplicationRequestRepository.findAllByTransactionToken(transaction.getToken());
            preApplicationRequests.stream().forEach(preApplicationRequest -> preApplicationRequestRepositoryNewDB.save(preApplicationRequest));

            transactionRepositoryNewDB.save(transaction);
            loanRequest.setTransaction(transaction);
            loanRequestRepositoryNewDB.save(loanRequest);
            if(deliveryInfo != null){
                deliveryInfo.setTransaction(transaction);
                deliveryInfoRepositoryNewDB.save(deliveryInfo);
            }

        }

    }


    @PostMapping(value = "/customTransactionSearch")
    public ResponseEntity customTransactionSearch(@Valid @RequestBody CustomQueryRequest customQueryRequest) {
        List<Transaction> transactionList = transactionRepository.search(customQueryRequest, true);
        if (customQueryRequest.getTransactionQueryType() == TransactionQueryType.TRANSACTION) {

            List<TransactionModel> transactionModels = TransactionModel.convertToTransactionModelList(transactionList, false);
            transactionModels.sort(Comparator.comparing(TransactionModel::getToken).reversed());

            return new ResponseEntity(transactionModels, HttpStatus.OK);

        } else if (customQueryRequest.getTransactionQueryType() == TransactionQueryType.HISTORY) {
            List<Pair<Transaction, String>> pairList = new ArrayList<>();
            for (Transaction transaction : transactionList) {
                Optional<TransactionHistoryLog> opt = transactionHistoryLogRepository.findFirstByTransactionIdAndTransactionState(transaction.getId(), TransactionState.TASLAK);
                String ipAddress = opt.isPresent() ? opt.get().getIpAddressV4() : "0";
                pairList.add(Pair.of(transaction, ipAddress));
            }
            List<TransactionModelBO> transactionModelBOList = TransactionModelBO.convertToBOTransactionModels(pairList);
            transactionModelBOList.sort(Comparator.comparing(TransactionModelBO::getTransactionDate).reversed());
            System.out.println(transactionModelBOList.size());
            return new ResponseEntity(transactionModelBOList, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
