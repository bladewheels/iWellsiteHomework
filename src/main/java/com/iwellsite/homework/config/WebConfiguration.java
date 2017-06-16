package com.iwellsite.homework.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;

/**
 * Created by mshields on 2017-06-16.
 */
@Configuration
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        // See http://host:port/console, use: driver: 'org.h2.Driver', URL: 'jdbc:h2:mem:testdb' and username: 'sa' to login
        return registrationBean;
    }
}
