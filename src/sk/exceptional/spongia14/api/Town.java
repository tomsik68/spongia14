package sk.exceptional.spongia14.api;

import javax.naming.NameAlreadyBoundException;

public class Town extends Registry<Place> {
    /**
     * Ktory {@link Place} z tohto mesta je mapa
     */
    private final String mapPlaceId;

    public Town(String mapPlaceId) {
	this.mapPlaceId = mapPlaceId;
    }

    public Place getMap() {
	return get(mapPlaceId);
    }

    // TODO: vymazat
    public void addPlace(Place place) {
	try {
	    register(place.getId(), place);
	} catch (NameAlreadyBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
