package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.ClickAction;
import sk.exceptional.spongia14.api.Mission;

public abstract class ClickableRegion {
    protected final ArrayList<ClickAction> actions = new ArrayList<ClickAction>();

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public abstract boolean shouldRemove();

    public void addAction(ClickAction action) {
	actions.add(action);
    }

    void onClick(Mission mission) {
	for (ClickAction action : actions) {
	    action.execute(mission, this);
	}
    }

    public void render(Graphics gfx) {
	
    }
}
