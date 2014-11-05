package sk.exceptional.spongia14.api;

public class TakeItemAction extends Action {

    private final String itemId;

    public TakeItemAction(String itemId) {
	this.itemId = itemId;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	Item item = mission.getItem(itemId);
	state.getInventory().deleteItem(item);
    }

}
