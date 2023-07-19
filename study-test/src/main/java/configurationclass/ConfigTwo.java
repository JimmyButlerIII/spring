package configurationclass;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import parse.Person;

@Configuration
public class ConfigTwo {
	@Bean(name = "person", autowire = Autowire.BY_NAME)
	@Scope("singleton")
	public static Person person(){
		return new Person("ConfigTwo", 18, "email", "朱勇ConfigTwo");
	}
}
