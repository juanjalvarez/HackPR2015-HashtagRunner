import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public Game(String title) throws FileNotFoundException {
		super(title);
		File scoreFile = new File("highscore.dat");
		PrintWriter scoreWriter = new PrintWriter(scoreFile);
		if(!scoreFile.exists())
			scoreWriter.println(0);
			
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenuState(this));
		addState(new GameState(this));
	}

}
