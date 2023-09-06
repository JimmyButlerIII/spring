package circulardependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CircularConfig.class);
//		UserService bean = applicationContext.getBean(UserService.class);
//		System.out.println("bean = " + bean);
//		ZeroService bean = applicationContext.getBean(ZeroService.class);
//		System.out.println("bean = " + bean.getUserService() + " " + bean.getCashService());

//		UserFactoryBean bean = applicationContext.getBean(UserFactoryBean.class);
//		Object bean = applicationContext.getBean("userFactoryBean");
//		System.out.println("bean = " + bean);
		Object bean = applicationContext.getBean("&userFactoryBean");
		System.out.println("bean = " + bean);
	}
}
