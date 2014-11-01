package sk.exceptional.spongia14.api;

import java.util.ArrayList;

abstract class ClickableRegion {
    private ArrayList<ClickAction> actions = new ArrayList<ClickAction>();

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public abstract boolean shouldRemove();

    void onClick(Mission mission) {
	for (ClickAction action : actions) {
	    action.execute(mission, this);
	}
    }
}
