import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
private int score = 0;
private int highscore = 0;
private File scoreFile = new File("highscore.dat");
private PrintWriter scoreWriter = new PrintWriter(scoreFile);

	public Game(String title) throws FileNotFoundException {
		
		super(title);
		Scanner reader = new Scanner(scoreFile);
		if(!scoreFile.exists())
			scoreWriter.println(0);
		else
			setHighScore(reader.nextInt());
			
	}
	
	public void setScore(int sc){
		score = sc;
	}
	
	public int getScore(){
		return score; 
	}
	
	public void setHighScore(int hs){
		highscore = hs;
		scoreWriter.println(hs);
	}
	
	public int getHighScore(){
		return highscore; 
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenuState(this));
		addState(new GameState(this));
		addState(new GameOverState(this));
	}

}
