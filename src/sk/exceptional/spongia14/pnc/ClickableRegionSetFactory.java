package sk.exceptional.spongia14.pnc;

import java.util.HashMap;

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
    // toto sa pouziva na ukladanie stavu
    private HashMap<String, ClickableRegionSet> cache = new HashMap<String, ClickableRegionSet>();

    public ClickableRegionSetFactory(Resources res) {
	resources = res;
    }

    public ClickableRegionSet createCRS(Mission mission, Place place) {
	ClickableRegionSet result;
	if (!cache.containsKey(place.getId())) {
	    result = new ClickableRegionSet(place.getBackgroundImage());
	    // TODO: add items
	    for (ItemContainer itemContainer : place.getItems()) {
		ClickableRegion cr = new ItemClickableRegion(itemContainer,
			resources.getImage(itemContainer.getItem()
				.getResourceImage()));
		result.addRegion(cr);
	    }
	    // TODO: add people

	    for (Entrance entrance : place.getEntrances()) {
		EntranceClickableRegion cr = new EntranceClickableRegion(
			entrance);
		result.addRegion(cr);
	    }
	    cache.put(place.getId(), result);
	} else {
	    result = cache.get(place.getId());
	}
	return result;
    }
}
