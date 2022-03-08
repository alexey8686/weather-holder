package spb.weatherholder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableFeignClients
@EnableScheduling
public class WeatherHolderApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherHolderApplication.class, args);


  }
}
