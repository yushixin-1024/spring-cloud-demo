import org.springframework.boot.SpringBootVersion;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.SpringVersion;

public class Main {

    public static void main(String[] args) {
        System.out.println("SpringVersion: "+SpringVersion.getVersion()+", SpringBootVersion: "+ SpringBootVersion.getVersion());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
        System.out.println(a);
        System.out.println(b);
    }
}
