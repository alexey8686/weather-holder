package spb.weatherholder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class WindDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 4369592251526453497L;

  private BigDecimal speed;

  private BigDecimal deg;


}
