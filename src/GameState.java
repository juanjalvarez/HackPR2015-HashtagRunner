import java.awt.Desktop;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState{

	private ArrayList<HashtagObject> hashList;
	private float x;
	private float size;
	private TrueTypeFont font;
	private long lastAdd;
	private StateBasedGame game;
	private Image background;
	private long tick;
	private ArrayList<String> hashtagList;
	

	public GameState(StateBasedGame sgb){
		game = sgb;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		hashList = new ArrayList<HashtagObject>();
		x = 600;
		size = 50;
		Font f = new Font("Verdana", Font.BOLD, 32);
		font = new TrueTypeFont(f, true);
		lastAdd = 0;
		background = new Image("textures/background.png");
		tick=0;
		try {
			hashtagList = GetAvailableTrends.getPopularHashtags();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		background.draw(-tick/4, 0);
		arg2.drawRect(20, x, size, size);
		for (HashtagObject obj : hashList) {
			arg2.setFont(font);
			arg2.drawRect(obj.getX(), obj.getY(), obj.getHitbox().getWidth(), obj.getHitbox().getHeight());
			arg2.drawString("#" + obj.getText(), obj.getX(), obj.getY());
		}
		arg2.setColor(Color.black);
		arg2.drawString("Score: " + tick, 10, 10);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input i = arg0.getInput();
		if (System.currentTimeMillis() - lastAdd > 500) {
			lastAdd = System.currentTimeMillis();
			hashList.add(new HashtagObject(this, hashtagList.get(new Random().nextInt(hashtagList.size()))));
		}
		if (i.isKeyDown(Input.KEY_UP))
			x -= 5;
		if (i.isKeyDown(Input.KEY_DOWN))
			x += 5;
		if (x + (size / 2) > Main.SCREEN_HEIGHT)
			x = Main.SCREEN_HEIGHT-(size/2);
		if (x - (size / 2) < 0)
			x = size/2;
		Rectangle hitbox = new Rectangle(20, x, size, size);
		for (int x = 0; x < hashList.size(); x++) {
			hashList.get(x).move();
			if (!hashList.get(x).isAlive())
				hashList.remove(x);
			if (hashList.get(x).getHitbox().intersects(hitbox))
				System.exit(0);
			if(hashList.get(x).getHitbox().intersects(new Rectangle(i.getMouseX(), i.getMouseY(), 1, 1)))
				if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					try {
						Desktop.getDesktop().browse(new URI("https://twitter.com/hashtag/" +  hashList.get(x).getRawHash() + "?src=hash&lang=en"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
		}tick++;
	}

	@Override
	public int getID() {
		return 1;
	}

}
