package populatebean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("populatebean")
public class PopulateBeanConfig {

    @Bean
    public Person person(Address address){
        Person person =  new Person();
        person.setAddress(address);
        return person;
    }

    @Bean
    public Address address(){
        return new Address("河南省","郑州市","金水区");
    }

	@Bean
	public MyDestructionAwareBeanPostProcessor myDestructionAwareBeanPostProcessor(){
		return new MyDestructionAwareBeanPostProcessor();
	}
}
