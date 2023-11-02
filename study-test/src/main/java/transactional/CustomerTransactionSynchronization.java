package transactional;

import org.springframework.transaction.support.TransactionSynchronization;

public class CustomerTransactionSynchronization implements TransactionSynchronization {

	private String desc;

//	@Override
//	public void beforeCommit(boolean readOnly) {
//		System.out.println(desc);
//	}

//	@Override
//	public void afterCompletion(int status) {
//		System.out.println("customer afterCompletion");
//	}
//
//	@Override
//	public void beforeCompletion() {
//		System.out.println("customer beforeCompletion");
//	}


	@Override
	public void afterCommit() {
		System.out.println(desc);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public CustomerTransactionSynchronization(String desc) {
		this.desc = desc;
	}


}


