package parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义Handler,注册自定义标签解析器
 */
public class CustomerPersonHandler  extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("person", new CustomerPersonParse());
	}
}
