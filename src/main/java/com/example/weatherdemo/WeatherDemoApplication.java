package com.example.weatherdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients
@SpringBootApplication
public class WeatherDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherDemoApplication.class, args);
  }
}
