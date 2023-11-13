package com.example.weatherdemo.service;

import com.example.weatherdemo.routing.ApiService;
import com.example.weatherdemo.routing.transport.WeatherApiResponse;
import com.example.weatherdemo.transport.CityWeatherInfo;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WeatherService {

  private static final Set<String> ACCEPTED_CITIES =
      Set.of("Cluj-Napoca", "Bucuresti", "Timisoara", "Constanta", "Baia-Mare", "Arad");

  private final ApiService apiService;
  private final CsvWriter csvWriter;

  @Autowired
  public WeatherService(ApiService apiService, CsvWriter csvWriter) {
    this.apiService = apiService;
    this.csvWriter = csvWriter;
  }

  public Mono<List<CityWeatherInfo>> getInfo(List<String> cities) {
    return Flux.fromIterable(cities)
        .sort()
        .filter(ACCEPTED_CITIES::contains)
        .flatMap(this::getWeatherInfo)
        .onErrorResume(e -> Mono.empty())
        .collectList()
        .doOnNext(csvWriter::write);
  }

  private Mono<CityWeatherInfo> getWeatherInfo(String city) {
    return apiService
        .getWeatherInfo(city)
        .map(response -> mapToCityWeatherInfo(response, city))
        .doOnError(error -> log.error("Error fetching city data: {}", city, error));
  }

  private CityWeatherInfo mapToCityWeatherInfo(WeatherApiResponse response, String city) {
    CityWeatherInfo cityWeatherInfo = new CityWeatherInfo();
    cityWeatherInfo.setName(city);
    cityWeatherInfo.setWind(response.getAverageWind());
    cityWeatherInfo.setTemperature(response.getAverageTemperature());
    return cityWeatherInfo;
  }
}
