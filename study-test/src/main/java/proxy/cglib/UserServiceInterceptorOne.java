package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceInterceptorOne implements MethodInterceptor {
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("UserServiceInterceptorOne Before Method Invoke");
		Object object = proxy.invokeSuper(obj, args);
		System.out.println("UserServiceInterceptorOne After Method Invoke" + object);
		return object;
	}
}
