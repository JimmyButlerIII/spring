package circulate;

import org.springframework.stereotype.Component;

@Component
public class RoomService {
	public void add() {
		System.out.println("RoomService add");
	}

	public void delete() {
		System.out.println("RoomService delete");
	}

	public void update(String name) {
		System.out.println("RoomService update" + name);
	}
}
