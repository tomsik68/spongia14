package sk.exceptional.spongia14.api;

public class Item {
    private final String resourceImage;
    private final String uniqueID;
    private final String name, description;
    private final boolean virtual;

    public Item(String uid, String name, String desc, String imgResourceId) {
	this(uid, name, desc, imgResourceId, false);
    }

    public Item(String uid, String name, String desc, String imgResourceId,
	    boolean virtual) {
	this.uniqueID = uid;
	this.name = name;
	this.description = desc;
	this.resourceImage = imgResourceId;
	this.virtual = virtual;
    }

    public String getUniqueID() {
	return uniqueID;
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

    public boolean isVirtual() {
	return virtual;
    }
}
