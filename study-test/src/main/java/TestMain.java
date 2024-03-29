import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args){
		// 读取xml配置文件
		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-${profile:context}.xml");
		User user = applicationContext.getBean(User.class);
		System.out.println(user);

//		AbstractApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-parse.xml");
//		Person person= applicationContext.getBean(Person.class);
//		System.out.println(person);
//		applicationContext.registerShutdownHook();


//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigOne.class);
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigTwo.class);
//		Person person = applicationContext.getBean(Person.class);
//		System.out.println(person);


		// 测试bean的循环依赖
//		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("application-service.xml");
//		UserService userService = applicationContext.getBean("service", UserService.class);
//		System.out.println(userService);
	}
}
