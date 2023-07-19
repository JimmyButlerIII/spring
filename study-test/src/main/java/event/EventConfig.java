package event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
	@Bean
	public EventHandler eventHandler() {
		return new EventHandler();
	}

	@Bean
	public EventPublish eventPublish() {
		return new EventPublish();
	}
}
