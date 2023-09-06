package customobjectfactory;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface MyFunctionalInterface {
	Object getObject() throws BeansException;
}
