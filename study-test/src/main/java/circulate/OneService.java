package circulate;

import javax.annotation.Resource;

public class OneService {
	@Resource
	private TwoService twoService;

	public TwoService getTwoService() {
		return twoService;
	}

	public void setTwoService(TwoService twoService) {
		this.twoService = twoService;
	}

	public void test() {
		System.out.println("OneService-test"+ twoService);
	}
}
