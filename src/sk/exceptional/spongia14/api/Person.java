package sk.exceptional.spongia14.api;

public class Person {
    private final String uniqueID;
    private String name;

    public Person(String uid) {
	this.uniqueID = uid;
    }

    public String getUniqueID() {
	return uniqueID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
