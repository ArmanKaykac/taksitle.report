package io.ngss.taksitle.report.shared.database.repository;

import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.enums.Channel;
import io.ngss.taksitle.report.shared.database.entity.Configuration;
import io.ngss.taksitle.report.shared.database.enums.ConfigParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    List<Configuration> findAllByConfigName(ConfigParameter configName);

    //Configuration findByLoanCategoryAndChannel(LoanCategory loanCategory, Channel channel);

    Optional<Configuration> findByConfigName(ConfigParameter configParameter);
}
