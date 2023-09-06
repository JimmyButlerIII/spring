package circulardependency;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryBean implements FactoryBean<UserService> {
	@Override
	public UserService getObject() throws Exception {
		UserService userService  = new UserService();
		System.out.println("MyFactoryBean.getObject() userService = " + userService);
		return userService;
	}

	@Override
	public Class<?> getObjectType() {
		return UserService.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
