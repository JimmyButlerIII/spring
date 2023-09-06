package circulate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CirculateTest {
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
//		OneService oneService = (OneService)applicationContext.getBean("oneService");
//		oneService.test();
//
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
		ThreeService bean = applicationContext.getBean(ThreeService.class);
		bean.test();

//		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("circulate.xml");
//		One bean = applicationContext.getBean(One.class);
//		bean.test();
	}
}
