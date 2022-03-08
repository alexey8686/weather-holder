package spb.weatherholder.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spb.weatherholder.client.OpenWeatherMapClient;
import spb.weatherholder.config.OpenWeatherMapConfig;
import spb.weatherholder.dto.ForecastInfoDto;
import spb.weatherholder.service.AsyncCitiesProcessor;
import spb.weatherholder.service.WeatherService;

@Component
public class AsyncCitiesProcessorImpl implements AsyncCitiesProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(AsyncCitiesProcessorImpl.class);

  private static final ExecutorService executorService = Executors.newFixedThreadPool(
      Runtime.getRuntime().availableProcessors());

  final String[] citiesId;

  private final WeatherService weatherService;

  private final OpenWeatherMapClient openWeatherMapClient;

  private final OpenWeatherMapConfig mapConfig;

  @Autowired
  public AsyncCitiesProcessorImpl(WeatherService weatherService,
      OpenWeatherMapClient openWeatherMapClient,
      OpenWeatherMapConfig mapConfig) {
    this.weatherService = weatherService;
    this.openWeatherMapClient = openWeatherMapClient;
    this.mapConfig = mapConfig;
    citiesId = mapConfig.getCityId() != null && !mapConfig.getCityId().isEmpty()
        ? mapConfig.getCityId().split(",") : new String[]{"536203"};
  }

  @Override
  public void asyncWithCompletionService() {
    ExecutorCompletionService<ForecastInfoDto> completionService = new ExecutorCompletionService<>(
        executorService);
    List<Future<ForecastInfoDto>> futureList = new ArrayList<>();
    for (String cityId : citiesId) {
      futureList.add(completionService.submit(() -> requestWeatherFromWeatherService(cityId)));
    }

    for (Future<ForecastInfoDto> forecastInfoDtoFuture : futureList) {
      try {
        final ForecastInfoDto forecastInfoDto = completionService.take().get();
        saveRequestedWeather(forecastInfoDto);
      } catch (InterruptedException | ExecutionException e) {
        LOG.error(e.getMessage(), e);
      }
    }
  }

  @Override
  public void asyncWithComputableFuture() {

    for (String cityId : citiesId) {
      CompletableFuture.supplyAsync(() -> requestWeatherFromWeatherService(cityId))
          .thenAcceptAsync(this::saveRequestedWeather, executorService);
    }
  }

  @Override
  public void asyncWithStreams() {
    Arrays.stream(citiesId).parallel().map(this::requestWeatherFromWeatherService)
        .forEach(this::saveRequestedWeather);
  }

  private void saveRequestedWeather(ForecastInfoDto requestedWeather) {
    weatherService.mapAndSaveWeather(requestedWeather);
  }

  private ForecastInfoDto requestWeatherFromWeatherService(String cityId) {
    return openWeatherMapClient.getWeatherInfo(
        cityId,
        mapConfig.getUnits(),
        mapConfig.getApiKey());
  }

}
