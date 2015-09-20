import java.awt.Font;

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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {
	
	Rectangle play;
	Rectangle exit;
	StateBasedGame game;
	TrueTypeFont headerFont;
	TrueTypeFont headerFont2;
	TrueTypeFont buttonFont;
	Image background;
	
	public MainMenuState(StateBasedGame game){
		this.game = game;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		play = new Rectangle(Main.SCREEN_WIDTH/2-130, Main.SCREEN_HEIGHT/2, 200, 100);
		exit = new Rectangle(Main.SCREEN_WIDTH/2-130, Main.SCREEN_HEIGHT/2+110, 200, 100);
		Font f1 = new Font("Verdana", Font.BOLD, 50);
		Font f1_2 = new Font("Verdana", Font.BOLD, 52);
		Font f2 = new Font("Verdana", Font.PLAIN, 40);
		headerFont = new TrueTypeFont(f1, true);
		headerFont2 = new TrueTypeFont(f1_2, true);
		buttonFont = new TrueTypeFont(f2, true);
		background = new Image("textures/background.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		background.draw(0, 0);
		arg2.setColor(Color.black);
		arg2.setFont(headerFont2);
		arg2.drawString("#HashtagRunner", Main.SCREEN_WIDTH/2-263, 150);
		arg2.setFont(headerFont);
		arg2.setColor(Color.blue);
		arg2.drawString("#HashtagRunner", Main.SCREEN_WIDTH/2-255, 150);
		arg2.setColor(Color.lightGray);
		arg2.fill(play);
		arg2.fill(exit);
		arg2.setColor(Color.black);
		arg2.setFont(buttonFont);
		arg2.drawString("Play", Main.SCREEN_WIDTH/2-75, Main.SCREEN_HEIGHT/2+25);
		arg2.drawString("Exit", Main.SCREEN_WIDTH/2-75, Main.SCREEN_HEIGHT/2+135);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input i = arg0.getInput();
		if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(new Rectangle(i.getMouseX(), i.getMouseY(), 1, 1).intersects(play))
				game.enterState(1, new FadeOutTransition(), new FadeInTransition());
			if(new Rectangle(i.getMouseX(), i.getMouseY(), 1, 1).intersects(exit))
				System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
