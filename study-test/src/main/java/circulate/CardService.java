package circulate;

import org.springframework.stereotype.Component;

@Component
public class CardService {

	public void add() {
		System.out.println("add");
	}
	public void find() {
		System.out.println("find");
	}

	public void edit(String name) {
		System.out.println("CardService edit" + name);
	}
}
