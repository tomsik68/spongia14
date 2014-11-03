package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sk.exceptional.spongia14.api.ClickAction;
import sk.exceptional.spongia14.api.Item;

public class ItemContainer {
    private final List<ClickAction> clickActions;
    private final Item item;
    private final int x, y;

    public ItemContainer(Item item, int x, int y) {
	this.item = item;
	this.x = x;
	this.y = y;
	clickActions = new ArrayList<ClickAction>();
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public Item getItem() {
	return item;
    }

    public final void addActions(ClickAction action) {
	clickActions.add(action);
    }

    final Collection<ClickAction> getActions() {
	return Collections.unmodifiableList(clickActions);
    }

}
