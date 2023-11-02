package autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CashService extends SuperCashService {

    private String callback;
//    private CardService cardService;
//
//    private String name;
//
//    @Autowired
//    public void cashService(CardService service) {
//        this.cardService = service;
//        System.out.println("CashService(CardService cardService) is called" + cardService);
//    }
//
    @Value("${callback_url}")
    public void setCallback(String callback) {
        this.callback = callback;
    }
//
//    public void print() {
//        System.out.println("callback: " + callback);
//    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void schedule() {
        System.out.println("cashService-- schedule--");
    }
}
