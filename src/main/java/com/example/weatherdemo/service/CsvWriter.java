package com.example.weatherdemo.service;

import com.example.weatherdemo.transport.CityWeatherInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CsvWriter {

  private static final Set<String> HEADER_COLUMNS = Set.of("name", "temperature", "wind");
  private final String filename;

  public CsvWriter(@Value("${data.output.name}") String filename) {
    this.filename = filename;
  }

  public void write(List<CityWeatherInfo> infoList) {
    File csvOutputFile = new File(filename);
    try (PrintWriter writer = new PrintWriter(csvOutputFile)) {
      writer.println(String.join(",", HEADER_COLUMNS));
      infoList.stream().map(this::convertToCsvLine).forEach(writer::println);
    } catch (FileNotFoundException e) {
      log.error("File not found", e);
    }
  }

  private String convertToCsvLine(CityWeatherInfo info) {
    return String.join(
        ",", info.getName(), info.getTemperature().toString(), info.getWind().toString());
  }
}
