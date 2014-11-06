package sk.exceptional.spongia14.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class MementoGui {
    private Image memento;
    private final Image paperBackground;

    public MementoGui(Image background) {
	paperBackground = background;
    }

    public void showMemento(Image res) {
	this.memento = res;
	if (memento == null)
	    throw new NullPointerException("Null memento!");
    }

    public void render(Graphics gfx) {
	gfx.drawImage(paperBackground, 0, 0);
	gfx.drawImage(memento, 0, 120);
    }

    public void update(GameContainer gc) {

    }
}
