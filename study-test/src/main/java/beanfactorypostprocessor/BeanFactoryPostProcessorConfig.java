package beanfactorypostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import parse.Person;

@Configuration
public class BeanFactoryPostProcessorConfig {
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
	public MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor(){
		return new MyBeanDefinitionRegistryPostProcessor();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessorTwo myBeanDefinitionRegistryPostProcessorTwo(){
		return new MyBeanDefinitionRegistryPostProcessorTwo();
	}

	@Bean
	public MyBeanDefinitionRegistryPostProcessorThree myBeanDefinitionRegistryPostProcessorThree(){
		return new MyBeanDefinitionRegistryPostProcessorThree();
	}

	@Bean(name = "person")
	public Person person(){
		return new Person("BeanFactoryPostProcessorConfig", 18, "email", "朱勇 BeanFactoryPostProcessorConfig");
	}
}
