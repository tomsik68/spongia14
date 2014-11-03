package sk.exceptional.spongia14.api;

public class Item {
    private final String resourceImage;
    private final String uniqueID;
    private final String itemClass;
    private final String name, description;

    public Item(String uid, String itemClass, String name, String desc,
	    String imgResourceId) {
	this.uniqueID = uid;
	this.itemClass = itemClass;
	this.name = name;
	this.description = desc;
	this.resourceImage = imgResourceId;
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

    public String getResourceImage() {
	return resourceImage;
    }
}
