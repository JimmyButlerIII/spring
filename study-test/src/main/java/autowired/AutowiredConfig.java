package autowired;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "autowired")
@PropertySource("classpath:application.properties")
@EnableScheduling
public class AutowiredConfig {
}
