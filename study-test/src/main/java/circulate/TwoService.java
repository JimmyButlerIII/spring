package circulate;

import javax.annotation.Resource;

public class TwoService {
	@Resource
	private OneService oneService;

	public OneService getOneService() {
		return oneService;
	}

	public void setOneService(OneService oneService) {
		this.oneService = oneService;
	}

	public void test() {
		System.out.println("TwoService-test"+ oneService);
	}
}
