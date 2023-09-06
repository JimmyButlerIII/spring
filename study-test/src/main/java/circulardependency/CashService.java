package circulardependency;

import org.springframework.stereotype.Service;

@Service
public class CashService {

	public CashService() {
		System.out.println("CashService.CashService()");
	}
}
