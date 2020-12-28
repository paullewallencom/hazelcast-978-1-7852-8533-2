package tasks;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;

public class TimeCallable implements Callable<String>, Serializable {

  @Override
  public String call() throws Exception {
    return new Date().toString();
  }
}
