package proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 在CglibTest中创建enhancer对象之后，通过enhancer.setCallbackFilter()方法设置CallbackFilter，此时可以传入多个实现CallbackFilter的数组，
 * 这个UserServiceCallbackFilter就是返回对应下标位置，我们来选择使用哪个Callback。
 */
public class UserServiceCallbackFilter implements CallbackFilter {
	@Override
	public int accept(Method method) {
		if ("find".equals(method.getName())) {
			return 1;
		}else if ("add".equals(method.getName())) {
			return 2;
		}else if ("delete".equals(method.getName())){
			return 3;
		} else {
			return 0;
		}
	}
}
