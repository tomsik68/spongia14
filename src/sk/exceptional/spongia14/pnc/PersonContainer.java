package sk.exceptional.spongia14.pnc;

import sk.exceptional.spongia14.api.Person;
import sk.exceptional.spongia14.api.PersonState;

public class PersonContainer {
    private final Person person;
    private PersonState currentState;
    private int x, y;

    public PersonContainer(Person person, String defState, int x, int y) {
	this.person = person;
	this.setX(x);
	this.setY(y);
	currentState = person.getState(defState);
    }

    public Person getPerson() {
	return person;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getWidth() {
	return 90;
    }

    public int getHeight() {
	return 90;
    }

    public PersonState getState() {
	return currentState;
    }

    public void setState(String state) {
	this.currentState = person.getState(state);
    }
}
