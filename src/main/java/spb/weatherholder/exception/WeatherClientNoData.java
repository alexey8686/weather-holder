package spb.weatherholder.exception;

import java.io.Serial;

public class WeatherClientNoData extends RuntimeException{

  @Serial
  private static final long serialVersionUID = 4016624516072463191L;

  public WeatherClientNoData (){
    super();
  }

  public WeatherClientNoData (String message){
    super(message);
  }

  public WeatherClientNoData(String message, Throwable e){
    super(message, e);
  }

}
