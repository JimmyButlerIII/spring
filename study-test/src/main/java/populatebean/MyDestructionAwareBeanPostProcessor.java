package populatebean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		System.out.println("MyDestructionAwareBeanPostProcessor.postProcessBeforeDestruction" + beanName);
	}
}
