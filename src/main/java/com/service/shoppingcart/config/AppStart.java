package com.service.shoppingcart.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Spring Boot implementation class - This class defines the Spring Boot
 * configuration
 *
 * @author je.calderon
 */
@SpringBootApplication
@ComponentScan({"com.service.shoppingcart"})
@EnableJpaRepositories("com.service.shoppingcart.model")
@EntityScan("com.service.shoppingcart.model")
public class AppStart extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppStart.class).run(args);
    }
}
