package com.george.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);
    private String driver;
    private String url;
    private String userId;
    private String password;

    public DataSourceConfiguration (
        @Value("${driver}") String driver,
        @Value("${url}") String url,
        @Value("${userId}") String userId,
        @Value("${password}") String password
    ) {
        this.driver = driver;
        this.url = url;
        this.userId = userId;
        this.password = password;
    }

    @Bean
    public DataSource datasource() {
        DataSource ds =  DataSourceBuilder.create()
                .driverClassName(this.driver)
                .url(this.url)
                .username(this.userId)
                .password(this.password)
                .build();
        logger.info("data source created =====> {}@{}", this.userId, this.url);
        return ds;
    }

}
