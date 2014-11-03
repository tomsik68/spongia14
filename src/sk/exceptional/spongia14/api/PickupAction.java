package sk.exceptional.spongia14.api;

import sk.exceptional.spongia14.pnc.ClickableRegion;
import sk.exceptional.spongia14.pnc.ItemClickableRegion;

public class PickupAction extends ClickAction {

    @Override
    public void execute(Mission mission, MissionState missionState,
	    ClickableRegion clicked) {
	if (clicked instanceof ItemClickableRegion) {
	    ItemClickableRegion icr = (ItemClickableRegion) clicked;
	    Item item = icr.getItem();
	    missionState.putItemInInventory(item);
	} else {
	    // TODO: co ak nie?!
	    System.out.println("Nic ine ako item sa neda zobrat!");
	}

    }

}
