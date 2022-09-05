import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import soundsystem.BlankDisc;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		User user = (User) applicationContext.getBean("user");
		BlankDisc blankDisc = (BlankDisc) applicationContext.getBean("compactDisc");
		blankDisc.play();
	}
}
