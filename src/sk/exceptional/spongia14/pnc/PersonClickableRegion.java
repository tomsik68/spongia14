package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Action;

public class PersonClickableRegion extends RectangularClickableRegion {
    private final PersonContainer personContainer;

    public PersonClickableRegion(PersonContainer personContainer) {
	this.personContainer = personContainer;
    }

    @Override
    public void render(Graphics gfx) {
	gfx.setColor(Color.black);
	gfx.fillRect(personContainer.getX(), personContainer.getY(),
		personContainer.getWidth(), personContainer.getHeight());
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
