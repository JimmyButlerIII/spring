package aop;

//@Component
public class UserService {

	public Integer add(Integer a, Integer b) {
		System.out.println("UserService add");
		return a + b;
	}

	public Integer div(Integer a, Integer b) {
		return a / b;
	}
}
