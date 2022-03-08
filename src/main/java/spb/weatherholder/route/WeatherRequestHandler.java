package spb.weatherholder.route;

import java.util.Comparator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spb.weatherholder.model.Weather;
import spb.weatherholder.service.WeatherService;

@Component
public class WeatherRequestHandler {

  private final WeatherService weatherService;

  @Autowired
  public WeatherRequestHandler(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  public Mono<ServerResponse> getWeather(ServerRequest serverRequest) {
    Optional<String> city = serverRequest.queryParam("city");
    int limit = Integer.parseInt(serverRequest.queryParam("limit").orElse("2"));
    Flux<Weather> weatherFlux = weatherService.findAllWeather();
    if (city.isPresent()){
      weatherFlux= weatherFlux.filter(
          weather -> weather.getCityName().equals(city.get()));
    }
    weatherFlux =weatherFlux.sort(Comparator.comparing(Weather::getDate).reversed()).take(limit);

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(weatherFlux,
            Weather.class);
  }

}
