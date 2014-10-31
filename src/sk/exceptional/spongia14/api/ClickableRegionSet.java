package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.HashSet;

public class ClickableRegionSet {
	private final String backgroundResource;
	private final ArrayList<ClickableRegion> regions = new ArrayList<ClickableRegion>();
	private HashSet<ClickableRegion> toRemove = new HashSet<ClickableRegion>();

	public ClickableRegionSet(String background) {
		backgroundResource = background;
	}

	void addRegion(ClickableRegion region) {
		regions.add(region);
	}

	public String getBackgroundResource() {
		return backgroundResource;
	}

	public void onClick(Mission mission, int x, int y) {
		for (ClickableRegion region : regions) {
			if (region.contains(x, y)) {
				region.onClick(mission);
				if (region.shouldRemove())
					toRemove.add(region);
			}
		}
		regions.removeAll(toRemove);
		toRemove.clear();
	}

}
