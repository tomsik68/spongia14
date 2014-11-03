package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import sk.exceptional.spongia14.pnc.ItemContainer;
import sk.exceptional.spongia14.pnc.PersonContainer;

public class Place {
    private final String id, backgroundImage;
    private ArrayList<ItemContainer> items = new ArrayList<ItemContainer>();
    private ArrayList<PersonContainer> people = new ArrayList<PersonContainer>();

    public Place(String id, String bgImage) {
	this.id = id;
	this.backgroundImage = bgImage;
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
}
