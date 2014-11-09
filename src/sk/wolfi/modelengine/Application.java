package sk.wolfi.modelengine;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Application extends BasicGame {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 800;
    public static final int FPS = 60;
    public static final double DELTA_TIME = 1d / FPS;

    private WalkingHumanBody dude;

    public Application() {
	super("Model");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
	/*
	 * leftArm = new Extremity(width, height,
	 * 
	 * dude = new HumanBody( new Extremity(...), ... );
	 */

	dude = new WalkingHumanBody(100, 150, 244, 367, new Image(
		"res/ppl/greenwich/torso.png"),
		new Extremity("head", 233, 308, new Image("res/ppl/greenwich/head.png"), 117, 308, 100, 33, false),
		new Extremity("leftArm", 100, 306, new Image("res/ppl/greenwich/right_arm.png"), 40, 20,70, 102, true),
		new Extremity("rightArm", 100, 306, new Image("res/ppl/greenwich/right_arm.png"), 40, 20, 210, 88, false),
		new Extremity("leftLeg", 148, 318, new Image("res/ppl/greenwich/left_leg.png"), 70, 20, 34,265, true),
		new Extremity("rightLeg", 128, 329, new Image("res/ppl/greenwich/right_leg.png"), 70, 20, 122, 275, true)
	);
    }

    @Override
    public void update(GameContainer container, int delta)
	    throws SlickException {
	Input input = container.getInput();
	int a = 5;
	if (input.isKeyDown(Input.KEY_UP)) {
	    dude.moveBy(0, -a);
	}

	if (input.isKeyDown(Input.KEY_DOWN)) {
	    dude.moveBy(0, a);
	}

	if (input.isKeyDown(Input.KEY_LEFT)) {
	    dude.moveBy(-a, 0);
	}

	if (input.isKeyDown(Input.KEY_RIGHT)) {
	    dude.moveBy(a, 0);
	}
	if (input.isKeyPressed(Input.KEY_W)) {
	    dude.setWalking(!dude.isWalking());
	}
	/*
	 * if(input.isKeyDown(Input.KEY_L)) { dude.get("leftArm").rotateBy(2); }
	 * 
	 * if(input.isKeyDown(Input.KEY_R)) { dude.get("rightArm").rotateBy(2);
	 * }
	 * 
	 * if(input.isKeyDown(Input.KEY_K)) { dude.get("leftLeg").rotateBy(2); }
	 * 
	 * if(input.isKeyDown(Input.KEY_E)) { dude.get("rightLeg").rotateBy(2);
	 * }
	 * 
	 * if(input.isKeyDown(Input.KEY_H)) { dude.get("head").rotateBy(2); }
	 */
	dude.update();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
	dude.draw();
	Display.sync(FPS);
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
