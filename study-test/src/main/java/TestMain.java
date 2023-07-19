import configurationclass.ConfigOne;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args){
		// 读取xml配置文件
//		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-${profile:context}.xml");
//		User user = applicationContext.getBean(User.class);
//		System.out.println(user);

//		AbstractApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-parse.xml");
//		Person person= applicationContext.getBean(Person.class);
//		System.out.println(person);
//		applicationContext.registerShutdownHook();


		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigOne.class);
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigTwo.class);
//		Person person = applicationContext.getBean(Person.class);
//		System.out.println(person);
		ConfigOne configOne = applicationContext.getBean(ConfigOne.class);
		System.out.println(configOne.person());
		System.out.println(configOne.person());
		System.out.println(configOne.person());
		System.out.println(configOne);


		// 测试bean的循环依赖
//		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("application-service.xml");
//		UserService userService = applicationContext.getBean("service", UserService.class);
//		System.out.println(userService);
	}
}
