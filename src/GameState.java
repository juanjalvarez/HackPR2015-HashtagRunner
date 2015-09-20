import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

	public GameState(StateBasedGame sgb){
		game = sgb;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		hashList = new ArrayList<HashtagObject>();
		x = 600;
		size = 50;
		Font f = new Font("Verdana", Font.BOLD, 32);
		font = new TrueTypeFont(f, false);
		lastAdd = 0;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawRect(20, x, size, size);
		for (HashtagObject obj : hashList) {
			arg2.setFont(font);
			arg2.drawRect(obj.getX(), obj.getY(), obj.getHitbox().getWidth(), obj.getHitbox().getHeight());
			arg2.drawString("#" + obj.getText(), obj.getX(), obj.getY());
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (System.currentTimeMillis() - lastAdd > 500) {
			lastAdd = System.currentTimeMillis();
			hashList.add(new HashtagObject(this, "randomword"));
		}
		Input i = arg0.getInput();
		if (i.isKeyDown(Input.KEY_UP))
			x -= 5;
		if (i.isKeyDown(Input.KEY_DOWN))
			x += 5;
		if (x + (size / 2) > Main.SCREEN_HEIGHT)
			x = Main.SCREEN_HEIGHT;
		if (x - (size / 2) < 0)
			x = 0;
		Rectangle hitbox = new Rectangle(20, x, size, size);
		for (int x = 0; x < hashList.size(); x++) {
			hashList.get(x).move();
			if (!hashList.get(x).isAlive())
				hashList.remove(x);
			if (hashList.get(x).getHitbox().intersects(hitbox))
				System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
