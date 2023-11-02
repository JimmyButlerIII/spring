package circulate;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.util.Properties;

public class CirculateTest {
	public static void main(String[] args) throws Exception {
		saveGeneratedCGlibProxyFiles(System.getProperty("user.dir")+"/proxy");
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
//		OneService oneService = (OneService)applicationContext.getBean("oneService");
//		oneService.test();
//

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
//		CardService bean = applicationContext.getBean(CardService.class);
//		bean.add();

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
		RoomService roomService = applicationContext.getBean(RoomService.class);
		CardService cardService = applicationContext.getBean(CardService.class);
//		roomService.add();
//		roomService.update("123");
		cardService.edit("123");


//		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("circulate.xml");
//		One bean = applicationContext.getBean(One.class);
//		bean.test();

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
//		CirculateConfig bean = applicationContext.getBean(CirculateConfig.class);

//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CirculateConfig.class);
//		OneService bean = applicationContext.getBean(OneService.class);
//		bean.test();
	}

	public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
		Field field = System.class.getDeclaredField("props");
		field.setAccessible(true);
		Properties props = (Properties) field.get(null);
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
		props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
	}
}
