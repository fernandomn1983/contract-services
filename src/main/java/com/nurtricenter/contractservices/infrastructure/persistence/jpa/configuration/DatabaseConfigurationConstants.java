package com.nurtricenter.contractservices.infrastructure.persistence.jpa.configuration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseConfigurationConstants {

    public static final String DATA_SOURCE = "contractServiceDataSource";
    public static final String DATA_SOURCE_PROPERTIES_PREFIX = "contract-service.datasource";
    public static final String ENTITY_MANAGER_FACTORY = "contractServiceEntityManagerFactory";
    public static final String PACKAGES_TO_SCAN = "com.nurtricenter.contractservices.infrastructure.persistence.jpa.entity";
    public static final String BASE_PACKAGES = "com.nurtricenter.contractservices.infrastructure.persistence.jpa.repository";
    public static final String DATASOURCE_DIALECT_PROPERTY = "${contract-service.datasource.dialect}";
    public static final String DATASOURCE_HIBERNATE_JDBC_BATCH_SIZE_PROPERTY = "${contract-service.datasource.hibernate.jdbc.batch_size}";
    public static final String DATASOURCE_DDL_AUTO_PROPERTY = "${contract-service.datasource.ddl.auto:none}";
    public static final String DDL_AUTO_NONE = "none";
    public static final String HIBERNATE_DIALECT_PROPERTY_KEY = "hibernate.dialect";
    public static final String HIBERNATE_HBM2_DDL_AUTO_PROPERTY_KEY = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_JDBC_BATCH_SIZE_PROPERTY_KEY = "hibernate.jdbc.batch_size";
    public static final String HIBERNATE_ORDER_INSERTS_PROPERTY_KEY = "hibernate.order_inserts";
    public static final String HIBERNATE_ORDER_UPDATES_PROPERTY_KEY = "hibernate.order_updates";
    public static final String HIBERNATE_ORDER_DELETES_PROPERTY_KEY = "hibernate.order_deletes";
    public static final String TRANSACTION_MANAGER = "contractServiceTransactionManager";
    
}
