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
    private Image domVraha;

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
	domVraha = new Image("res/buildings/in/domVraha.png");
	float c = 0.5f * 0.8f;
	dude = new WalkingHumanBody((int) (100 * c), (int) (150 * c),
		(int) (31 * c), (int) (46 * c), new Image(
			"res/ppl/greenwich/torso.png"),
		new Extremity("head", (int) (233 * c), (int) (308 * c),
			new Image("res/ppl/greenwich/head.png"),
			(int) (117 * c), (int) (308 * c), (int) (100 * c),
			(int) (33 * c), false), new Extremity("leftArm",
			(int) (100 * c), (int) (306 * c), new Image(
				"res/ppl/greenwich/right_arm.png"),
			(int) (40 * c), (int) (20 * c), (int) (70 * c),
			(int) (102 * c), true), new Extremity("rightArm",
			(int) (100 * c), (int) (306 * c), new Image(
				"res/ppl/greenwich/right_arm.png"),
			(int) (40 * c), (int) (20 * c), (int) (210 * c),
			(int) (88 * c), false), new Extremity("leftLeg",
			(int) (148 * c), (int) (318 * c), new Image(
				"res/ppl/greenwich/left_leg.png"),
			(int) (70 * c), (int) (20 * c), (int) (34 * c),
			(int) (265 * c), true), new Extremity("rightLeg",
			(int) (128 * c), (int) (329 * c), new Image(
				"res/ppl/greenwich/right_leg.png"),
			(int) (70 * c), (int) (20 * c), (int) (122 * c),
			(int) (275 * c), true));
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
	domVraha.draw();
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
