package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;

public class PickupAction extends Action {

    private final String itemId;

    public PickupAction(String itemId) {
	this.itemId = itemId;
    }

    @Override
    public void execute(Mission mission, MissionState missionState) {
	Item item = mission.getItem(itemId);
	missionState.getInventory().addItem(item);
    }

}
