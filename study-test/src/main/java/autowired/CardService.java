package autowired;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CardService implements InitializingBean {
	@Value("${callback_url}")
	private String callback;

	private CashService cashService;

	@Autowired
	public void setCashService(CashService cashService){
		this.cashService = cashService;
	}

	@PostConstruct
	public void initMethod(){
		System.out.println("CardService initMethod");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("CardService afterPropertiesSet");
	}
}
