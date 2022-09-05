package soundsystem;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

//@Component("lonely")
@Named("lonely")
public class SgtPeppers implements CompactDisc{
	private static final String title = "Sgt.Pepper's Lonely Hearts Club Band";
	public String artList = "the beatles";
	@Override
	public void play() {
		System.out.println("Playing " + title + " by " + artList);
	}
	@PostConstruct
	public void init(){
		System.out.println("sgtPeppers init method");
	}

	@PreDestroy
	public void destroy(){
		System.out.println("sgtPeppers destroy method");
	}
}
