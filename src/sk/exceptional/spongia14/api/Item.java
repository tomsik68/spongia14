package sk.exceptional.spongia14.api;

public class Item {
    private final String uniqueID;
    private final String itemClass;
    private final String name, description;

    public Item(String uid, String itemClass, String name, String desc) {
	this.uniqueID = uid;
	this.itemClass = itemClass;
	this.name = name;
	this.description = desc;
    }

    public String getUniqueID() {
	return uniqueID;
    }

    public String getItemClass() {
	return itemClass;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }
}
