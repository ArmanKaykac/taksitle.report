package io.ngss.taksitle.report.dealer.database.repository;

import io.ngss.taksitle.report.dealer.database.entity.backoffice.BackOfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackOfficeUserRepository extends JpaRepository<BackOfficeUser, Long> {

    Optional<BackOfficeUser> findByName(String name);
}
