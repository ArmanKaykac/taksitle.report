package io.ngss.test;

import io.ngss.taksitle.report.dealer.database.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepositoryNewDB extends JpaRepository<Cart, Long> {

    Cart findCartByLoanRequestTransactionToken(Integer token);

    Cart findCartByLoanRequestTransactionId(Long aLong);
}
