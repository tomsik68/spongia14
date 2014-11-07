package sk.exceptional.spongia14.api;

import javax.naming.NameAlreadyBoundException;

public class Person {
    private final String uniqueID;
    private String name;
    private final Registry<PersonState> states;

    public Person(String uid) {
	this.uniqueID = uid;
	states = new Registry<PersonState>();
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

    public final void addState(PersonState state)
	    throws NameAlreadyBoundException {
	states.register(state.getId(), state);
    }

    public PersonState getState(String state) {
	return states.get(state);
    }
}
