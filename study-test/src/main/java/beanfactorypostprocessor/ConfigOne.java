package beanfactorypostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parse.Person;

@Configuration
public class ConfigOne {

	@Bean
	public Person person(){
		return new Person("person", 18, "email", "朱勇");
	}

	@Bean
	public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){
		return new MyBeanFactoryPostProcessor();
	}

	@Bean
	public MyBeanFactoryPostProcessorTwo myBeanFactoryPostProcessorTwo(){
		return new MyBeanFactoryPostProcessorTwo();
	}

	@Bean
	public MyBeanFactoryPostProcessorThree myBeanFactoryPostProcessorThree(){
		return new MyBeanFactoryPostProcessorThree();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessor MyBeanDefinitionRegistryPostProcessor(){
		return new MyBeanDefinitionRegistryPostProcessor();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessorTwo MyBeanDefinitionRegistryPostProcessorTwo(){
		return new MyBeanDefinitionRegistryPostProcessorTwo();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessorThree MyBeanDefinitionRegistryPostProcessorThree(){
		return new MyBeanDefinitionRegistryPostProcessorThree();
	}
}
