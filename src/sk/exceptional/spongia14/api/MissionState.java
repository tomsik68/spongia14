package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 
public class MissionState {
    private List<Item> inventory = new ArrayList<Item>();

    public MissionState() {
    }

    public final List<Item> getInventory() {
	return Collections.unmodifiableList(inventory);
    }

    public void putItemInInventory(Item item) {
	inventory.add(item);
    }
}
