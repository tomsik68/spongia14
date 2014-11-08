package sk.exceptional.spongia14.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SpongiaGame extends StateBasedGame {

    public SpongiaGame() {
	super("Exceptional Srandosak");
    }

    @Override
    public void initStatesList(GameContainer arg0) throws SlickException {
	arg0.setShowFPS(false);
	addState(new InRegionSetGameState());
    }

}
