package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Dialog {
    private final String id;
    private final ArrayList<Replica> replicas = new ArrayList<Replica>();

    public Dialog(String id) {
	this.id = id;
    }

    public final String getId() {
	return id;
    }

    public List<Replica> getReplicas() {
	return Collections.unmodifiableList(replicas);
    }
}
