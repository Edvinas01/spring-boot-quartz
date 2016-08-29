package com.edv.quartz.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    public static final String FLYWAY = "flyway";

    @Autowired
    private DataSource dataSource;

    @Bean(name = FLYWAY)
    public Flyway dataSource() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return flyway;
    }
}