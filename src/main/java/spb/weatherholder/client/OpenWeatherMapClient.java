package spb.weatherholder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spb.weatherholder.dto.ForecastInfoDto;

@FeignClient(name = "openweathermap", url = "http://api.openweathermap.org/data/2.5/weather")
public interface OpenWeatherMapClient {


  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ForecastInfoDto getWeatherInfo(@RequestParam(name = "id") String cityId, @RequestParam String units,
      @RequestParam String appid);

}
