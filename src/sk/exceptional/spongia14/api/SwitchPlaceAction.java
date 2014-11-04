package sk.exceptional.spongia14.api;


public class SwitchPlaceAction extends Action {
    private final String newPlaceId;

    public SwitchPlaceAction(String newPlace) {
	this.newPlaceId = newPlace;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	mission.switchPlace(newPlaceId);
    }

}
