public class User {
	private String name;
	private Integer age;
	private String email;

	public User(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				'}';
	}

	public User(String name, Integer age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
}
