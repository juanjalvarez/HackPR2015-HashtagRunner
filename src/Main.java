import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;


	public static void main(String[] args) throws SlickException {
		Game game = new Game("#Hashtagrunner");
		AppGameContainer agc = new AppGameContainer(game);
		agc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		agc.setTargetFrameRate(60);
		agc.start();
	}
}
