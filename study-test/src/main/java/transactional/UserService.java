package transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	CardService cardService;

	public void insertUser(String userName, String password, String email, Integer age) {
		userDao.insertUser(userName, password, email, age);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateEmail(Integer id, String email) {
//		TransactionSynchronizationManager.registerSynchronization(new CustomerTransactionSynchronization("user_service"));
		cardService.updateEmail(id, email);
		return 1;
	}

	@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
	public int updateEmailForDao(Integer id, String email) {
//		TransactionSynchronizationManager.registerSynchronization(new CustomerTransactionSynchronization("user_service"));
		userDao.updateEmail(id, email);
		return 1;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAge(Integer id) throws IOException {
		userDao.addScore(id);
		userDao.addAge(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addScore(Integer id){
		return userDao.addScore(id);
	}


}
