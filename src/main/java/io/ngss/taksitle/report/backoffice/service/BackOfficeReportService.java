package io.ngss.taksitle.report.backoffice.service;


import io.ngss.taksitle.report.backoffice.manager.reports.*;
import io.ngss.taksitle.report.backoffice.model.reports.search.*;
import io.ngss.taksitle.report.backoffice.model.reports.GrayApprovalPeriodReportModelManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping(value = "/grayResponsePeriodReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    //@Transactional("persistentDatabaseTransactionManager")
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
    @Transactional("transactionManager2")
    public ResponseEntity getOtosorReport(@Valid @RequestBody OtosorReportSearchModel otosorReportSearchModel) {
        return new ResponseEntity(otosorReportManager.filterModel(otosorReportSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/getDailyTransactionState")
    //@Transactional("inmemoryDatabaseTransactionManager")
    public ResponseEntity getDailyTransactionState(@Valid @RequestBody TransactionStateCountSearchModel transactionStateCountSearchModel) {
        return new ResponseEntity(stateCountModelManager.filterModelWithState(transactionStateCountSearchModel, false), HttpStatus.OK);
    }

//    @PostMapping(value = "/countsByDealer")
//    @Transactional("inmemoryDatabaseTransactionManager")
//    public ResponseEntity getTransactionStateBaseDealer(@Valid @RequestBody TransactionStateBaseDealerSearchModel searchModel) {
//        return new ResponseEntity(transactionStateBaseDealerModelManager.filterModel(searchModel), HttpStatus.OK);
//    }

    @PostMapping(value = "/amountsByDealer")
    //@Transactional("inmemoryDatabaseTransactionManager")
    public ResponseEntity getTransactionStateAmount(@Valid @RequestBody TransactionStateBaseDealerSearchModel transactionStateBaseDealerSearchModel) {
        return new ResponseEntity(transactionStateAmountModelManager.filterModel(transactionStateBaseDealerSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/incomeDocument")
    //@Transactional("inmemoryDatabaseTransactionManager")
    //@Transactional("persistentDatabaseTransactionManager")
    public ResponseEntity getIncomeDocumentReport(@Valid @RequestBody IncomeDocumentSearchModel incomeDocumentSearchModel) {
        return new ResponseEntity(incomeDocumentModelManager.filterModel(incomeDocumentSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/transactionStateLogs")
    //@Transactional("inmemoryDatabaseTransactionManager")
    public ResponseEntity getTransactionStateBaseDealer(@Valid @RequestBody TransactionStateLogSearchModel transactionStateLogSearchModel) {
        return new ResponseEntity(transactionStateLogModelManager.filterModel(transactionStateLogSearchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/approvalRejectionReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    //@Transactional("persistentDatabaseTransactionManager")
    public ResponseEntity getApprovalRejectionReport(@Valid @RequestBody ApprovalRejectionReportSearchModel searchModel) {
        return new ResponseEntity(approvalRejectionReportModelManager.filterModel(searchModel), HttpStatus.OK);
    }

    @PostMapping(value = "/cancelReport")
    //@Transactional("inmemoryDatabaseTransactionManager")
    public ResponseEntity getTransaction(@Valid @RequestBody CancelTransactionSearchModel cancelTransactionSearchModel) {
        return new ResponseEntity(cancelTransactionModelManager.filterModel(cancelTransactionSearchModel), HttpStatus.OK);
    }
}
