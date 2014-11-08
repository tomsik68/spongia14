package sk.wolfi.spongia;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class Application extends BasicGame {
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 800;
	
	private HumanBody dude;
	
	public Application() {
		super("Model");
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		/*body = new Body(100, 100, 70, 100, "res/torso.png"); 
		
		arm = new Extremity(107, 185, "res/arm.png"); 
		arm.setJoint(50, 10);
		
		
		body.attach(arm, 20, 80, "leftArm");
		body.attach(arm, 120, 80, "rightArm");*/
		
		
		
		dude = new HumanBody(100, 150, 244, 367, "res/torso.png",
				new Extremity("head", 233, 308, "res/head.png", 117, 308, 100, 33),
				new Extremity("leftArm", 97, 306, "res/right_arm.png", 40, 20, 34, 102),
				new Extremity("rightArm", 97, 306, "res/right_arm.png", 40, 20, 210, 88),
				new Extremity("leftLeg", 148, 318, "res/left_leg.png", 70, 20, 34, 315),
				new Extremity("rightleg", 128, 329, "res/right_leg.png", 70, 20, 122, 327)
		);
	

		/*leg = new Extremity(20, 50);
		leg.setJoint(10, 0);
		
		body.attach(arm, 0, 20, "leftArm");
		body.attach(arm, 50, 20, "rightArm");
		

		body.attach(leg, 10, 50, "leftLeg");
		body.attach(leg, 40, 50, "rightLeg");
		
		body.find("leftLeg").extend(leg, 10, 50, "leftLegExtention");
		body.find("rightLeg").extend(leg, 10, 50, "rightLegExtention");
		
		body.find("leftArm").extend(arm, 0, 10, "leftArmExtention");
		body.find("leftArm").getExtention().rotateBy(-90);
		
		body.find("leftLeg").getExtention().rotateBy(-25);*/
		
		/*
		 * 
		 * HumanBody dude = new HumanBody(x, y, width, height, bodyImgLocation 
		 * 		new Extremity("leftArm", leftArmWidth, leftArmHeight, leftArmImg),
		 * 		new Extremity("rightArm", rightArmWidth, rightArmHeight, rightArmImg),
		 * 		new Extremity(...),
		 * 	... );
		 *
		 * 
		 * dude.leftArm().rotateLeft(30);
		 * dure.rightLeg().rotateRight(20);
		 * dude.get("leftArm").rotate(30);
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)) {
			dude.moveBy(-1, 0);
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			dude.moveBy(1, 0);
		}
		
		if(input.isKeyDown(Input.KEY_UP)) {
			dude.moveBy(0, -1);
		}
		
		if(input.isKeyDown(Input.KEY_DOWN)) {
			dude.moveBy(0, 1);
		}
		
		if(input.isKeyDown(Input.KEY_R)) {
			dude.find("rightArm").rotateBy(1);
			dude.find("leftLeg").rotateBy(1);
		}
		
		if(input.isKeyDown(Input.KEY_L)) {
			dude.get("leftArm").rotateBy(1);
		}
		/*
		dude.find("leftArm").followMouse(input);
		dude.find("rightArm").followMouse(input);
		*/
		
		
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		dude.draw();
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
