package com.bala.codechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.bala.assignment.gituser.SpringBootUserController;
import com.bala.codechallenge.helloworld.HelloWorldController;
import com.bala.codechallenge.quotes.SpringBootQuotesController;



@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@ComponentScan(basePackageClasses = SpringBootUserController.class)
@ComponentScan(basePackageClasses=HelloWorldController.class)
@ComponentScan(basePackageClasses=SpringBootQuotesController.class)
public class MicroServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(MicroServiceMain.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
