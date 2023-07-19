package event;

import org.springframework.context.event.EventListener;

public class EventHandler {

	@EventListener(classes = CustomerEvent.class)
	public void handleEvent(CustomerEvent event) {
		System.out.println("Handling event: " + event);
	}
}
