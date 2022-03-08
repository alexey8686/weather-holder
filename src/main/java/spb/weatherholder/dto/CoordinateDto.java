package spb.weatherholder.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CoordinateDto implements Serializable {

  @Serial
  private static final long serialVersionUID = -6914648522595611421L;
  private BigDecimal lon;
  private BigDecimal lat;

}
