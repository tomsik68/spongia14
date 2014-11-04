package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Action;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public abstract class ClickableRegion {
    protected final ArrayList<Action> actions = new ArrayList<Action>();

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public abstract boolean shouldRemove();

    public void addAction(Action action) {
	actions.add(action);
    }

    void onClick(Mission mission, MissionState state) {
	for (Action action : actions) {
	    action.execute(mission, state);
	}
    }

    public void render(Graphics gfx) {

    }
}
