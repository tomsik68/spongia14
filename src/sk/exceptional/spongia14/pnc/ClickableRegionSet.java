package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;
import java.util.HashSet;

import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class ClickableRegionSet {
    private final String backgroundResource;
    private final ArrayList<ClickableRegion> regions = new ArrayList<ClickableRegion>();
    private HashSet<ClickableRegion> toRemove = new HashSet<ClickableRegion>();

    public ClickableRegionSet(String background) {
	backgroundResource = background;
    }

    public void addRegion(ClickableRegion region) {
	regions.add(region);
    }

    public String getBackgroundResource() {
	return backgroundResource;
    }

    public void onClick(Mission mission, MissionState state, int x, int y) {
	for (ClickableRegion region : regions) {
	    if (region.contains(x, y)) {
		region.onClick(mission, state);
		if (region.shouldRemove())
		    toRemove.add(region);
	    }
	}
	regions.removeAll(toRemove);
	toRemove.clear();
    }

    public void renderRegions(Graphics gfx, int mouseX, int mouseY) {
	for (ClickableRegion region : regions) {
	    if (region.contains(mouseX, mouseY)) {
		// TODO: dat najavo, ze mozem s tym nieco robit
		region.getAvailableActions();
	    }
	    region.render(gfx);
	}
    }
}