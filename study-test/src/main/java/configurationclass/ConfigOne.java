package configurationclass;

import beanfactorypostprocessor.SuperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import parse.Person;

@Configuration(proxyBeanMethods = true)
//@Import({ConfigTwo.class})
//@Conditional({ConditionOne.class})
public class ConfigOne extends SuperConfig {


	@Bean(name = "person")
	@Primary
	public Person person(){
		return new Person("person", 18, "email", "朱勇");
	}

	@Bean(name = "person")
	public Person personTwo(){
		return new Person("config on person two", 18, "email", "朱勇 config on person two");
	}
}
