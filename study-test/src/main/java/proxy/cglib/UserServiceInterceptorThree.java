package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceInterceptorThree implements MethodInterceptor {
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("UserServiceInterceptorThree Before Method Invoke");
		Object object = proxy.invokeSuper(obj, args);
		System.out.println("UserServiceInterceptorThree After Method Invoke" + object);
		return object;
	}
}
