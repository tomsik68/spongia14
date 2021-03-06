package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.actions.Action;

public class PersonClickableRegion extends RectangularClickableRegion {
    private final PersonContainer personContainer;

    public PersonClickableRegion(PersonContainer personContainer) {
	this.personContainer = personContainer;
    }

    @Override
    public void render(Graphics gfx) {
	gfx.setColor(Color.black);
	personContainer.getModel().render(gfx, getX(), getY());
    }

    @Override
    protected Action getAction() {
	return personContainer.getState().getAction();
    }

    @Override
    public int getX() {
	return personContainer.getX();
    }

    @Override
    public int getY() {
	return personContainer.getY();
    }

    @Override
    public int getWidth() {
	return personContainer.getWidth();
    }

    @Override
    public int getHeight() {
	return personContainer.getHeight();
    }

}
