package mapreduce.combiner;

import com.hazelcast.mapreduce.Combiner;
import com.hazelcast.mapreduce.CombinerFactory;

public class IntegerSumCombinerFactory<T> implements CombinerFactory<T, Integer, Integer> {

  @Override
  public Combiner<Integer, Integer> newCombiner(T key) {
    return new IntegerSumCombiner();
  }

  private static class IntegerSumCombiner extends Combiner<Integer, Integer> {
    int total = 0;

    @Override
    public void combine(Integer value) {
      total += value;
    }

    @Override
    public Integer finalizeChunk() {
      return total;
    }

    @Override
    public void reset() {
      total = 0;
    }
  }
}
