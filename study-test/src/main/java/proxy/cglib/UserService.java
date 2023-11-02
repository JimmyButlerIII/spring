package proxy.cglib;

public class UserService {
	public User find(){
		return new User("zhangsan", 20);
	}

	public void add(){
		System.out.println("UserService Add Method");
	}

	public void delete(){
		System.out.println("UserService Delete Method");
	}

	public void update(){
		System.out.println("UserService Delete Method");
	}
}
