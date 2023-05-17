import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args){
		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-${profile:context}.xml");
		User user = applicationContext.getBean(User.class);
		System.out.println(user);

//		AbstractApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-parse.xml");
//		Person person= applicationContext.getBean(Person.class);
//		System.out.println(person);
//		applicationContext.registerShutdownHook();

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(beanfactorypostprocessor.MyConfig.class);
//		Person person = applicationContext.getBean(Person.class);
//		System.out.println(person);
	}
}
