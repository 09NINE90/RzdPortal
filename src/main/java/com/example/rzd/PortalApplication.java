package com.example.rzd;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class PortalApplication {
//    @Bean
//    MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.parse("20000KB"));
//        factory.setMaxRequestSize(DataSize.parse("20000KB"));
//        return factory.createMultipartConfig();
//    }

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);

    }

}
