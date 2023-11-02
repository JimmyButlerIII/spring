package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
//@Component
public class AspectTestTwo {

	@Before("pointcut()")
	public void before() {
		System.out.println("AspectTestTwo before");
	}

	@After("pointcut()")
	public void after() {
		System.out.println("AspectTestTwo after");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AspectTestTwo around before");
		Object proceed = pjp.proceed();
		System.out.println("AspectTestTwo around after");
		return proceed;
	}

	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("AspectTestTwo afterReturning");
	}

	@AfterThrowing("pointcut()")
	public void AfterThrowing() {
		System.out.println("AspectTestTwo afterReturning");
	}

	@Pointcut("execution(* circulate.CardService.*(..))")
	public void pointcut() {
	}
}
