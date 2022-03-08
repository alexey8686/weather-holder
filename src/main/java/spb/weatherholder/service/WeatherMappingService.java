package spb.weatherholder.service;

import spb.weatherholder.dto.ForecastInfoDto;
import spb.weatherholder.model.Weather;

public interface WeatherMappingService {


  Weather map(ForecastInfoDto forecastInfoDto);

}
