package methodoverride;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring中默认的对象都是单例的，spring会在一级缓存中持有该对象，方便下次直接获取，
 * 那么如果是原型作用域的话，会创建一个新的对象
 * 如果想在一个单例模式的bean下引用一个原型模式的bean,此时就需要引用lookup-method标签来解决此问题
 * 通过拦截器的方式每次需要的时候都去创建最新的对象，而不会把原型对象缓存起来，
 *
 */


public class MethodOverrideTest {
	public static void main(String[] args) {
//		ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("method-override.xml");
//		FruitPlate fruitPlate1 = applicationContext.getBean("fruitPlate1", FruitPlate.class);
//		fruitPlate1.getFruit();
//		FruitPlate fruitPlate2 = applicationContext.getBean("fruitPlate2", FruitPlate.class);
//		System.out.println(fruitPlate2.getFruit());

		// 注解方式实现
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		AbstractService abstractService = applicationContext.getBean("abstractService", AbstractService.class);
//		abstractService.getPrototypeBean();
//		abstractService.getPrototypeBean();
//		System.out.println(abstractService.getPrototypeBean());
//		System.out.println(abstractService.getPrototypeBean());


		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		PrototypeBeanService prototypeBeanService = applicationContext.getBean("prototypeBeanService", PrototypeBeanService.class);
		prototypeBeanService.getPrototypeBean();
		prototypeBeanService.getPrototypeBean();
		System.out.println(prototypeBeanService.getPrototypeBean());
		System.out.println(prototypeBeanService.getPrototypeBean());

	}
}
