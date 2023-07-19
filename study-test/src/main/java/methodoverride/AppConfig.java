package methodoverride;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("methodoverride")
public class AppConfig {
	@Bean
	public PrototypeBean prototypeBean() {
		return new PrototypeBean();
	}

	@Bean
	public AbstractService abstractService() {
		return new AbstractService() {
			public PrototypeBean getPrototypeBean() {
				// This method will be overridden by Spring at runtime
				return new PrototypeBean();
			}
		};
	}

	@Bean
	public PrototypeBeanService prototypeBeanService() {
		return new PrototypeBeanService();
	}
}
