import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Test extends BasicGame{
	private Image cherry;
	private Random rand;
	
	public Test(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException{
		Test a = new Test("Hashtag Runner");
		AppGameContainer gc = new AppGameContainer(a);
		gc.setDisplayMode(1280, 720, false);
		gc.start();
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		cherry.draw(0, 0);
		cherry.draw(arg0.getInput().getAbsoluteMouseX(), arg0.getInput().getAbsoluteMouseY());
		int its = rand.nextInt(100);
		Image[] cherries = new Image[its];
		for(int i = 0; i < its; i++){
			cherries[i] = cherry;
			cherries[i].draw(rand.nextInt(1280), rand.nextInt(720));
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		cherry = new Image("textures/food_fruit_cherry.png");
		rand = new Random();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
