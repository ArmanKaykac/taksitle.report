package io.ngss.taksitle.report.shared.database.entity;

import io.ngss.taksitle.report.backoffice.model.LoanCategoryAndChannelBasedOfferTimeoutModel;
import io.ngss.taksitle.report.bank.LoanCategory;
import io.ngss.taksitle.report.dealer.database.enums.Channel;
import io.ngss.taksitle.report.shared.database.enums.ConfigParameter;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {

    @Enumerated(EnumType.STRING)
    public LoanCategory loanCategory;
    @Enumerated(EnumType.STRING)
    public Channel channel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private ConfigParameter configName;
    private String configValue;

    public static Configuration createLoanCategoryAndChannelBasedLoanOfferTimeout(LoanCategoryAndChannelBasedOfferTimeoutModel model) {
        Configuration configuration = new Configuration();

        configuration.configName = ConfigParameter.LOAN_CATEGORY_AND_CHANNEL_BASED_LOAN_OFFER_TIMEOUT;
        configuration.loanCategory = model.loanCategory;
        configuration.channel = model.channel;
        configuration.configValue = String.valueOf(model.timeout);

        return configuration;
    }

    public ConfigParameter getConfigName() {
        return configName;
    }

    public void setConfigName(ConfigParameter configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Long getId() {
        return id;
    }

}