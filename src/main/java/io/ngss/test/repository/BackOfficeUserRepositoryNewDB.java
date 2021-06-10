package io.ngss.test.repository;

import io.ngss.taksitle.report.dealer.database.entity.backoffice.BackOfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackOfficeUserRepositoryNewDB extends JpaRepository<BackOfficeUser, Long> {

        Optional<BackOfficeUser> findByName(String name);
        }
