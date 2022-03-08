package spb.weatherholder.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties (prefix = "open.weather")
@Component
@Getter
@Setter
public class OpenWeatherMapConfig {

  private String apiKey;

  private String cityId;

  private String units;

}
