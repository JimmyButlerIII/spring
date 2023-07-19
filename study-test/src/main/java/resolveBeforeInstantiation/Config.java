package resolveBeforeInstantiation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	public BeforeInstantiation beforeInstantiation() {
		return new BeforeInstantiation();
	}

	@Bean
	public MyInstantiationAwareBeanPostProcessor myInstantiationAwareBeanPostProcessor() {
		return new MyInstantiationAwareBeanPostProcessor();
	}
}
