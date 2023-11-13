package com.example.weatherdemo.transport;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WeatherResponse {
  private final List<CityWeatherInfo> result;
}
