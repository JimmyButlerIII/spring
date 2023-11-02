package aop;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.util.Properties;

public class AopTestMain {
	public static void main(String[] args) throws Exception {
		saveGeneratedCGlibProxyFiles(System.getProperty("user.dir")+"/proxy");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
		UserService userService = applicationContext.getBean(UserService.class);
		userService.add(1, 2);
//		userService.div(1, 0);
		AopConfig aopConfig = applicationContext.getBean(AopConfig.class);
		CardService cardService = aopConfig.cardService();
		aopConfig.userService(cardService);
		System.out.println(cardService);
	}

	public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
		Field field = System.class.getDeclaredField("props");
		field.setAccessible(true);
		Properties props = (Properties) field.get(null);
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
		props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
	}
}
