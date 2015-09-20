import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	private int score = 0;
	private int highscore;

	public Game(String title) throws FileNotFoundException {
		super(title);
		score = 0;
		File scoreFile = new File("highscore.dat");
		if (!scoreFile.exists()) {
			PrintWriter scoreWriter = new PrintWriter(scoreFile);
			scoreWriter.println(score);
			scoreWriter.close();
		}
		Scanner reader = new Scanner(scoreFile);
		setHighScore(reader.nextInt());
		reader.close();

	}

	public void setScore(int sc) {
		if (sc > getHighScore())
			try {
				setHighScore(sc);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		score = sc;
	}

	public int getScore() {
		return score;
	}

	public void setHighScore(int hs) throws FileNotFoundException {
		File scoreFile = new File("highscore.dat");
		PrintWriter scoreWriter = new PrintWriter(scoreFile);
		highscore = hs;
		scoreWriter.println(hs);
		scoreWriter.close();
	}

	public int getHighScore() {
		return highscore;
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenuState(this));
		addState(new GameState(this).initialize());
		addState(new GameOverState(this));
	}

}
