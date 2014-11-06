package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import sk.exceptional.spongia14.api.Item;

public class ItemClickableRegion extends RectangularClickableRegion {

    private final Image image;
    private final Item item;

    public ItemClickableRegion(ItemContainer itemContainer, Image image) {
	super(itemContainer.getX(), itemContainer.getY(), image.getWidth(),
		image.getHeight());
	this.item = itemContainer.getItem();
	actions.addAll(itemContainer.getActions());
	this.image = image;
    }

    @Override
    public void render(Graphics gfx) {
	if (!item.isVirtual())
	    gfx.drawImage(image, x, y);
    }

    public Item getItem() {
	return item;
    }
}
