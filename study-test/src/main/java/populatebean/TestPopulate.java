package populatebean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestPopulate {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("populateBean.xml");
//		Person person = ac.getBean("person", Person.class);
//		ac.close();
//		System.out.println(person);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PopulateBeanConfig.class);
//        Person person = applicationContext.getBean("person", Person.class);
//        System.out.println(person);
		applicationContext.close();
    }
}
