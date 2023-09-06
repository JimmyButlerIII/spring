package circulate;

import org.springframework.beans.factory.FactoryBean;

//@Component
public class TwoServiceFactoryBean implements FactoryBean<TwoService> {
	@Override
	public TwoService getObject() throws Exception {
		return  new TwoService();
	}

	@Override
	public Class<?> getObjectType() {
		return TwoService.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
