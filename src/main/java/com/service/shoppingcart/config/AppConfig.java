package com.service.shoppingcart.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class AppConfig extends WebMvcConfigurationSupport {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    super.addResourceHandlers(registry);
    registry
        .addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
