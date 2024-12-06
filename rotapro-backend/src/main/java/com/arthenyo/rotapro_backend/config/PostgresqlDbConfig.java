package com.arthenyo.rotapro_backend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.arthenyo.rotapro_backend.repositories.repository_postgresql",
        entityManagerFactoryRef = "postgresqlEntityManager"
)
@ConditionalOnProperty(name = "postgresql.datasource.jdbc-url")
public class PostgresqlDbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "postgresql.datasource")
    public DataSource postgresqlDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.arthenyo.rotapro_backend.model.model_postgresql")
                .build();
    }
    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("postgresqlEntityManager") LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactoryBean) {
        return new JpaTransactionManager(postgresqlEntityManagerFactoryBean.getObject());
    }


}
