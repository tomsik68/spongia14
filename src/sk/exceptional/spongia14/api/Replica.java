package sk.exceptional.spongia14.api;

public final class Replica {
    private final String text, actorId;

    public Replica(String text, String actorId) {
	this.text = text;
	this.actorId = actorId;
    }

    public String getText() {
	return text;
    }

    public String getActorId() {
	return actorId;
    }
}
