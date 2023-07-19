package abstractbean;

public class ParentBean {
	private String name;
	private Integer age;
	private String gender;

	public ParentBean(String name, Integer age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "ParentBean{" +
				"name='" + name + '\'' +
				", age=" + age +
				", gender='" + gender + '\'' +
				'}';
	}
}
