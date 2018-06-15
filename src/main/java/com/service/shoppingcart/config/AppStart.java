package com.service.shoppingcart.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * Spring Boot implementation class - This class defines the Spring Boot
 * configuration
 *
 * @author je.calderon
 */
@SpringBootApplication
@ComponentScan({"com.service.shoppingcart"})
public class AppStart extends SpringBootServletInitializer {
    public static void main(String[] args) {
    	//System.setProperty("server.servlet.context-path", "/shoppingcar");
        new SpringApplicationBuilder(AppStart.class).run(args);
    }
}
