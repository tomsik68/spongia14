package sk.exceptional.spongia14.engine;

import java.io.File;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.DialogTriggerListener;
import sk.exceptional.spongia14.api.MementoAddListener;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.pnc.ClickableRegionSetContainer;
import sk.exceptional.spongia14.pnc.ClickableRegionSetFactory;
import sk.exceptional.spongia14.pnc.PlaceChangeListener;
import sk.tomsik68.resourceslib.Resources;
import sk.wolfi.modelengine.WalkingHumanBody;
import sk.wolfi.modelengine.WalkingHumanBodyFactory;

public class InRegionSetGameState extends BasicGameState implements
	PlaceChangeListener, DialogTriggerListener, MementoAddListener {
    public static final int STATE_ID = 1;
    private Mission mission;
    private MissionState missionState;
    private ClickableRegionSetContainer container;

    private ClickableRegionSetFactory crsFactory;
    private int fadeTimer = 0;
    private Place newPlace;
    private DialogWizard dialogWizard;
    private boolean inDialog = false;
    private boolean inMemento = false;
    private MementoGui mementoGui;
    private GuiTopPanel topPanel;
    private WalkingHumanBody player;
    private Resources resources;

    // private ClickableRegionSetContainer newContainer;

    @Override
    public void init(GameContainer arg0, StateBasedGame game)
	    throws SlickException {
	SpongiaGame g = (SpongiaGame) game;
	resources = g.getResources();
	try {
	    resources.load(new File("res"));
	} catch (Exception e) {
	    e.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Engine failure: failed to load resources.");
	    System.exit(1);
	}
	crsFactory = new ClickableRegionSetFactory(resources);
	try {
	    mission = SpongiaCampaign.createMission();
	} catch (Exception e) {
	    e.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Kedze sme vynimocni, zasluzite si od nas vynimku ;)");
	}
	missionState = new MissionState(mission);
	missionState.addPlaceChangeListener(this);
	missionState.addDialogListener(this);
	missionState.addMementoListener(this);
	SpongiaCampaign.prepareState(missionState);
	Place beginning = mission.getPlace(SpongiaCampaign.getBeginningPlace());
	placeSwitched(beginning);
	mementoGui = new MementoGui(resources.getImage("gui.memento"),
		resources);
	topPanel = new GuiTopPanel(resources);
	topPanel.setMissionState(missionState);

	// player = WalkingHumanBodyFactory.create();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics gfx)
	    throws SlickException {
	if (container != null)
	    container.render(gfx);
	if (!inDialog) {
	    if (fadeTimer > 0) {
		gfx.setColor(new Color(0, 0, 0, 255 - fadeTimer / 2));
		gfx.fillRect(0, 0, gc.getWidth(), gc.getHeight());
	    }
	}
	topPanel.render(gfx);
	if (inMemento && !mementoGui.isDone()) {
	    mementoGui.render(gfx);
	}
	if (inDialog) {
	    dialogWizard.render(gfx);
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
	if (!inDialog && !inMemento) {
	    if (fadeTimer == 0) {
		container.update(gc.getInput());
	    } else {
		--fadeTimer;
		if (fadeTimer == 0) {
		    container = new ClickableRegionSetContainer(mission,
			    missionState, crsFactory.createCRS(mission,
				    missionState, newPlace));
		    container.init(resources);
		}
	    }
	    if (gc.getInput().isKeyPressed(Input.KEY_M)
		    || (topPanel.isOpenMemento() && !inMemento)) {
		mementoGui.show();
		topPanel.setExclShown(false);
		topPanel.setOpenMemento(false);
		inMemento = true;
		System.out.println("Janeviemus");
	    }
	}
	if (inMemento) {
	    if (mementoGui.isDone()) {
		inMemento = false;
	    } else
		mementoGui.update(gc);
	}
	if (inDialog) {
	    if (dialogWizard.isDone()) {
		inDialog = false;
	    }
	    dialogWizard.update(gc);
	}
	topPanel.update(gc);
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

    @Override
    public void onTriggerDialog(Dialog dialog) {
	inDialog = true;
	dialogWizard = new DialogWizard(dialog);
	dialogWizard.init(resources);
    }

    @Override
    public void onMementoAdded(String mementoRes) {
	/*
	 * inMemento = true;
	 * mementoGui.showMemento(resources.getImage(mementoRes));
	 */
	mementoGui.addMemento(mementoRes);
	topPanel.setExclShown(true);
    }

}
