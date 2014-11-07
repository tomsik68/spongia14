package sk.exceptional.spongia14.api;

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
