package resolveBeforeInstantiation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		BeforeInstantiation beforeInstantiation = applicationContext.getBean("beforeInstantiation", BeforeInstantiation.class);
		System.out.println("beforeInstantiation:"+beforeInstantiation);
	}
}
