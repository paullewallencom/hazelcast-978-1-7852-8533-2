package mapreduce.reducer;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class IntegerSumReducerFactory<T> implements ReducerFactory<T, Integer, Integer> {

  @Override
  public Reducer<Integer, Integer> newReducer(T key) {
    return new IntegerSumReducer();
  }

  private static class IntegerSumReducer extends Reducer<Integer, Integer> {
    int total = 0;

    @Override
    public void reduce(Integer value) {
      total += value;
    }

    @Override
    public Integer finalizeReduce() {
      return total;
    }
  }
}
