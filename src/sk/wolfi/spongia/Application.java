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
	private Extremity leftArm, rightArm;
	
	public Application() {
		super("Model");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		body = new Body(100, 100, 50, 50); // x, y, width, height
		leftArm = new Extremity(50, 20); // width, height
		rightArm = new Extremity(50, 20);
		leftArm.setJoint(50, 10); // xOffset, yOffset
		rightArm.setJoint(0, 10);
		body.attach(leftArm, 0, 20); // object, offsetX, offsetY
		body.attach(rightArm, 50, 20);
		
		
		
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)) {
			body.find(leftArm).rotateBy(1);
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			body.find(rightArm).rotateBy(1);
		}
		
		if(input.isKeyDown(Input.KEY_UP)) {
			body.update();
			body.getShape().setLocation(body.getShape().getX() + 1, body.getShape().getY());
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		g.draw(body.getShape());
		//g.drawOval(jointX - 2, jointY - 2, 5, 5);
		//g.drawRect(extremityX, extremityY, extremityWidth, extremityHeight);
		g.draw(body.find(leftArm).getShape());
		g.draw(body.find(rightArm).getShape());
	
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
