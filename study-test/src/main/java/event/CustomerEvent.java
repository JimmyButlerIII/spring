package event;

import org.springframework.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent {
	private String string;

	public CustomerEvent(Object source, String string) {
		super(source);
		this.string =  string;
	}
}
