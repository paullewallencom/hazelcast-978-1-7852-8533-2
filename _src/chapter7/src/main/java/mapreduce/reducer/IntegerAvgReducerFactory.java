package mapreduce.reducer;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class IntegerAvgReducerFactory<T> implements ReducerFactory<T, Integer, Integer> {

  @Override
  public Reducer<Integer, Integer> newReducer(T key) {
    return new IntegerAvgReducer();
  }

  private static class IntegerAvgReducer extends Reducer<Integer, Integer> {
    int total = 0;
    int number = 0;

    @Override
    public void reduce(Integer value) {
      total += value;
      number++;
    }

    @Override
    public Integer finalizeReduce() {
      return total / number;
    }
  }
}
