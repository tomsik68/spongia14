package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public abstract class CancelAction extends Action {

    public CancelAction() {

    }

    @Override
    public void execute(Mission mission, MissionState state) {
	
    }

    public abstract boolean shouldCancel(MissionState state);

}
