package io.ngss.taksitle.report.bank.database.repository;

import io.ngss.taksitle.report.bank.database.entity.Bank;
import io.ngss.taksitle.report.bank.database.enums.Banks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String name);
    Bank findByCode(Integer code);
    Bank findByTaxOffice(String taxOffice);
    Bank findByBanks(Banks banks);
}
