package com.snowcode.lab.dummy.java.template.dao.postgresql;

import com.snowcode.lab.dummy.java.template.dao.postgresql.configuration.DummyPostgresqlDaoAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@EnableAutoConfiguration
@SpringBootTest(classes = {DummyPostgresqlDaoAutoConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractPostgresqlIntegrationTest {

}