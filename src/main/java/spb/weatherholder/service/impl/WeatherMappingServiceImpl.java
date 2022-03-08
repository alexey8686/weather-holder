package spb.weatherholder.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import spb.weatherholder.dto.ForecastInfoDto;
import spb.weatherholder.exception.WeatherClientNoData;
import spb.weatherholder.model.Weather;
import spb.weatherholder.service.WeatherMappingService;

@Component
public class WeatherMappingServiceImpl implements
    WeatherMappingService {

  @Override
  public Weather map(ForecastInfoDto forecastInfoDto) {
    if (forecastInfoDto == null) {
      throw new WeatherClientNoData("No data was returned from weather service");
    }

    Weather weather = new Weather();
    weather.setDate(LocalDateTime.now());
    weather.setMain(getMainIfExist(forecastInfoDto));
    weather.setDescription(getDescriptionIfExist(forecastInfoDto));
    if (forecastInfoDto.getMain() != null) {
      weather.setTemp(forecastInfoDto.getMain().getTemp());
      weather.setFeels_like(forecastInfoDto.getMain().getFeels_like());
      weather.setTemp_min(forecastInfoDto.getMain().getTemp_min());
      weather.setTemp_max(forecastInfoDto.getMain().getTemp_max());
      weather.setPressure(forecastInfoDto.getMain().getPressure());
    }
    weather.setSpeed(
        forecastInfoDto.getWind() != null ? forecastInfoDto.getWind().getSpeed() : null);
    weather.setCityName(forecastInfoDto.getCityName());

    return weather;
  }

  private String getDescriptionIfExist(ForecastInfoDto forecastInfoDto) {
    if (forecastInfoDto.getWeather() != null && !forecastInfoDto.getWeather().isEmpty()) {
      return forecastInfoDto.getWeather().get(0).getDescription();
    }

    return null;
  }


  private String getMainIfExist(ForecastInfoDto forecastInfoDto) {
    if (forecastInfoDto.getWeather() != null && !forecastInfoDto.getWeather().isEmpty()) {
      return forecastInfoDto.getWeather().get(0).getMain();
    }
    return null;
  }
}
