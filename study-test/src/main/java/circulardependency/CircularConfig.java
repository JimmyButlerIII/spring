package circulardependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "circulardependency")
public class CircularConfig {

	@Bean
	public CashService cashService() {
		CashService cashService  = new CashService();
		System.out.println("CircularConfig.cashService()" + cashService);
		return cashService;
	}
}
