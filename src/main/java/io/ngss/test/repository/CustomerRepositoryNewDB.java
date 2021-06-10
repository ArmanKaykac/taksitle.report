package io.ngss.test.repository;

import io.ngss.taksitle.report.customermanagement.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepositoryNewDB extends JpaRepository<Customer, Long> {

    String getCustomerByNameSurname  = "select c.* from customer c join customer_details cd on c.customer_details_id = cd.id where UPPER(TRANSLATE(cd.name,'ıi','Iİ')) = UPPER(TRANSLATE(:name,'ıi','Iİ')) ";

    Customer findByGsmno(Long gsmno);
    Customer findByGsmnoAndTckno(Long gsmNo, Long tckNo);
    Customer findByTckno(Long tckno);
    Customer findByCustomerTransactionsToken(Integer token);
    Customer findByCustomerDetailsNameContainingIgnoreCase(String name);

    @Query(value = getCustomerByNameSurname, nativeQuery = true)
    Customer getCustomerByContainsNameSurname (@Param("name") String name);
}
