package com.qa.student.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Config {
	@Bean

    public ModelMapper mapper() {

        return new ModelMapper();

    }





        @Bean

        public Docket api() {

            return new Docket(DocumentationType.SWAGGER_2).select()

                    .apis(RequestHandlerSelectors.basePackage("com.qa.Stucontroller")).paths(PathSelectors.any())

                    .build();

        }


}
    
