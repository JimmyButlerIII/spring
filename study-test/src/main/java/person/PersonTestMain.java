package person;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTestMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("person.xml");
		Person person = applicationContext.getBean("person", Person.class);
//		Person personTwo = ac.getBean("person", Person.class);
		System.out.println(person);
//		System.out.println(personTwo);
		applicationContext.close();
	}
}
