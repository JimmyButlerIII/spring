package customobjectfactory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class TestMain {
	public static void main(String[] args) {
		myAddSingletonFactory("a", () -> myGetEarlyBeanReference("a", null, null));
	}

	protected static void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {

	}

	protected static void myAddSingletonFactory(String beanName, MyFunctionalInterface myFunctionalInterface) {

	}

	protected static Object  getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
		Object exposedObject = bean;
		return exposedObject;
	}

	protected static Object  myGetEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
		Object exposedObject = bean;
		return exposedObject;
	}
}
