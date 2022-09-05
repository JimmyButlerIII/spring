package captain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soundsystem.CompactDisc;
import soundsystem.SgtPeppers;

@Configuration
public class CDConfig {
	@Bean
	public CompactDisc sgtPeppers(){
		return new SgtPeppers();
	}

}
