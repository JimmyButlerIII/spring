package parse;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class CustomerPersonParse extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return Person.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String age = element.getAttribute("age");
		String email = element.getAttribute("email");
		if (StringUtils.hasText(id)){
			builder.addPropertyValue("id",id);
		}
		if (StringUtils.hasText(name)){
			builder.addPropertyValue("name",name);
		}
		if (StringUtils.hasText(age)){
			builder.addPropertyValue("age",Integer.valueOf(age));
		}
		if (StringUtils.hasText(email)){
			builder.addPropertyValue("email",email);
		}
	}
}
