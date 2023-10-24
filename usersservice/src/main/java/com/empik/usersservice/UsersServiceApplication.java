package com.empik.usersservice;

import com.empik.usersservice.http.interceptor.LoggingRequestResponseInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@SpringBootApplication
@EnableCaching
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(loggingRequestResponseInterceptor()));
        return restTemplate;
    }

    @Bean
    public LoggingRequestResponseInterceptor loggingRequestResponseInterceptor(){
        return new LoggingRequestResponseInterceptor();
    }

}
