package spb.weatherholder.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Weather {

  @Id
  private String id;

  private LocalDateTime date;

  private String main;

  private String description;

  private BigDecimal temp;

  private BigDecimal feels_like;

  private BigDecimal temp_min;

  private BigDecimal temp_max;

  private BigDecimal pressure;

  private BigDecimal speed;

  private String cityName;
}
