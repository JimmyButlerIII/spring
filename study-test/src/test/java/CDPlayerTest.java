import captain.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import soundsystem.CompactDisc;
import soundsystem.MediaPlayer;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CDPlayerConfig.class})
public class CDPlayerTest {

//	@Autowired
//	private CompactDisc cd;

	@Inject
	private CompactDisc cd;

	@Autowired
	private MediaPlayer player;

	@Test
	public void testOne(){
		player.play();
	}
}
