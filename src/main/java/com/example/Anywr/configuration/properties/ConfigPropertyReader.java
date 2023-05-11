package com.example.Anywr.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;

@Data
@Configuration
@EnableAsync
@PropertySource("file:config.properties")
public class ConfigPropertyReader {
    @Value("${anywr.datasource.username}")
    private String username;
    @Value("${anywr.datasource.password}")
    private String password;
    @Value("${anywr.datasource.databaseName}")
    private String databaseName;
    @Value("${anywr.datasource.url}")
    private String db_url;
    @Value("${anywr.datasource.driver-class-name}")
    private String driver_class_name;
    @Value("${anywr.datasource.extra-params}")
    private String driver_params;

    //jwt config
    @Value("${anywr.jwt.secretKey}")
    private String secretKey;
    @Value("${anywr.jwt.expiration}")
    private Duration tokenExpiration;
    @Value("${anywr.jwt.tokenPrefix}")
    private String tokenPrefix;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
