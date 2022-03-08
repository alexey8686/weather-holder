package spb.weatherholder.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class CloudsDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 2685762233998848265L;
  private String all;

}
