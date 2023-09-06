package circulate;

public class One {
	private Two two;

	public Two getTwo() {
		return two;
	}

	public void setTwo(Two two) {
		this.two = two;
	}

	public void test() {
		System.out.println("One-test"+ two);
	}
}
