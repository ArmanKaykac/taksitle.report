package io.ngss.test;

import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement
@RestController
@RequestMapping("/test2/")
@Slf4j
public class TestService2 {

    @Autowired
    DealerRepositoryNewDB dealerRepositoryNewDB;

    @Autowired
    TransactionRepository transactionRepositoryNewDB;
    @Autowired
    TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepositoryNewDB;

    @PostMapping(value = "/transactionLastStateReport")
    @Transactional("transactionManager")
    public ResponseEntity getTransactionLastStateCountReport() {
        return new ResponseEntity(transactionRepositoryNewDB.findAll(), HttpStatus.OK);
    }
}
