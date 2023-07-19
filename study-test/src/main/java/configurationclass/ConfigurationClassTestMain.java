package configurationclass;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationClassTestMain {
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigOne.class);
//		ConfigOne configOne = applicationContext.getBean("configOne", ConfigOne.class);
//		configOne.person();

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigThree.class);
		ConfigThree configThree = applicationContext.getBean("configThree", ConfigThree.class);
		configThree.person();
	}
}
