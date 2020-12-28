import com.hazelcast.core.IMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExample {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
      new ClassPathXmlApplicationContext("spring.xml");

    IMap<String, String> capitals =
      (IMap)applicationContext.getBean("capitalsMap");

    capitals.put("GB", "London");
    System.err.println("Capital of GB: " + capitals.get("GB"));
  }
}
