package com.nurtricenter.contractservices.infrastructure.persistence.jpa.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

import static com.nurtricenter.contractservices.infrastructure.persistence.jpa.configuration.DatabaseConfigurationConstants.*;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {BASE_PACKAGES},
        entityManagerFactoryRef = ENTITY_MANAGER_FACTORY,
        transactionManagerRef = TRANSACTION_MANAGER
)
public class DatabaseConfiguration {

    @Value(DATASOURCE_DIALECT_PROPERTY)
    private String dialect;

    @Value(DATASOURCE_HIBERNATE_JDBC_BATCH_SIZE_PROPERTY)
    private int hibernateJdbcBatchSize;

    @Value(DATASOURCE_DDL_AUTO_PROPERTY)
    private String ddlAuto;

    @Bean(name = DATA_SOURCE)
    @ConfigurationProperties(prefix = DATA_SOURCE_PROPERTIES_PREFIX)
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(!ddlAuto.equals(DDL_AUTO_NONE));
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(HIBERNATE_DIALECT_PROPERTY_KEY, dialect);
        properties.put(HIBERNATE_HBM2_DDL_AUTO_PROPERTY_KEY, ddlAuto);
        properties.put(HIBERNATE_JDBC_BATCH_SIZE_PROPERTY_KEY, hibernateJdbcBatchSize);
        properties.put(HIBERNATE_ORDER_INSERTS_PROPERTY_KEY, true);
        properties.put(HIBERNATE_ORDER_UPDATES_PROPERTY_KEY, true);
        properties.put(HIBERNATE_ORDER_DELETES_PROPERTY_KEY, true);
        entityManagerFactoryBean.setJpaPropertyMap(properties);

        return entityManagerFactoryBean;
    }

    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(
            @Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
