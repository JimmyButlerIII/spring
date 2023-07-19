package abstractbean;

public class ChildrenBean {
	private String name;
	private String age;
	private String gender;
	private Double height;
	// 省略 set/get/toString方法

	public ChildrenBean() {
	}

	public ChildrenBean(String name, String age, String gender, Double height) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}



	@Override
	public String toString() {
		return "ChildrenBean{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				", gender='" + gender + '\'' +
				", height=" + height +
				'}';
	}
}