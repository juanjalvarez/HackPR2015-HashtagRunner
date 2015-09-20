import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends BasicGameState {
	
	private StateBasedGame game;
	private int score;
	private TrueTypeFont headerFont;
	private int tick;
	private Rectangle mainMenuButton;
	
	public GameOverState(StateBasedGame game){
		this.game = game;
		this.score = score;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		headerFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 50), true);
		mainMenuButton = new Rectangle(Main.SCREEN_WIDTH/2-110, Main.SCREEN_HEIGHT/2+100, 150, 100);
		tick = 0;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setFont(headerFont);
		arg2.setColor(Color.white);
		arg2.drawString("You have been defeated by social media!", Main.SCREEN_WIDTH/2-580, Main.SCREEN_HEIGHT/2-50);
		arg2.drawString("Score: " + 666, Main.SCREEN_WIDTH/2-180, Main.SCREEN_HEIGHT/2);
		arg2.setColor(Color.lightGray);
		if(tick>100)
			arg2.fill(mainMenuButton);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		tick++;
		Input i = arg0.getInput();
		if(tick>100)
			if(i.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				if(mainMenuButton.intersects(new Rectangle(i.getMouseX(), i.getMouseY(), 1, 1)))
						game.enterState(0, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return 2;
	}

}
