import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

public class JCacheExample {
  public static void main(String[] args) {
    CachingProvider cachingProvider = Caching.getCachingProvider();

    CacheManager cacheManager = cachingProvider.getCacheManager();

    Cache<String, String> capitals = cacheManager.getCache("capitals", String.class, String.class);

    capitals.put("GB", "London");
    System.err.println("Capital of GB: " + capitals.get("GB"));
  }
}
