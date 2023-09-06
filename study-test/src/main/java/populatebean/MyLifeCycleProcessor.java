package populatebean;

import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyLifeCycleProcessor implements LifecycleProcessor {
	@Override
	public void start() {
		System.out.println("MyLifeCycleProcessor start");
	}

	@Override
	public void stop() {
		System.out.println("MyLifeCycleProcessor stop");
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public void onRefresh() {
		System.out.println("MyLifeCycleProcessor onRefresh");
	}

	@Override
	public void onClose() {
		System.out.println("MyLifeCycleProcessor onClose");
	}
}
