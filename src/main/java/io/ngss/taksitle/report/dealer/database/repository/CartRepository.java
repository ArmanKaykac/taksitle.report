package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

        Cart findCartByLoanRequestTransactionToken(Integer token);

        Cart findCartByLoanRequestTransactionId(Long aLong);
        Cart findByLoanRequestId(Long aLong);
}
