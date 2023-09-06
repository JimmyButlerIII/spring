package circulardependency;

import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class UserService {
	@Autowired
	private CashService cashService;

	public UserService(){
		System.out.println("UserService.UserService");
	}
}
