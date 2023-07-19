package beanpostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parse.Person;

/**
 * BeanPostProcessor 测试
 */
public class BeanPostProcessorTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfigOne.class);
		Person person = applicationContext.getBean("person", Person.class);
		System.out.println(person);
	}
}
