package com.snowcode.lab.dummy.java.template.service.impl.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@Configuration
public class IdGeneratorConfiguration {

    @Bean
    IdGenerator jdkIdGenerator() {
        return new JdkIdGenerator();
    }

}
