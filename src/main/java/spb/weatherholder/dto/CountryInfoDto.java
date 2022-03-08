package spb.weatherholder.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CountryInfoDto implements Serializable {

  @Serial
  private static final long serialVersionUID = -4130326470129210717L;
  private long type;

  private long id;

  private BigDecimal message;

  private String country;

  private long sunrise;

  private long sunset;


}
