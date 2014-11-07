package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<Item>();

    public Inventory() {

    }

    public Collection<Item> getItems() {
	return Collections.unmodifiableList(items);
    }

    public void addItem(Item item) {
	items.add(item);
    }

    public void deleteItem(Item item) {
	items.remove(item);
    }

    public boolean hasItem(Item item) {
	return items.contains(item);
    }

    public boolean hasItem(String itemId) {
	for (Item item : items) {
	    if (item.getUniqueID().equalsIgnoreCase(itemId)) {
		return true;
	    }
	}
	return false;
    }
}
