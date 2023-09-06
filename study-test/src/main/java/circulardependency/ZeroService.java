package circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZeroService {

	private UserService userService;

	private CashService cashService;

	@Autowired
	public void setUserService(UserService userService, CashService cashService) {
		this.userService = userService;
		this.cashService = cashService;
	}

	public UserService getUserService() {
		return userService;
	}

	public CashService getCashService() {
		return cashService;
	}

}
