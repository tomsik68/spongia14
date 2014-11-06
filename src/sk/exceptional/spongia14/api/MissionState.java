package sk.exceptional.spongia14.api;

import java.util.ArrayList;

import sk.exceptional.spongia14.pnc.PlaceChangeListener;

public class MissionState {
    private ArrayList<PlaceChangeListener> placeChangeListeners = new ArrayList<PlaceChangeListener>();
    private ArrayList<DialogTriggerListener> dialogListeners = new ArrayList<DialogTriggerListener>();
    private final Inventory inventory;
    private final Mission mission;

    public MissionState(Mission mission) {
	inventory = new Inventory();
	this.mission = mission;
    }

    public final Inventory getInventory() {
	return inventory;
    }

    public void triggerDialog(String dialogId) {
	Dialog dialog = mission.getDialog(dialogId);
	for (DialogTriggerListener listener : dialogListeners) {
	    listener.onTriggerDialog(dialog);
	}
    }

    final void switchPlace(String newPlaceId) {
	Place newPlace = mission.getPlace(newPlaceId);
	for (PlaceChangeListener listener : placeChangeListeners) {
	    listener.placeSwitched(newPlace);
	}
    }

    public final void addPlaceChangeListener(PlaceChangeListener listener) {
	placeChangeListeners.add(listener);
    }

    public final void addDialogListener(DialogTriggerListener listener) {
	dialogListeners.add(listener);
    }
}
