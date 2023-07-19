package abstractbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * abstract bean
 */
public class AbstractBeanTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("abstract-bean.xml");
		ChildrenBean childrenBean = applicationContext.getBean("childrenBean", ChildrenBean.class);
		System.out.println(childrenBean);

	}
}
