package parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CustomerPersonHandler  extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("person", new CustomerPersonParse());
	}
}
