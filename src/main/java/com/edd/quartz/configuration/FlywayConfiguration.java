package com.edd.quartz.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Use flyway migrations to create the required database entries.
 *
 * @see <a href="https://dzone.com/articles/configuring-quartz">configuring-quartz</a>
 */
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