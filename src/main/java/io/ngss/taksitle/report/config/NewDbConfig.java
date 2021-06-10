package io.ngss.taksitle.report.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2",
        basePackages = { "io.ngss.test" }
)
public class NewDbConfig {


    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource2") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("io.ngss.taksitle.report")
                //.persistenceUnit("foo")
                .build();
    }


    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory2") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}