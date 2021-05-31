package io.ngss.taksitle.report.backoffice.service;

import io.ngss.taksitle.report.backoffice.model.reports.search.TransactionLastStateCountSearchModel;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.test.DealerRepositoryNewDB;
import io.ngss.test.TransactionRepositoryNewDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/test/")
@Slf4j
public class TestService {

    @Autowired
    DealerRepositoryNewDB dealerRepositoryNewDB;

    @Autowired
    TransactionRepository transactionRepositoryNewDB;

    @PostMapping(value = "/transactionLastStateReport")
    @Transactional("transactionManager")
    public ResponseEntity getTransactionLastStateCountReport() {
        return new ResponseEntity(transactionRepositoryNewDB.findAll(), HttpStatus.OK);
    }
}
