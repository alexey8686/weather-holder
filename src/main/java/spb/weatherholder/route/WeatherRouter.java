package spb.weatherholder.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WeatherRouter {

  @Bean
  public RouterFunction<ServerResponse> weatherSortedList(WeatherRequestHandler weatherRequestHandler) {
    return RouterFunctions.route(RequestPredicates.GET("/weather/sorted/list"),
        weatherRequestHandler::getWeather);
  }

}
