package com.iwellsite.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mshields on 2017-06-16.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();

        // TODO: Remove these for PRODUCTION environments, they support use of the H2 console
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}




