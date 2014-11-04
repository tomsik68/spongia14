package sk.exceptional.spongia14.api;

import sk.exceptional.spongia14.pnc.ClickableRegion;

public class SwitchPlaceAction extends ClickAction {
    private final String newPlaceId;

    public SwitchPlaceAction(String newPlace) {
	this.newPlaceId = newPlace;
    }

    @Override
    public void execute(Mission mission, MissionState state,
	    ClickableRegion clicked) {
	mission.switchPlace(newPlaceId);
    }

}
