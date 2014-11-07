package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Action;

public class PersonClickableRegion extends ClickableRegion {
    private final PersonContainer personContainer;

    public PersonClickableRegion(PersonContainer personContainer) {
	this.personContainer = personContainer;
    }

    @Override
    public boolean contains(int x, int y) {
	return x >= personContainer.getX()
		&& x <= personContainer.getX() + personContainer.getWidth()
		&& y >= personContainer.getY()
		&& y <= personContainer.getY() + personContainer.getHeight();
    }

    @Override
    public void render(Graphics gfx) {
	gfx.fillRect(personContainer.getX(), personContainer.getY(),
		personContainer.getWidth(), personContainer.getHeight());
    }

    @Override
    protected Action getAction() {
	return personContainer.getState().getAction();
    }

}
