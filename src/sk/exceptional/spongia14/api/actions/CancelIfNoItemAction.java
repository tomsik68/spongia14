package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.MissionState;

public class CancelIfNoItemAction extends CancelAction {

    private String itemId;

    public CancelIfNoItemAction(String item) {
	this.itemId = item;
    }

    @Override
    public boolean shouldCancel(MissionState state) {
	return !state.getInventory().hasItem(itemId);
    }
}
