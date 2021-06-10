package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.customermanagement.Customer;
import io.ngss.taksitle.report.dealer.database.Address;
import io.ngss.taksitle.report.dealer.database.enums.AddressTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomerAndAddressTypes(Customer customer, AddressTypes addressType);
    List<Address> findByDeliveryInfoId (Long id);
}
