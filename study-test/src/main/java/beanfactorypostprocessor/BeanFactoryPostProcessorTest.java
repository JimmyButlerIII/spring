package beanfactorypostprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parse.Person;

public class BeanFactoryPostProcessorTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorConfig.class);
		Person person = applicationContext.getBean("person", Person.class);
		System.out.println(person);

	}
}
