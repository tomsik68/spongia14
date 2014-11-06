package sk.exceptional.spongia14.api;

public class SwitchPlaceAction extends Action {
    private final String newPlaceId;
    private String cantEnterText;

    public SwitchPlaceAction(String newPlace) {
	this.newPlaceId = newPlace;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.switchPlace(newPlaceId, cantEnterText);
    }

    public String getCantEnterText() {
	return cantEnterText;
    }

    public void setCantEnterText(String s) {
	cantEnterText = s;
    }

}
