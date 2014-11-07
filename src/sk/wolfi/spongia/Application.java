package sk.wolfi.spongia;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class Application extends BasicGame {
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 500;
	
	private Body body;
	private Extremity arm, leg;
	
	public Application() {
		super("Model");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		body = new Body(100, 100, 50, 50); // x, y, width, height
		
		arm = new Extremity(50, 20); // width, height
		arm.setJoint(50, 10); // xOffset, yOffset

		body.attach(arm, 0, 20, "leftArm");
		body.attach(arm, 50, 20, "rightArm");

		
		leg = new Extremity(20, 50);
		leg.setJoint(10, 0);

		body.attach(leg, 10, 50, "leftLeg");
		body.attach(leg, 40, 50, "rightLeg");
		
		
		
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)) {
			body.moveBy(-1, 0);
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			body.moveBy(1, 0);
		}
		
		if(input.isKeyDown(Input.KEY_UP)) {
			body.moveBy(0, -1);
		}
		
		if(input.isKeyDown(Input.KEY_DOWN)) {
			body.moveBy(0, 1);
		}
		
		if(input.isKeyDown(Input.KEY_R)) {
			body.find("rightArm").rotateBy(1);
			body.find("leftLeg").rotateBy(1);
		}
		
		if(input.isKeyDown(Input.KEY_L)) {
			body.find("leftArm").rotateBy(1);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		body.draw(g);
		g.drawString(new String("/\\, \\/, <-, ->, L, R"), 20, 30);
		
	
	}

	 public static void main(String[] args) {
	    try {
			AppGameContainer app = new AppGameContainer(new Application());
			app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			app.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

}
