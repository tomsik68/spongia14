package sk.exceptional.spongia14.engine;

import java.io.File;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.SwitchPlaceAction;
import sk.exceptional.spongia14.api.Town;
import sk.exceptional.spongia14.pnc.ClickableRegionSetContainer;
import sk.exceptional.spongia14.pnc.ClickableRegionSetFactory;
import sk.exceptional.spongia14.pnc.ItemContainer;
import sk.exceptional.spongia14.pnc.PlaceChangeListener;
import sk.tomsik68.resourceslib.Resources;

public class InRegionSetGameState extends BasicGameState implements
	PlaceChangeListener {
    public static final int STATE_ID = 0;
    private Mission mission;
    private MissionState missionState;
    private ClickableRegionSetContainer container;
    private final Resources resources = new Resources();
    private ClickableRegionSetFactory crsFactory;
    //private ClickableRegionSetContainer newContainer;

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
	    throws SlickException {
	try {
	    resources.load(new File("res"));
	} catch (Exception e) {
	    e.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Engine failure: failed to load resources.");
	    System.exit(1);
	}
	crsFactory = new ClickableRegionSetFactory(resources);
	mission = prepareExampleMission();
	missionState = new MissionState(mission);
	missionState.addPlaceChangeListener(this);
	placeSwitched(mission.getTown().getMap());
    }

    private Mission prepareExampleMission() {
	Mission mission = new Mission();
	Town town = new Town("ivan.homola");

	Place place = new Place("ivan.homola", "example");
	Item ivanItem = new Item("ivan-item", "key", "Ivan Homola",
		"Najvacsi pan na svete", "ivanItem");
	mission.registerItem(ivanItem);
	ItemContainer ic = new ItemContainer(ivanItem, 50, 50);
	ic.addActions(new SwitchPlaceAction("ivan2"));
	place.addItem(ic);
	town.addPlace(place);

	Place place2 = new Place("ivan2", "example2");
	town.addPlace(place2);

	mission.setTown(town);
	return mission;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	    throws SlickException {
	container.render(gfx);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
	    throws SlickException {
	/*if (newContainer != null) {
	    container = newContainer;
	    newContainer = null;
	}*/
	container.update(gc.getInput());
    }

    @Override
    public int getID() {
	return STATE_ID;
    }

    @Override
    public void placeSwitched(Place newPlace) {
	container = new ClickableRegionSetContainer(mission, missionState,
		crsFactory.createCRS(mission, newPlace));
	container.init(resources);
    }

}
