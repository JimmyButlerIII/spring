package aop;

import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("aop")
public class AopConfig {
	@Bean
	@Scope("prototype")
	public UserService userService(CardService cardService) {
		return new UserService();
	}

	@Bean
	public CardService cardService() {
		return new CardService();
	}
}
