package sk.exceptional.spongia14.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import sk.tomsik68.resourceslib.Resources;

public class GuiTopPanel {
    private Image background;

    public GuiTopPanel(Resources res) {
	background = res.getImage("gui.top_panel");
    }

    public void render(Graphics gfx) {
	gfx.drawImage(background, 0, 0);
    }

    public void update(GameContainer gc) {

    }
}
