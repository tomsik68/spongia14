package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ItemClickableRegion extends RectangularClickableRegion {

    private final Image image;

    public ItemClickableRegion(ItemContainer item, Image image) {
	super(item.getX(), item.getY(), 64, 64);
	actions.addAll(item.getActions());
	this.image = image;
    }

    @Override
    public void render(Graphics gfx) {
	gfx.drawImage(image, x, y);
    }
}
