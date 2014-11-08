package sk.exceptional.spongia14.pnc;

import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.actions.Action;
import sk.exceptional.spongia14.api.actions.SwitchPlaceAction;

public class EntranceClickableRegion extends StaticRectangularClickableRegion {
    private Entrance entrance;
    private SwitchPlaceAction spa;

    public EntranceClickableRegion(Entrance entrance) {
	super(entrance.x, entrance.y, entrance.w, entrance.h);
	this.entrance = entrance;
	spa = new SwitchPlaceAction(entrance.destination);
	spa.setCantEnterText(entrance.cantEnterText);
    }

    @Override
    protected Action getAction() {
	return spa;
    }

}
