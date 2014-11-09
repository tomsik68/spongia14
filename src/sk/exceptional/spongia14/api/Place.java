package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sk.exceptional.spongia14.pnc.ItemContainer;
import sk.exceptional.spongia14.pnc.PersonContainer;

public class Place {
    private final String id, backgroundImage;
    private final boolean inside;
    private ArrayList<ItemContainer> items = new ArrayList<ItemContainer>();
    private ArrayList<PersonContainer> people = new ArrayList<PersonContainer>();
    private ArrayList<Entrance> entrances = new ArrayList<Entrance>();

    public Place(String id, String bgImage, boolean inside) {
	this.id = id;
	this.backgroundImage = bgImage;
	this.inside = inside;
    }

    public String getId() {
	return id;
    }

    public final void addItem(ItemContainer item) {
	items.add(item);
    }

    public final void addPerson(PersonContainer person) {
	people.add(person);
    }

    public final Collection<ItemContainer> getItems() {
	return Collections.unmodifiableList(items);
    }

    public final Collection<PersonContainer> getPeople() {
	return Collections.unmodifiableList(people);
    }

    public String getBackgroundImage() {
	return backgroundImage;
    }

    public List<Entrance> getEntrances() {
	return entrances;
    }

    public final void addEntrance(Entrance entrance) {
	entrances.add(entrance);
    }

    public boolean isInside() {
	return inside;
    }
}
