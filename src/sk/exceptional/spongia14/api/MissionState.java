package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.HashSet;

import sk.exceptional.spongia14.pnc.PlaceChangeListener;

public class MissionState {
    private ArrayList<PlaceChangeListener> placeChangeListeners = new ArrayList<PlaceChangeListener>();
    private ArrayList<DialogTriggerListener> dialogListeners = new ArrayList<DialogTriggerListener>();
    private final Inventory inventory;
    private final Mission mission;
    private final HashSet<String> allowedPlaces;

    public MissionState(Mission mission) {
	inventory = new Inventory();
	this.mission = mission;
	allowedPlaces = new HashSet<String>();
    }

    public final Inventory getInventory() {
	return inventory;
    }

    public void triggerDialog(String dialogId) {
	Dialog dialog = mission.getDialog(dialogId);
	doTriggerDialog(dialog);
    }

    private void doTriggerDialog(Dialog dialog) {
	for (DialogTriggerListener listener : dialogListeners) {
	    listener.onTriggerDialog(dialog);
	}
    }

    final void switchPlace(String newPlaceId, String cantEnterText) {
	if (allowedPlaces.contains(newPlaceId)) {
	    Place newPlace = mission.getPlace(newPlaceId);
	    for (PlaceChangeListener listener : placeChangeListeners) {
		listener.placeSwitched(newPlace);
	    }
	} else {
	    if (cantEnterText != null && cantEnterText.length() > 0) {
		Dialog virtualDialog = new Dialog("cant-enter-this");
		try {
		    virtualDialog.addActor(new DialogActor("self",
			    "Michael Greenwich", "portrait.greenwich"));
		} catch (Exception ignored) {
		}
		virtualDialog.addReplica(new Replica(cantEnterText, "self"));
		doTriggerDialog(virtualDialog);
	    }
	}
    }

    public final void addPlaceChangeListener(PlaceChangeListener listener) {
	placeChangeListeners.add(listener);
    }

    public final void addDialogListener(DialogTriggerListener listener) {
	dialogListeners.add(listener);
    }

    public final void allowEntrance(String placeID) {
	allowedPlaces.add(placeID);
    }
}
