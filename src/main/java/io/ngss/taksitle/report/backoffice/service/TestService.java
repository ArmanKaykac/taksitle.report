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
import java.util.Date;

@EnableTransactionManagement
@RestController
@RequestMapping("/test/")
@Slf4j
public class TestService {

    @Autowired
    DealerRepositoryNewDB dealerRepositoryNewDB;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping(value = "/transactionLastStateReport")
    @Transactional("transactionManager")
    public ResponseEntity getTransactionLastStateCountReport() {
        Date selectedDate = new Date(new Date().getTime() - 86400000L * 1);
        System.out.println(transactionRepository.findAllByStateUpdateDateAfter(selectedDate));
        System.out.printf("sda");
        return new ResponseEntity(transactionRepository.findAll(), HttpStatus.OK);
    }
}
