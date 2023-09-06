package factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
	public static void main(String[] args) {
		// 下面试FactoryBean的使用
		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("factory-bean.xml");
		FactoryUser FactoryUser = applicationContext.getBean(FactoryUser.class);
		System.out.println(FactoryUser);

//		FactoryUser FactoryUser = (FactoryUser) applicationContext.getBean("myFactoryBean");
//		System.out.println(FactoryUser);
//		FactoryUser FactoryUser = applicationContext.getBean("myFactoryBean", FactoryUser.class);
//		System.out.println(FactoryUser);
//		MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&&myFactoryBean");
//		System.out.println(myFactoryBean);
	}
}
