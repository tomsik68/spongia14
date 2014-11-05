package sk.exceptional.spongia14.api;

import javax.naming.NameAlreadyBoundException;

public final class Mission {
    private final Registry<Dialog> dialogs = new Registry<Dialog>();
    private final Registry<Item> items = new Registry<Item>();
    private final Registry<Person> people = new Registry<Person>();

    private Town town;

    public Mission() {
    }

    public final Town getTown() {
	return town;
    }

    public final void setTown(Town town) {
	this.town = town;
    }

    public Dialog getDialog(String id) {
	return dialogs.get(id);
    }

    public Item getItem(String itemId) {
	return items.get(itemId);
    }

    public Place getPlace(String placeId) {
	return town.get(placeId);
    }

    // TODO: vymazat
    public void registerItem(Item ivanItem) {
	try {
	    items.register(ivanItem.getUniqueID(), ivanItem);
	} catch (NameAlreadyBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
