package beanpostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPostProcessorConfigOne {

	@Bean
	public MyBeanPostProcessorOne MyBeanPostProcessorOne(){
		return new MyBeanPostProcessorOne();
	}

	@Bean
	public MyBeanPostProcessorTwo myBeanPostProcessorTwo(){
		return new MyBeanPostProcessorTwo();
	}


	@Bean
	public MyBeanPostProcessorThree myBeanPostProcessorThree(){
		return new MyBeanPostProcessorThree();
	}

	@Bean
	public MyBeanPostProcessorFour myBeanPostProcessorFour(){
		return new MyBeanPostProcessorFour();
	}

//	@Bean("person")
//	public Person person(){
//		return new Person("BeanPostProcessorConfigOne", 18, "email", "朱勇BeanPostProcessorConfigOne");
//	}
//
//	@Bean("person")
//	public Person personOne(){
//		return new Person("BeanPostProcessorConfigOne", 18, "email", "朱勇BeanPostProcessorConfigOneTwo");
//	}
}
