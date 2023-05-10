package com.example.Anywr.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@PropertySources({
        @PropertySource("file:config.properties"),
        @PropertySource("file:application.properties")
})
public class SwaggerConfig {
    @Value("${anywr.currentHost}")
    private String currentHost;

    @Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.Anywr.api.controllers"))
                .build()
                .host(currentHost);
    }


}
