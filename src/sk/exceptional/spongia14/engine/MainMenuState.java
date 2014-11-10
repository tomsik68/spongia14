package sk.exceptional.spongia14.engine;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sk.tomsik68.resourceslib.Resources;

public class MainMenuState extends BasicGameState {

    public static final int STATE_ID = 0;
    private Image image;
    private boolean mouseOver = false;

    public MainMenuState() {

    }

    @Override
    public void init(GameContainer gc, StateBasedGame game)
	    throws SlickException {
	image = ((SpongiaGame) game).getResources().getImage("gui.main_menu");
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics gfx)
	    throws SlickException {
	gfx.drawImage(image, 0, 0);
	if (mouseOver) {
	    gfx.setLineWidth(4);
	    gfx.setColor(Color.magenta);
	    gfx.drawRoundRect(332, 333, 140, 100, 20);
	    gfx.setLineWidth(1);
	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int arg2)
	    throws SlickException {
	// 332,333
	Input input = gc.getInput();
	int mouseX = input.getMouseX();
	int mouseY = input.getMouseY();
	if (mouseX >= 332 && mouseX <= 470 && mouseY >= 333 && mouseY <= 430) {
	    mouseOver = true;
	    if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		game.enterState(InRegionSetGameState.STATE_ID);
	    }
	} else
	    mouseOver = false;

    }

    @Override
    public int getID() {
	return STATE_ID;
    }

}
