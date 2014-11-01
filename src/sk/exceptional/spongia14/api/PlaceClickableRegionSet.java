package sk.exceptional.spongia14.api;

public class PlaceClickableRegionSet extends ClickableRegionSet {
    private final Place place;

    public PlaceClickableRegionSet(Place place, String background) {
	super(background);
	this.place = place;
    }

    public Place getPlace() {
	return place;
    }

}
