package event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(EventConfig.class);
		EventPublish eventPublish = applicationContext.getBean("eventHandler", EventPublish.class);
		eventPublish.publish("hello world");
	}
}
