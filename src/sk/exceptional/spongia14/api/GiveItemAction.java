package sk.exceptional.spongia14.api;

public class GiveItemAction extends Action {

    private final String itemId;

    public GiveItemAction(String itemId) {
	this.itemId = itemId;
    }

    @Override
    public void execute(Mission mission, MissionState state) {
	Item item = mission.getItem(itemId);
	state.getInventory().addItem(item);
    }

}
