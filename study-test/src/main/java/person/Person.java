package person;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.ws.WebServiceRef;

@Component
public class Person {

	@WebServiceRef
	private Integer age;
	private String name;
	private String email;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person(){}
	public Person(Integer age, String name){
		this.age = age;
		this.name = name;
	}

//	@Autowired(required = false)
	public Person(Integer age){
		this.age = age;
	}

	public Person(String name, Integer age){
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person{" +
				"age=" + age +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}' + "hashcode = " + this.hashCode();
	}
//
//	@Lookup
//	public Person person(){
//		return new Person(12, "朱勇");
//	}

	@PostConstruct
	public void init(){
		System.out.println("person init...");
	}

	@PreDestroy
	public void destroy(){
		System.out.println("person destroy...");
	}
}
