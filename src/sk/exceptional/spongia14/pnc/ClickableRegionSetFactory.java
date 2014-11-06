package sk.exceptional.spongia14.pnc;

import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.SwitchPlaceAction;
import sk.tomsik68.resourceslib.Resources;

/**
 * ClickableRegionSetContainerFactory
 *
 */
public class ClickableRegionSetFactory {
    private final Resources resources;

    public ClickableRegionSetFactory(Resources res) {
	resources = res;
    }

    public ClickableRegionSet createCRS(Mission mission, Place place) {
	ClickableRegionSet result = new ClickableRegionSet(
		place.getBackgroundImage());
	// TODO: add items
	for (ItemContainer itemContainer : place.getItems()) {
	    ClickableRegion cr = new ItemClickableRegion(itemContainer,
		    resources.getImage(itemContainer.getItem()
			    .getResourceImage()));
	    result.addRegion(cr);
	}
	// TODO: add people

	for (Entrance entrance : place.getEntrances()) {
	    ClickableRegion cr = new RectangularClickableRegion(entrance.x,
		    entrance.y, entrance.w, entrance.h);
	    cr.addAction(new SwitchPlaceAction(entrance.destination));
	    result.addRegion(cr);
	}
	return result;
    }
}
