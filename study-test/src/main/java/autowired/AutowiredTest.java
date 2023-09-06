package autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        CashService cashService = applicationContext.getBean(CashService.class);
//        cashService.print();
    }
}
