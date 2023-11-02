package circulate;

import javax.annotation.Resource;

//@Component
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
		two();
		System.out.println("OneService-test "+ twoService);
	}

	public void two(){
		System.out.println("OneService-two");
	}

	public OneService(TwoService twoService) {
		this.twoService = twoService;
	}
}
