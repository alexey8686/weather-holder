package spb.weatherholder.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ForecastInfoDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 6473065911010922617L;

  private List<WeatherDto> weather;

  private CoordinateDto coord;

  private String base;

  private MainInfoDto main;

  private BigDecimal visibility;

  private WindDto wind;

  private CloudsDto cloudsDto;

  private BigDecimal dt;

  private CountryInfoDto sys;

  private int  timezone;

  private long id;

  @JsonAlias(value = "name")
  private String cityName;

  private int cod;

}
