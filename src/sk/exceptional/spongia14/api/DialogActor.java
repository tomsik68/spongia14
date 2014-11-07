package sk.exceptional.spongia14.api;

public class DialogActor {
    private final String id, name;
    private final String portraitResource;

    public DialogActor(String id, String name, String portraitRes) {
	this.id = id;
	this.name = name;
	this.portraitResource = portraitRes;
    }

    public String getId() {
	return id;
    }

    public String getPortraitResource() {
	return portraitResource;
    }

    public String getName() {
	return name;
    }
}
