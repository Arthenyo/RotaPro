package com.arthenyo.rotapro_backend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.arthenyo.rotapro_backend.repositories.repository_oracle",
        entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "transactionManagerOracle"
)
@ConditionalOnProperty(name = "oracle.datasource.jdbc-url")
public class OracleDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "oracle.datasource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oracleDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.arthenyo.rotapro_backend.model.model_oracle")
                .persistenceUnit("oracle")
                .build();
    }

    @Bean(name = "transactionManagerOracle")
    public PlatformTransactionManager transactionManagerOracle(
            @Qualifier("oracleEntityManagerFactory") LocalContainerEntityManagerFactoryBean oracleEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(oracleEntityManagerFactoryBean.getObject());
    }
}
