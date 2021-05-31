package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

    Optional<City> findById(Long id);
}
