package com.iwellsite.homework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by mshields on 2017-06-16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.iwellsite.domain"})
@EnableJpaRepositories(basePackages = {"com.iwellsite.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
