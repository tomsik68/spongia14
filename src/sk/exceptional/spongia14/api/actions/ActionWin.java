package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class ActionWin extends Action {

    public ActionWin() {

    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.win();
    }

}
