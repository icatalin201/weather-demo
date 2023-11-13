package com.example.weatherdemo.transport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityWeatherInfo {
  private String name;
  private Double temperature;
  private Double wind;
}
