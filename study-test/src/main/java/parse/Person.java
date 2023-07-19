package parse;

public class Person {
	private String id;
	private Integer age;
	private String email;

	private String name;

	public Person() {}

	public Person(String id, Integer age, String email, String name) {
		this.id = id;
		this.age = age;
		this.email = email;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public String toString() {
//		return "Person{" +
//				"id='" + id + '\'' +
//				", age=" + age +
//				", email='" + email + '\'' +
//				", name='" + name + '\'' +
//				'}';
//	}
}
