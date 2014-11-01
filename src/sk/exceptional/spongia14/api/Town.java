package sk.exceptional.spongia14.api;

import java.util.HashMap;

public class Town {
	private HashMap<String, Place> places = new HashMap<String, Place>();
	/**
	 * Ktory {@link Place} z tohto mesta je mapa
	 */
	private final String mapPlaceId;

	public Town(String mapPlaceId) {
		this.mapPlaceId = mapPlaceId;
	}

	final void addPlace(Place place) {
		places.put(place.getId(), place);
	}

	public final Place getPlace(String id) {
		Place result = places.get(id);
		if (result == null)
			throw new NullPointerException("Place `" + id + "` not found!");
		return result;
	}

	public Place getMap() {
		return getPlace(mapPlaceId);
	}
}
