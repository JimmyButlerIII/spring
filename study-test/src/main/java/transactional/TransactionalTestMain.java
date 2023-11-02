package transactional;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;
import java.util.Properties;

public class TransactionalTestMain {
	public static void main(String[] args) throws Exception {
		saveGeneratedCGlibProxyFiles(System.getProperty("user.dir")+"/proxy/transactional");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
		UserService userService = applicationContext.getBean(UserService.class);
//		String user = userService.findUser(3);
//		System.out.println(user);
//		userService.updateEmail(2, "zcc@foxmail.com");
		applicationContext.getBean("userDao");
		userService.addAge(2);
	}

	public static void saveGeneratedCGlibProxyFiles(String dir) throws Exception {
		Field field = System.class.getDeclaredField("props");
		field.setAccessible(true);
		Properties props = (Properties) field.get(null);
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, dir);//dir为保存文件路径
		props.put("net.sf.cglib.core.DebuggingClassWriter.traceEnabled", "true");
	}
}
