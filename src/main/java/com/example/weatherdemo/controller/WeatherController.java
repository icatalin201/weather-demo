package com.example.weatherdemo.controller;

import com.example.weatherdemo.service.WeatherService;
import com.example.weatherdemo.transport.WeatherResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  private final WeatherService weatherService;

  @Autowired
  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping
  public Mono<WeatherResponse> getInfo(@RequestParam("city") List<String> cities) {
    return weatherService.getInfo(cities).map(WeatherResponse::new);
  }
}
