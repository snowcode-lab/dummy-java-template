package com.snowcode.lab.dummy.java.template.dao.postgresql.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@AutoConfiguration
@ComponentScan("com.snowcode.lab.dummy.java.template.dao.postgresql")
public class DummyPostgresqlDaoAutoConfiguration {

    @Configuration
    @PropertySource(value = "classpath:postgresql-dao.properties")
    @EntityScan("com.snowcode.lab.dummy.java.template.dao.postgresql.model")
    @EnableJpaRepositories("com.snowcode.lab.dummy.java.template.dao.postgresql.repository")
    public static class DummyPostgresqlDaoConfiguration {
    }

}
