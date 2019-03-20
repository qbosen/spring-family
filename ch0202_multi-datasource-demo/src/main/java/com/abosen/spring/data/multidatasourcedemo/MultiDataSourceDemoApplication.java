package com.abosen.spring.data.multidatasourcedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
public class MultiDataSourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourceDemoApplication.class, args);
    }

    /***************************************************************************************************************
     * foo
     ***************************************************************************************************************/
    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource(DataSourceProperties fooDataSourceProperties) {
        log.info("foo datasource: {}", fooDataSourceProperties.getUrl());
        return fooDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSourceInitializer fooSchemaInitializer(DataSource fooDataSource, @Value("${foo.datasource.schema}") String schema) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(fooDataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource(schema));
        dataSourceInitializer.setDatabasePopulator(populator);
        dataSourceInitializer.setEnabled(true);
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        return new DataSourceTransactionManager(fooDataSource);
    }

    @Bean
    public JdbcTemplate fooJdbcTemplate(DataSource fooDataSource) {
        return new JdbcTemplate(fooDataSource);
    }


    /***************************************************************************************************************
     *  bar
     ***************************************************************************************************************/
    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDataSource() {
        DataSourceProperties dataSourceProperties = barDataSourceProperties();
        log.info("bar datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public PlatformTransactionManager barTxManager(DataSource barDataSource) {
        return new DataSourceTransactionManager(barDataSource);
    }

    @Bean
    public JdbcTemplate barJdbcTemplate(DataSource barDataSource) {
        return new JdbcTemplate(barDataSource);
    }

    @Bean
    public DataSourceInitializer barSchemaInitializer(DataSource barDataSource, @Value("${bar.datasource.schema}") String schema) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(barDataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource(schema));
        dataSourceInitializer.setDatabasePopulator(populator);
        dataSourceInitializer.setEnabled(true);
        return dataSourceInitializer;
    }
}

