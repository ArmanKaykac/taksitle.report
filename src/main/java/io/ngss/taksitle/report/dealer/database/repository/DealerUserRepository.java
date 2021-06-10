package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.dealer.Dealer;
import io.ngss.taksitle.report.dealer.database.entity.dealer.DealerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealerUserRepository extends JpaRepository<DealerUser, Long> {

    Optional<List<DealerUser>> findByEmail(String email);
    Optional<DealerUser> findByGsmno(String gsmno);
    Optional<DealerUser> findByDealerUserDetailsTckno(Long tckno);
    Optional<DealerUser> findByName(String name);
    Optional<DealerUser> findByGsmnoAndDealerUserDetailsTckno(String gsmNo,Long tckno);

    Optional<DealerUser> findByGsmnoAndDealerIdAndIsEnabled(String gsmNo, Long dealerId, boolean isEnabled);

    Optional<DealerUser> findByGsmnoAndDealerUserDetailsTcknoAndIsEnabled(String gsmNo, Long tckno, boolean isEnabled);

    Optional<DealerUser> findByGsmnoAndDealerIdAndIsEnabledAndDealerUserDetailsTcknoNot(String gsmNo, Long dealerId, boolean isEnabled, Long tckno);

    Optional<DealerUser> findByGsmnoAndDealerIdAndIsEnabledAndDealerPhoneNumberAndDealerUserDetailsTcknoNot(String gsmNo, Long dealerId, boolean isEnabled, Long phoneNumber ,Long tckno);

    List<DealerUser> findAllByIdIsNotAndDealer(Long activeDealerUserId, Dealer dealer);

    List<DealerUser> findAllByDealerIdAndUserTypeIsNotNull(Long dealerId);

    Optional<DealerUser> findByDealerIdAndId(Long dealerId, Long dealerUserId);

    Integer countByDealerUserDetailsTcknoOrGsmno(Long tckno, String gsmno);

    List<DealerUser> findAllByDealerId(Long aLong);
}
