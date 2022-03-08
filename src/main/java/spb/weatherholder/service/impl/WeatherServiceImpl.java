package spb.weatherholder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spb.weatherholder.dto.ForecastInfoDto;
import spb.weatherholder.exception.WeatherClientNoData;
import spb.weatherholder.model.Weather;
import spb.weatherholder.repository.WeatherRepository;
import spb.weatherholder.service.WeatherMappingService;
import spb.weatherholder.service.WeatherService;
import spb.weatherholder.utils.JsonUtils;

@Service
public class WeatherServiceImpl implements
    WeatherService {

  private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

  private final WeatherMappingService weatherMappingService;

  private final WeatherRepository weatherRepository;

  @Autowired
  public WeatherServiceImpl(
      WeatherMappingService weatherMappingService,
      WeatherRepository weatherRepository) {
    this.weatherMappingService = weatherMappingService;
    this.weatherRepository = weatherRepository;
  }

  @Override
  public void mapAndSaveWeather(ForecastInfoDto forecastInfoDto) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Full weather forecast is {}", JsonUtils.convertObjectToString(forecastInfoDto));
    }
    try {
      Weather weather= mapWeather(forecastInfoDto);
      Mono<Weather> weatherMono = saveWeather(weather);
      weatherMono.subscribe();
    } catch (WeatherClientNoData e) {
      LOG.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }


  }

  private Weather mapWeather(ForecastInfoDto forecastInfoDto) {
    return weatherMappingService.map(forecastInfoDto);
  }

  private Mono<Weather> saveWeather(Weather weather) {
    return weatherRepository.save(weather);
  }

  @Override
  public Flux<Weather> findAllWeather() {
    return weatherRepository.findAll();
  }
}
