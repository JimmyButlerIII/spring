package autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class SuperCashService {
    private CardService cardService;

    @Autowired
    private void superCashService(CardService cardServiceOne) {
        this.cardService = cardServiceOne;
        System.out.println("SuperCashService is called" + cardService);
    }
}
