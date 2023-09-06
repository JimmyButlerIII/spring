package scheduling;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SchedulingConfig {

	@Scheduled(cron = "0/1 * * * * ?")
	public void schedule() {
		System.out.println("cashService-- schedule--");
	}
}
