package sk.exceptional.spongia14.api;

import java.util.HashMap;

import javax.naming.NameAlreadyBoundException;

public class Registry<T> {
    private final HashMap<String, T> registered = new HashMap<>();

    public Registry() {

    }

    public final void register(String key, T value)
	    throws NameAlreadyBoundException {
	if (registered.containsKey(key)) {
	    throw new NameAlreadyBoundException("Kluc " + key
		    + " nie je unikatny!!!");
	}
	registered.put(key, value);
    }

    public final T get(String key) {
	T result = registered.get(key);
	if (result == null) {
	    throw new NullPointerException("Kluc " + key + " neexistuje!");
	}
	return result;
    }
}
