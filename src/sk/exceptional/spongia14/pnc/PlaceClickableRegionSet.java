package sk.exceptional.spongia14.pnc;

import sk.exceptional.spongia14.api.Place;

public class PlaceClickableRegionSet extends ClickableRegionSet {
    private final Place place;

    public PlaceClickableRegionSet(Place place, String background) {
	super(background, place.isInside());
	this.place = place;
    }

    public Place getPlace() {
	return place;
    }

}
