import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

//andy is poopyhead
public class Game extends BasicGame {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	private ArrayList<HashtagObject> hashList;
	private float x;
	private float size;
	private TrueTypeFont font;
	private long lastAdd;

	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		arg1.drawRect(20, x, size, size);
		for (HashtagObject obj : hashList) {
			arg1.setFont(font);
			arg1.drawRect(obj.getX(), obj.getY(), obj.getHitbox().getWidth(), obj.getHitbox().getHeight());
			arg1.drawString("#" + obj.getText(), obj.getX(), obj.getY());
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		hashList = new ArrayList<HashtagObject>();
		x = 600;
		size = 50;
		Font f = new Font("Verdana", Font.BOLD, 32);
		font = new TrueTypeFont(f, false);
		lastAdd = 0;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if (System.currentTimeMillis() - lastAdd > 500) {
			lastAdd = System.currentTimeMillis();
			hashList.add(new HashtagObject(this, "randomword"));
		}
		Input i = arg0.getInput();
		if (i.isKeyDown(Input.KEY_UP))
			x -= 5;
		if (i.isKeyDown(Input.KEY_DOWN))
			x += 5;
		if (x + (size / 2) > SCREEN_HEIGHT)
			x = SCREEN_HEIGHT;
		if (x - (size / 2) < 0)
			x = 0;
		Rectangle hitbox = new Rectangle(20, x, size, size);
		for (int x = 0; x < hashList.size(); x++) {
			hashList.get(x).move();
			if (!hashList.get(x).isAlive())
				hashList.remove(x);
			if (hashList.get(x).getHitbox().intersects(hitbox))
				System.out.println("fix me");
		}
	}

	public static void main(String[] args) throws SlickException {
		Game game = new Game("#Hashtagrunner");
		AppGameContainer agc = new AppGameContainer(game);
		agc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		agc.setTargetFrameRate(60);
		agc.start();
	}

}
