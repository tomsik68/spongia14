package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NameAlreadyBoundException;

public final class Dialog {
    private final String id;
    private final ArrayList<Replica> replicas = new ArrayList<Replica>();
    private final Registry<DialogActor> actors = new Registry<DialogActor>();

    public Dialog(String id) {
	this.id = id;
    }

    public final String getId() {
	return id;
    }

    public List<Replica> getReplicas() {
	return Collections.unmodifiableList(replicas);
    }

    public void addActor(DialogActor actor) throws NameAlreadyBoundException {
	actors.register(actor.getId(), actor);
    }

    public final DialogActor getActor(String id) {
	return actors.get(id);
    }

    public int getReplicaCount() {
	return replicas.size();
    }

    public void addReplica(Replica replica) {
	replicas.add(replica);
    }
}
