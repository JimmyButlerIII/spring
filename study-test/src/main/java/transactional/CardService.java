package transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class CardService {
	@Autowired
	private UserDao userDao;

	@Transactional(propagation = Propagation.NESTED)
	public Integer updateEmail(Integer id, String email) {
		TransactionSynchronizationManager.registerSynchronization(new CustomerTransactionSynchronization("card_service"));
		return userDao.updateEmail(id, email);
	}
}
