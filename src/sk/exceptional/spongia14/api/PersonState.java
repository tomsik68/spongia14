package sk.exceptional.spongia14.api;

public class PersonState {
    private final String id;
    private ActionSet action;

    public PersonState(String id) {
	this.id = id;
	action = new ActionSet();
    }

    public String getId() {
	return id;
    }

    public ActionSet getAction() {
	return action;
    }

    public void setAction(ActionSet action) {
	this.action = action;
    }
}
