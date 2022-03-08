package spb.weatherholder.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class WeatherDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1877553631531876750L;

  private int id;

  private String main;

  private String description;

  private String icon;

}
