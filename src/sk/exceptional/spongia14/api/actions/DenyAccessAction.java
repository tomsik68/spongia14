package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class DenyAccessAction extends Action {
    private final String placeId;

    public DenyAccessAction(String place) {
	this.placeId = place;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.denyAccess(placeId);
    }

}
