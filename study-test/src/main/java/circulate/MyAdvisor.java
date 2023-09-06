package circulate;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

public class MyAdvisor implements Advisor {
	@Override
	public Advice getAdvice() {
		return null;
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}
}
