import java.io.FileNotFoundException;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.LogSystem;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final Random generator = new Random();

	public static void main(String[] args) throws SlickException, FileNotFoundException {
		Game game = new Game("#Hashtagrunner");
		AppGameContainer agc = new AppGameContainer(game);
		Log.setLogSystem(new LogSystem() {

			@Override
			public void debug(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void error(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void error(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void error(String arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void info(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void warn(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void warn(String arg0, Throwable arg1) {
				// TODO Auto-generated method stub

			}

		});
		Log.setVerbose(false);
		agc.setShowFPS(false);
		agc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		agc.setTargetFrameRate(60);
		agc.start();
	}

	public static int nextInt(int lim) {
		return generator.nextInt(lim);
	}
}
