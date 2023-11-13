package com.example.weatherdemo.routing.transport;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {

  private Double temperature;
  private Double wind;
  private String description;
  private List<WeatherForecastResponse> forecast;

  public Double getAverageTemperature() {
    return forecast.stream()
        .mapToDouble(WeatherForecastResponse::getTemperature)
        .average()
        .orElse(0);
  }

  public Double getAverageWind() {
    return forecast.stream().mapToDouble(WeatherForecastResponse::getWind).average().orElse(0);
  }

  @Getter
  @Setter
  public static class WeatherForecastResponse {
    private Integer day;
    private Double temperature;
    private Double wind;
  }
}
