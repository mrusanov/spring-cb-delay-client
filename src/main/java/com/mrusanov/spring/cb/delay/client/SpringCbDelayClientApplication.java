package com.mrusanov.spring.cb.delay.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class SpringCbDelayClientApplication {

    @Value("${client.server.readTimeoutInMilliseconds}")
    private Integer readTimeoutInMilliseconds;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(readTimeoutInMilliseconds)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCbDelayClientApplication.class, args);
    }

}