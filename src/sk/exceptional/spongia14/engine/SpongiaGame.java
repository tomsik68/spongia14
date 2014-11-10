package sk.exceptional.spongia14.engine;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import sk.tomsik68.resourceslib.Resources;

public class SpongiaGame extends StateBasedGame {

    private Resources resources;

    public SpongiaGame() {
	super("Jeden den Jasona Grahama");
    }

    @Override
    public void initStatesList(GameContainer arg0) throws SlickException {
	resources = new Resources();
	try {
	    resources.load(new File("res"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	arg0.setShowFPS(false);
	addState(new MainMenuState());
	addState(new InRegionSetGameState());
    }

    public Resources getResources() {
	return resources;
    }

}
