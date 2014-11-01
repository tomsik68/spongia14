package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Place {
    private final String id;
    private ArrayList<ItemContainer> items = new ArrayList<ItemContainer>();
    private ArrayList<PersonContainer> people = new ArrayList<PersonContainer>();

    public Place(String id) {
	this.id = id;
    }

    public String getId() {
	return id;
    }

    final void addItem(ItemContainer item) {
	items.add(item);
    }

    final void addPerson(PersonContainer person) {
	people.add(person);
    }

    public final Collection<ItemContainer> getItems() {
	return Collections.unmodifiableList(items);
    }

    public final Collection<PersonContainer> getPeople() {
	return Collections.unmodifiableList(people);
    }
}
