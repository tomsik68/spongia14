package sk.exceptional.spongia14.engine;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import sk.tomsik68.resourceslib.Resources;

public class MementoGui {
    private final Resources resources;
    private Image memento;
    private int mementoIndex;
    private final Image paperBackground;
    private boolean done;
    private ArrayList<String> mementos = new ArrayList<String>();

    public MementoGui(Image background, Resources resources) {
	paperBackground = background;
	mementoIndex = 0;
	this.resources = resources;
    }

    public void show() {
	setDone(mementos.size() > 0);
	mementoIndex = mementos.size() - 1;
	reloadMemento();
    }

    private void reloadMemento() {
	if (mementoIndex <= -1) {
	    mementoIndex = mementos.size() - 1;
	}
	if (mementoIndex > mementos.size() - 1) {
	    mementoIndex = 0;
	}
	if (mementoIndex != -1)
	    memento = resources.getImage(mementos.get(mementoIndex));
	setDone(mementoIndex == -1);
    }

    public void render(Graphics gfx) {
	gfx.drawImage(paperBackground, 0, 0);
	gfx.drawImage(memento, 0, 0);
    }

    public void update(GameContainer gc) {
	if (gc.getInput().isKeyDown(Input.KEY_ESCAPE)) {
	    setDone(true);
	}
	if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
	    --mementoIndex;
	    reloadMemento();
	}
	if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {
	    ++mementoIndex;
	    reloadMemento();
	}
    }

    public boolean isDone() {
	return done;
    }

    public void setDone(boolean done) {
	this.done = done;
    }

    public void addMemento(String mementoRes) {
	if (!mementos.contains(mementoRes))
	    mementos.add(mementoRes);
    }
}
