package beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;

public class MyBeanPostProcessorOne implements BeanPostProcessor, PriorityOrdered {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessorOne PriorityOrdered postProcessBeforeInitialization()...  " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessorOne PriorityOrdered postProcessAfterInitialization()..." + beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
