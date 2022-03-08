package spb.weatherholder.service.impl;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import spb.weatherholder.service.AsyncCitiesProcessor;
import spb.weatherholder.service.ScheduledWeatherService;

@Service
public class ScheduledWeatherServiceImpl implements
    ScheduledWeatherService {

  private static final Logger LOG = LoggerFactory.getLogger(ScheduledWeatherServiceImpl.class);

  private final AsyncCitiesProcessor asyncCitiesProcessor;

  @Autowired
  public ScheduledWeatherServiceImpl(
      AsyncCitiesProcessor asyncCitiesProcessor) {
    this.asyncCitiesProcessor = asyncCitiesProcessor;
  }


  @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
  @Override
  public void requestWeatherAndSave() {
    LOG.info("Start request and save weather....");
    try {
     asyncCitiesProcessor.asyncWithComputableFuture();
    } catch (Exception e) {
      LOG.error("Some thing going wrong - {}", e.getMessage(), e);
    }

  }

}
