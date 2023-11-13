package com.example.weatherdemo.routing;

import com.example.weatherdemo.routing.transport.WeatherApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "api-service", url = "${routing.weather-api.url}")
public interface ApiService {
  @GetMapping("/{city}")
  Mono<WeatherApiResponse> getWeatherInfo(@PathVariable(name = "city") String city);
}
