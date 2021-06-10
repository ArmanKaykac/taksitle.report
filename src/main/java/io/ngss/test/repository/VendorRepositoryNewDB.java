package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorRepositoryNewDB extends JpaRepository<Vendor, Long> {

    String FIND_VENDOR_NAMES= "SELECT vendor_name FROM vendor";

    Vendor findByName(String name);

    @Query(value = FIND_VENDOR_NAMES, nativeQuery = true)
    List<String> findVendorNames();


}

