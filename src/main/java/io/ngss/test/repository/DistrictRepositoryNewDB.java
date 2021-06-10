package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepositoryNewDB extends JpaRepository<District, Long> {

    District findByNameAndCityId(String name, Long id);

    List<District> findAllByCityId(Long code);
}
