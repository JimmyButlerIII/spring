package cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibTest {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		enhancer.setCallbackFilter(new UserServiceCallbackFilter());
		Callback[] callbacks = new Callback[]{NoOp.INSTANCE, new UserServiceInterceptorOne(),new UserServiceInterceptorTwo(),new UserServiceInterceptorThree()};
		enhancer.setCallbacks(callbacks);
		UserService userService = (UserService) enhancer.create();
		userService.find();
		System.out.println("==================================n==");
		userService.add();
		System.out.println("====================================");
		userService.delete();
		System.out.println("====================================");
		userService.update();
	}
}
