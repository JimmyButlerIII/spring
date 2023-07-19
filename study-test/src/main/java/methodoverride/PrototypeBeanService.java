package methodoverride;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class PrototypeBeanService {

	@Lookup
	public PrototypeBean getPrototypeBean() {
		return new PrototypeBean();
	}
}
