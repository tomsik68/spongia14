package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sk.exceptional.spongia14.api.Action;
import sk.exceptional.spongia14.api.ActionSet;
import sk.exceptional.spongia14.api.Item;

public class ItemContainer {
    private final ActionSet actionSet;
    private final Item item;
    private final int x, y;

    public ItemContainer(Item item, int x, int y) {
	this.item = item;
	this.x = x;
	this.y = y;
	actionSet = new ActionSet();
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

    public final void addAction(Action action) {
	actionSet.addAction(action);
    }

    final ActionSet getActionSet() {
	return actionSet;
    }

}
