package com.empik.usersservice;

import com.empik.usersservice.http.interceptor.LoggingRequestResponseInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(LoggingRequestResponseInterceptor loggingRequestResponseInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(loggingRequestResponseInterceptor));
        return restTemplate;
    }

}
