package sk.exceptional.spongia14.api;

public class CancelIfNoItem extends CancelAction {

    private String itemId;

    public CancelIfNoItem(String item) {
	this.itemId = item;
    }

    @Override
    public boolean shouldCancel(MissionState state) {
	return !state.getInventory().hasItem(itemId);
    }
}
