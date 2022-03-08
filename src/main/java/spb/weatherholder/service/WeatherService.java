package spb.weatherholder.service;

import reactor.core.publisher.Flux;
import spb.weatherholder.dto.ForecastInfoDto;
import spb.weatherholder.model.Weather;

public interface WeatherService {

  void mapAndSaveWeather(ForecastInfoDto forecastInfoDto);

  Flux<Weather> findAllWeather();
}
