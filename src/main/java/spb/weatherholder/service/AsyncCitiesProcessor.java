package spb.weatherholder.service;

public interface AsyncCitiesProcessor {

  void asyncWithCompletionService ();

  void asyncWithComputableFuture ();

  void asyncWithStreams();

}
