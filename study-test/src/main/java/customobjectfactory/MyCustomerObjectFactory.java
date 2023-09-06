package customobjectfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyCustomerObjectFactory implements ObjectFactory<Object> {
	private final String beanName;
	private final RootBeanDefinition mbd;
	private final Object bean;

	public MyCustomerObjectFactory(String beanName, RootBeanDefinition mbd, Object bean) {
		this.beanName = beanName;
		this.mbd = mbd;
		this.bean = bean;
	}

	@Override
	public Object getObject() throws BeansException {
		return getEarlyBeanReference(beanName, mbd, bean);
	}

	protected Object  getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
		Object exposedObject = bean;
		return exposedObject;
	}


}
