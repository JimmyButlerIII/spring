package circulate;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

	@Before("execution(* circulate.*.test(..))")
	public void before() {
		System.out.println("before");
	}

	@After("execution(* circulate.*.test(..))")
	public void after() {
		System.out.println("after");
	}
}
