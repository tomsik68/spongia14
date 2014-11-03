package sk.exceptional.spongia14.api;

import java.util.ArrayList;

import sk.exceptional.spongia14.pnc.PlaceChangeListener;

public final class Mission {
    private ArrayList<PlaceChangeListener> placeChangeListeners = new ArrayList<PlaceChangeListener>();
    private Town town;

    public Mission() {
    }

    public final Town getTown() {
	return town;
    }

    public final void setTown(Town town) {
	this.town = town;
    }

    public final void addPlaceChangeListener(PlaceChangeListener listener) {
	placeChangeListeners.add(listener);
    }

    final void switchPlace(String newPlaceId) {
	Place newPlace = town.getPlace(newPlaceId);
	for (PlaceChangeListener listener : placeChangeListeners) {
	    listener.placeSwitched(newPlace);
	}
    }
}
