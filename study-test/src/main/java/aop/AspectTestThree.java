package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
//@Component
//@Order(1)
public class AspectTestThree {

//	@After("bean(roomService)")
//	public void around() {
//		System.out.println("AspectTestThree after");
//	}
	@Before("pointcut()")
	public void before() {
		System.out.println("AspectTestThree before");
	}


	@After("pointcut()")
	public void after() {
		System.out.println("AspectTestThree after");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AspectTestThree around before");
		Object proceed = pjp.proceed();
		System.out.println("AspectTestThree around after");
		return proceed;
	}

	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("AspectTestThree afterReturning");
	}

	@AfterThrowing("pointcut()")
	public void AfterThrowing() {
		System.out.println("AspectTestThree AfterThrowing");
	}

	@Pointcut("execution(* aop.*.*(..))")
	public void pointcut() {
	}
}
