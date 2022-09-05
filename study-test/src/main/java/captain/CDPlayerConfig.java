package captain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import soundsystem.CDPlayer;
import soundsystem.CompactDisc;

@Configuration
//@Import({CDConfig.class})
@ImportResource("classpath:applicationContext.xml")
public class CDPlayerConfig {

	@Bean
	public CDPlayer cdPlayer(CompactDisc compactDisc) {
		return new CDPlayer(compactDisc);
	}

}
