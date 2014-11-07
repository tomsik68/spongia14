package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import sk.exceptional.spongia14.api.Action;
import sk.exceptional.spongia14.api.Item;

public class ItemClickableRegion extends RectangularClickableRegion {

    private final Image image;
    private final ItemContainer itemContainer;

    public ItemClickableRegion(ItemContainer itemContainer, Image image) {
	super(itemContainer.getX(), itemContainer.getY(), image.getWidth(),
		image.getHeight());
	this.itemContainer = itemContainer;
	this.image = image;
    }

    @Override
    public void render(Graphics gfx) {
	if (!itemContainer.getItem().isVirtual())
	    gfx.drawImage(image, x, y);
    }

    public Item getItem() {
	return itemContainer.getItem();
    }

    @Override
    protected Action getAction() {
	return itemContainer.getActionSet();
    }
}
