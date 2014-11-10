package sk.exceptional.spongia14.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWonState extends BasicGameState {
    public static final int STATE_ID = 2;
    private Image background;

    @Override
    public void init(GameContainer arg0, StateBasedGame game)
	    throws SlickException {
	background = ((SpongiaGame) game).getResources().getImage(
		"gui.obed_zomrel");
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics gfx)
	    throws SlickException {
	gfx.drawImage(background, 0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame arg1, int arg2)
	    throws SlickException {
	if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
	    gc.exit();
	}
    }

    @Override
    public int getID() {
	return STATE_ID;
    }

}
