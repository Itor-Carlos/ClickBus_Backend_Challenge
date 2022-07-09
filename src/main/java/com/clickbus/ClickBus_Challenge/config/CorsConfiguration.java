package com.clickbus.ClickBus_Challenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){//CORS configuration
        //First client
        corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")//defines
            .allowedMethods("GET", "POST","PUT", "DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT");

        corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")//defines
            .allowedMethods("GET", "POST","PUT", "DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT");
        
        corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:8080")//defines
            .allowedMethods("GET", "POST","PUT", "DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT");
        }

}
