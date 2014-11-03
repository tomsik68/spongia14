package sk.exceptional.spongia14.api;

import java.util.ArrayList;

import sk.exceptional.spongia14.pnc.PlaceChangeListener;

public class Mission {
    private ArrayList<PlaceChangeListener> placeChangeListeners = new ArrayList<PlaceChangeListener>();
    private Town town;

    public Town getTown() {
	return town;
    }

    public void setTown(Town town) {
	this.town = town;
    }

    public final void addPlaceChangeListener(PlaceChangeListener listener) {
	placeChangeListeners.add(listener);
    }

    public void switchPlace(String newPlaceId) {
	Place newPlace = town.getPlace(newPlaceId);
	for (PlaceChangeListener listener : placeChangeListeners) {
	    listener.placeSwitched(newPlace);
	}
    }
}
