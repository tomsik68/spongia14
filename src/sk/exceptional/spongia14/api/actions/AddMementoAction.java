package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class AddMementoAction extends Action {
    private String mementoId;

    public AddMementoAction(String mementoId) {
	this.mementoId = mementoId;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.addMemento(mementoId);
    }

}
