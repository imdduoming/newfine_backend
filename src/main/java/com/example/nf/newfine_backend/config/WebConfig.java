package com.example.nf.newfine_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://newfinebackenddocker.ap-northeast-2.elasticbeanstalk.com/")
                .allowedMethods("POST", "PUT", "GET", "DELETE")
                .exposedHeaders(HttpHeaders.LOCATION);
    }
}