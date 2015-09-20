import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	private ArrayList<HashtagObject> hashList;
	float x;
	float size;

	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		arg1.drawRect(20, x, size, size);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		hashList = new ArrayList<HashtagObject>();
		x=0;
		size = 10;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input i = arg0.getInput();
		if(i.isKeyDown(Input.KEY_UP))
			x-=5;
		if(i.isKeyDown(Input.KEY_DOWN))
			x+=5;
		if(x+(size/2) > SCREEN_HEIGHT)
			x = SCREEN_HEIGHT;
		if(x-(size/2) < 0)
			x = 0;
	}

	public static void main(String[] args) throws SlickException {
		Game game = new Game("#Hashtagrunner");
		AppGameContainer agc = new AppGameContainer(game);
		agc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		agc.start();
	}

}
