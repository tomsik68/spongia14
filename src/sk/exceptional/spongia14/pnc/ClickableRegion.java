package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.actions.Action;
import sk.exceptional.spongia14.api.actions.ActionSet;

public abstract class ClickableRegion {
    private boolean remove = false;
    private String mouseTooltip;

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public final boolean shouldRemove() {
	return remove;
    }

    void onClick(Mission mission, MissionState state) {
	Action action = getAction();
	action.execute(mission, state);
	if (action instanceof ActionSet) {
	    ActionSet set = (ActionSet) action;
	    remove |= set.isRemove();
	}
    }

    protected abstract Action getAction();

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
