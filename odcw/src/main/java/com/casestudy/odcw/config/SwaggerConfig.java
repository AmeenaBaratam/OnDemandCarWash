package com.casestudy.odcw.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 
                .apis(RequestHandlerSelectors.basePackage("com.casestudy.odcw.controller"))
                .paths(regex("/api/.*"))
                .build()
                .apiInfo(metaData());
    }
	
	private ApiInfo metaData() {
		return new ApiInfo(
                "On-Demand Car Wash API",
                "Spring Boot REST API",
                "1.0",
                " ",
                new Contact("Ameena Baratam", " ", "ameena.baratam@gmail.com"),
                "Apache 2.0", 
                "http://www.apache.org/licenses/LICENSE-2.0");
    }
}
