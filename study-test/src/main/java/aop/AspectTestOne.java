package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
//@Component
//@Order(11)
public class AspectTestOne {

	@Before("pointcut()")
	public void before() {
		System.out.println("AspectTestOne before");
	}

	@After("pointcut()")
	public void after() {
		System.out.println("AspectTestOne after");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AspectTestOne around before");
		Object proceed = pjp.proceed();
		System.out.println("AspectTestOne around after");
		return proceed;
	}

	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("AspectTestOne afterReturning");
	}

	@AfterThrowing("pointcut()")
	public void AfterThrowing() {
		System.out.println("AspectTestOne afterReturning");
	}

	@Pointcut("execution(* aop.*.*(..))")
	public void pointcut() {
	}
}
