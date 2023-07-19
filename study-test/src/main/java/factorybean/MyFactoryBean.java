package factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<FactoryUser> {
	@Override
	public FactoryUser getObject() throws Exception {
		return new FactoryUser("changchengyes", 18, "email.com");
	}

	@Override
	public Class<?> getObjectType() {
		return FactoryUser.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public String toString() {
		return "MyFactoryBean{}";
	}

}
