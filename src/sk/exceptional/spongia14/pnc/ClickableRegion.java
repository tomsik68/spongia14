package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Action;
import sk.exceptional.spongia14.api.ActionSet;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public abstract class ClickableRegion {
    private boolean remove = false;
    private String mouseTooltip;
    protected Action action;

    public ClickableRegion() {
    }

    public abstract boolean contains(int x, int y);

    public final boolean shouldRemove() {
	return remove;
    }

    void onClick(Mission mission, MissionState state) {
	action.execute(mission, state);
	if (action instanceof ActionSet) {
	    ActionSet set = (ActionSet) action;
	    remove |= set.isRemove();
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

    public void addAction(Action action) {
	this.action = action;
    }
}
