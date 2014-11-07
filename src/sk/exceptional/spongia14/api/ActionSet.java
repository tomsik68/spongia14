package sk.exceptional.spongia14.api;

import java.util.ArrayList;
import java.util.Collection;

public class ActionSet extends Action {
    private final ArrayList<Action> actions = new ArrayList<Action>();
    private boolean remove = false;

    public ActionSet() {

    }

    public void addAction(Action action) {
	actions.add(action);
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	for (Action action : actions) {
	    action.execute(mission, state);
	    if (action instanceof CancelAction) {
		CancelAction cancelAction = (CancelAction) action;
		if (cancelAction.meetsConditions(state)) {
		    break;
		}
	    } else if (action instanceof RemoveAction) {
		remove = true;
	    } else if (action instanceof ActionSet) {
		ActionSet as = (ActionSet) action;
		remove |= as.isRemove();
	    }
	}
    }

    public boolean isRemove() {
	return remove;
    }

    public void setRemove(boolean remove) {
	this.remove = remove;
    }

    public void addAll(Collection<Action> actions2) {
	actions.addAll(actions2);
    }

}
