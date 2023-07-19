package cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceInterceptorTwo implements MethodInterceptor {
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("UserServiceInterceptorTwo Before Method Invoke");
		Object object = proxy.invokeSuper(obj, args);
		System.out.println("UserServiceInterceptorTwo After Method Invoke" + object);
		return object;
	}
}
