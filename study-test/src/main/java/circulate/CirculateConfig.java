package circulate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("circulate")
@EnableAspectJAutoProxy
public class CirculateConfig {

	@Bean("threeService")
	public ThreeService threeService(){
		return new ThreeService();
	}

//	@Bean
//	public OneService oneService(){
//		return new OneService();
//	}
//
//	@Bean
//	public TwoService twoService(){
//		return new TwoService();
//	}

}
