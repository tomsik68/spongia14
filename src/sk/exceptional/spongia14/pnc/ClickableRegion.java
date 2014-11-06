package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Action;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.RemoveAction;

public abstract class ClickableRegion {
    protected final ArrayList<Action> actions = new ArrayList<Action>();
    private boolean remove = false;
    private String mouseTooltip;

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public final boolean shouldRemove() {
	return remove;
    }

    public void addAction(Action action) {
	actions.add(action);
    }

    void onClick(Mission mission, MissionState state) {
	for (Action action : actions) {
	    action.execute(mission, state);
	    if (action instanceof RemoveAction) {
		remove = true;
	    }
	}

    }

    public void render(Graphics gfx) {

    }

    public String[] getAvailableActions() {
	return new String[] {};
    }

    public String getMouseTooltip() {
	return mouseTooltip;
    }

    public void setMouseTooltip(String mouseTooltip) {
	this.mouseTooltip = mouseTooltip;
    }
}
