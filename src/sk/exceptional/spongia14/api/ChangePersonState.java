package sk.exceptional.spongia14.api;

public class ChangePersonState extends Action {
    private final String person, personStateId;

    public ChangePersonState(String person, String state) {
	this.person = person;
	this.personStateId = state;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.changePersonState(person, personStateId);
    }

}
