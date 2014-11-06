package sk.exceptional.spongia14.api;

public class AllowAccessAction extends Action {

    private final String placeId;

    public AllowAccessAction(String place) {
	placeId = place;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.allowEntrance(placeId);
    }
}
