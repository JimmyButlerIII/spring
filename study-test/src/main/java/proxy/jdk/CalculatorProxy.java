package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {
    public static Calculator getProxy(final Calculator calculator){
        ClassLoader loader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
		InvocationHandler h = new MyInvocationHandler(calculator);
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        return (Calculator) proxy;
    }

	public static class MyInvocationHandler implements InvocationHandler {
		private Object target;

		public MyInvocationHandler(Object target) {
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			try {
				System.out.println("before");
				result = method.invoke(target, args);
				System.out.println("after");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
			return result;
		}
	}
}