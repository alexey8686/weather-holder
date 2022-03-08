package spb.weatherholder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class WebConfig {

  @Bean
  public MappingJackson2HttpMessageConverter messageConverter() {
    return new MappingJackson2HttpMessageConverter();
  }

  @Bean
  public HttpMessageConverters httpMessageConverters(
      @Autowired MappingJackson2HttpMessageConverter messageConverter) {
    return new HttpMessageConverters(messageConverter);
  }
}
