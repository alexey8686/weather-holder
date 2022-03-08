package spb.weatherholder.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class MainInfoDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 8960575001333327347L;
  private BigDecimal temp;

  private BigDecimal feels_like;

  private BigDecimal temp_min;

  private BigDecimal temp_max;

  private BigDecimal pressure;

  private BigDecimal humidity;

}
