package io.ngss.test;

import io.ngss.taksitle.report.dealer.database.repository.BusinessConditionsRepository;
import io.ngss.taksitle.report.dealer.database.repository.DealerFinancesRepository;
import io.ngss.taksitle.report.dealer.database.repository.DealerRepository;
import io.ngss.taksitle.report.transaction.repository.TransactionRepository;
import io.ngss.test.DealerRepositoryNewDB;
import io.ngss.test.TransactionHistoryLogRepositoryNewDB;
import io.ngss.test.repository.DealerFinancesRepositoryNewDB;
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
    DealerRepository dealerRepository;
    @Autowired
    DealerFinancesRepository dealerFinancesRepository;
    @Autowired
    DealerFinancesRepositoryNewDB dealerFinancesRepositoryNewDB;
    @Autowired
    BusinessConditionsRepository businessConditionsRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionHistoryLogRepositoryNewDB transactionHistoryLogRepositoryNewDB;

    @PostMapping(value = "/transactionLastStateReport")
    @Transactional("transactionManager")
    public void getTransactionLastStateCountReport() {
        // return new ResponseEntity(dealerRepository.findAll(), HttpStatus.OK);
        System.out.println(businessConditionsRepository.findAll());
    }
}
