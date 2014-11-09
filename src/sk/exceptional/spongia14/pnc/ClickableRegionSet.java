package sk.exceptional.spongia14.pnc;

import java.util.ArrayList;
import java.util.HashSet;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class ClickableRegionSet {
    private final String backgroundResource;
    private final ArrayList<ClickableRegion> regions = new ArrayList<ClickableRegion>();
    private HashSet<ClickableRegion> toRemove = new HashSet<ClickableRegion>();
    private static int r;
    private final boolean renderPlayer;

    public ClickableRegionSet(String background, boolean renderPlayer) {
	backgroundResource = background;
	this.renderPlayer = renderPlayer;
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
	    region.render(gfx);
	    if (region.contains(mouseX, mouseY)) {
		if (region instanceof RectangularClickableRegion) {
		    RectangularClickableRegion rect = (RectangularClickableRegion) region;
		    int x = rect.getX();
		    int y = rect.getY();
		    int w = Math.min(rect.getWidth(), rect.getHeight());
		    int h = w;
		    if (rect.getWidth() > rect.getHeight()) {
			x += rect.getWidth() / 2 - w / 2;
		    } else if (rect.getHeight() > rect.getWidth()) {
			y += rect.getHeight() / 2 - h / 2;
		    }
		    gfx.setColor(Color.magenta);
		    gfx.setLineWidth(3);
		    gfx.drawOval(x - r / 2, y - r / 2, w + r, h + r);
		    gfx.setLineWidth(1);
		}
	    }
	}
	++r;
	if (r == 50)
	    r = 0;
    }

    public boolean isRenderPlayer() {
	return renderPlayer;
    }
}