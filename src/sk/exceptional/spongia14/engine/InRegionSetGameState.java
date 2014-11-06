package sk.exceptional.spongia14.engine;

import java.io.File;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.Town;
import sk.exceptional.spongia14.pnc.ClickableRegionSetContainer;
import sk.exceptional.spongia14.pnc.ClickableRegionSetFactory;
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
    private int fadeTimer = 0;
    private Place newPlace;

    // private ClickableRegionSetContainer newContainer;

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
	Town town = new Town("domVraha");

	Place domVraha = new Place("domVraha", "buildings.in.domVraha");
	domVraha.addEntrance(new Entrance("bytVraha", 612, 234, 150, 300));
	town.addPlace(domVraha);

	Place bytVraha = new Place("bytVraha", "buildings.in.bytVraha");
	bytVraha.addEntrance(new Entrance("domVraha", 657, 125, 100, 300));
	town.addPlace(bytVraha);

	mission.setTown(town);
	return mission;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	    throws SlickException {
	if (container != null)
	    container.render(gfx);
	if (fadeTimer > 0) {
	    gfx.setColor(new Color(0, 0, 0, 255 - fadeTimer / 2));
	    gfx.fillRect(0, 0, gc.getWidth(), gc.getHeight());
	}
	Display.sync(60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
	    throws SlickException {
	/*
	 * if (newContainer != null) { container = newContainer; newContainer =
	 * null; }
	 */
	// pri prechode nefunguje nic
	if (fadeTimer == 0) {
	    container.update(gc.getInput());
	} else {
	    --fadeTimer;
	    if (fadeTimer == 0) {
		container = new ClickableRegionSetContainer(mission,
			missionState, crsFactory.createCRS(mission, newPlace));
		container.init(resources);
	    }
	}

    }

    @Override
    public int getID() {
	return STATE_ID;
    }

    @Override
    public void placeSwitched(Place newPlace) {
	fadeTimer = 60 * 2;
	this.newPlace = newPlace;
    }

}
