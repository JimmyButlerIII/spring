package beanfactorypostprocessor;

import org.springframework.context.annotation.Bean;
import parse.Person;

public class SuperConfig {

	@Bean("superPerson")
	public Person person(){
		return new Person("person", 18, "email", "朱勇");
	}
}
