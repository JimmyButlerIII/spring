package circulate;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("circulate")
@EnableAspectJAutoProxy(exposeProxy = true)
public class CirculateConfig {
//	@Bean
//	public CardService cardService() {
//		System.out.println("cardService bean method invoke");
//		return new CardService();
//	}

//	@Bean
//	public TwoService twoService() {
//		return new TwoService();
//	}

//	@Bean
//	public OneService oneService(TwoService twoService) {
//		return new OneService(twoService);
//	}

//	@Bean
//	public TwoService twoService(OneService oneService) {
//		System.out.println("twoService bean method invoke");
//		return new TwoService(oneService);
//	}
}
