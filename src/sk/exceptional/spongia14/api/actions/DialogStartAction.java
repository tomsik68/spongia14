package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class DialogStartAction extends Action {
    private final String dialogId;

    public DialogStartAction(String dialogId) {
	this.dialogId = dialogId;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	state.triggerDialog(dialogId);
    }

}
