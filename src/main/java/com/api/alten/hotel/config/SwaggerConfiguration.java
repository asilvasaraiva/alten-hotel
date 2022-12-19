package com.api.alten.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Class responsible for provide the custom information and configurations to swagger
 * @author Alexsandro Saraiva
 */

@EnableSwagger2
@Component
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.alten.hotel"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Endpoints to Hotel Cancun booking API")
                .description(" REST API system using Java 17.5 and Spring Boot as a part of technical assessment in the Alten Group recruitment process.")
                .version("1.0.0")
                .contact(new Contact("Alexsandro da Silva Saraiva","https://www.linkedin.com/in/alexsandrosaraiva/", "alex_saraiva14@hotmail.com"))
                .build();
    }

}
