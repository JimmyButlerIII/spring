package event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;

public class EventPublish {
	@Autowired
	private ApplicationEventMulticaster applicationEventMulticaster;

	public void publish(String message) {
		CustomerEvent customerEvent = new CustomerEvent(this, message);
		applicationEventMulticaster.multicastEvent(customerEvent);
	}
}
