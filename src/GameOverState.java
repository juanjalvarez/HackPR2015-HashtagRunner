import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {
	
	private StateBasedGame game;
	private int score;
	private TrueTypeFont headerFont;
	
	public GameOverState(StateBasedGame game){
		this.game = game;
		this.score = score;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		headerFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 50), true);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setFont(headerFont);
		arg2.setColor(Color.white);
		arg2.drawString("lol u die", Main.SCREEN_WIDTH/2-180, Main.SCREEN_HEIGHT/2-25);
		arg2.drawString("Score: " + 666, Main.SCREEN_WIDTH/2-180, Main.SCREEN_HEIGHT/2+25);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return 2;
	}

}
