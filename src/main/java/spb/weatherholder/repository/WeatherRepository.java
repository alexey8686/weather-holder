package spb.weatherholder.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spb.weatherholder.model.Weather;

public interface WeatherRepository extends ReactiveMongoRepository<Weather, String> {

}
