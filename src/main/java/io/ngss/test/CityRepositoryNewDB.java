package io.ngss.test;

import io.ngss.taksitle.report.dealer.database.City;
import io.ngss.taksitle.report.transaction.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepositoryNewDB extends JpaRepository<City, Long>, JpaSpecificationExecutor<Transaction> {

    City findByName(String name);

    Optional<City> findById(Long id);
}
