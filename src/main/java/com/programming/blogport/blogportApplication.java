package com.programming.blogport;

import com.programming.blogport.config.SwaggerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class blogportApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.programming.blogport.blogportApplication.class, args);
    }

}
//This is main method
// we are using Spring web as we are using Spring MVC
// Lombok for boiler plate code
// also Java Mail sender for user verification through email