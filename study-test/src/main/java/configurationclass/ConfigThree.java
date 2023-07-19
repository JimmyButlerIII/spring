package configurationclass;

import beanfactorypostprocessor.SuperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parse.Person;

@Configuration
//@Import({ConfigTwo.class})
//@Conditional({ConditionOne.class})
public class ConfigThree extends SuperConfig {


	@Bean(name = "person")
	public Person person(){
		return new Person("person", 18, "email", "朱勇");
	}

	@Bean(name = "person")
	public Person personTwo(){
		return new Person("config on person two", 18, "email", "朱勇 config on person two");
	}
}
