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

import sk.exceptional.spongia14.api.AllowAccessAction;
import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.DialogActor;
import sk.exceptional.spongia14.api.DialogStartAction;
import sk.exceptional.spongia14.api.DialogTriggerListener;
import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.RemoveAction;
import sk.exceptional.spongia14.api.Replica;
import sk.exceptional.spongia14.api.Town;
import sk.exceptional.spongia14.pnc.ClickableRegionSetContainer;
import sk.exceptional.spongia14.pnc.ClickableRegionSetFactory;
import sk.exceptional.spongia14.pnc.ItemContainer;
import sk.exceptional.spongia14.pnc.PlaceChangeListener;
import sk.tomsik68.resourceslib.Resources;

public class InRegionSetGameState extends BasicGameState implements
	PlaceChangeListener, DialogTriggerListener {
    public static final int STATE_ID = 0;
    private Mission mission;
    private MissionState missionState;
    private ClickableRegionSetContainer container;
    private final Resources resources = new Resources();
    private ClickableRegionSetFactory crsFactory;
    private int fadeTimer = 0;
    private Place newPlace;
    private DialogWizard dialogWizard;
    private boolean inDialog = false;

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
	try {
	    mission = prepareExampleMission();
	} catch (Exception e) {
	    e.printStackTrace();
	    JOptionPane.showMessageDialog(null,
		    "Kedze sme vynimocni, zasluzite si od nas vynimku ;)");
	}
	missionState = new MissionState(mission);
	/**/
	missionState.allowEntrance("bytVraha");
	/**/
	missionState.addPlaceChangeListener(this);
	missionState.addDialogListener(this);
    }

    private Mission prepareExampleMission() throws Exception {
	Mission mission = new Mission();
	Town town = new Town("domVraha");

	ItemContainer ic;
	Entrance e;

	Place domVraha = new Place("domVraha", "buildings.in.domVraha");
	domVraha.addEntrance(new Entrance("bytVraha", 612, 234, 150, 300));
	domVraha.addEntrance(new Entrance("planMesta", 300, 270, 200, 180));

	Item zasielka = new Item("zasielka1", "Vyplata", "Vyplata od bossa",
		"items.kufrik");
	domVraha.addItem(ic = new ItemContainer(zasielka, 35, 450));
	ic.addActions(new RemoveAction());
	town.addPlace(domVraha);

	Place bytVraha = new Place("bytVraha", "buildings.in.bytVraha");
	e = new Entrance("domVraha", 657, 125, 100, 400);
	e.cantEnterText = "Mal by som najprv zodvihnut telefonat od bossa, inak bude zasa nastvany...";
	bytVraha.addEntrance(e);

	Item mobil = new Item("mobilVraha", "Tvoj mobil.",
		"Ziaden sitny iPhone", "items.mobil");
	bytVraha.addItem(ic = new ItemContainer(mobil, 300, 300));
	ic.addActions(new DialogStartAction("dialogSBossom"));
	ic.addActions(new AllowAccessAction("domVraha"));
	ic.addActions(new RemoveAction());

	town.addPlace(bytVraha);

	mission.setTown(town);

	Dialog dialog = new Dialog("dialogSBossom");

	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("boss", "TEN boss", "portrait.boss"));
	dialog.addReplica(new Replica("Halo? Michael Greenwich pri telefone.",
		"murd"));
	dialog.addReplica(new Replica(
		"Informacie o tvojom cieli by ti mali onedlho prist v balicku ktory obsahuje\n aj tvoju prvu polovicu vyplaty.Druhu ako obvykle dostanes po splneni prace.",
		"boss"));
	dialog.addReplica(new Replica(
		"Rozumiem, mame este nejake doplnujuce info ktore nenajdem v zasielke?",
		"murd"));
	dialog.addReplica(new Replica(
		"Ano,tento ciel je velmi slizky a uz dlho sa ho snazime zabit.\nAk sa ti to nepodari bude trvat velmi dlho kym ho zas najdeme tak ma nesklam.",
		"boss"));
	dialog.addReplica(new Replica("Ozvem sa ked to bude hotove.", "murd"));
	dialog.addReplica(new Replica(
		"Este jedna vec,jedina fotka ciela ktoru mame je velmi rozmazana,\n takze sa najprv dobre uisti ze je to tvoj ciel.",
		"boss"));
	mission.registerDialog(dialog);
	// misia zacina u vraha v byte
	placeSwitched(bytVraha);

	return mission;
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
	} else {
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
	if (!inDialog) {
	    if (fadeTimer == 0) {
		container.update(gc.getInput());
	    } else {
		--fadeTimer;
		if (fadeTimer == 0) {
		    container = new ClickableRegionSetContainer(mission,
			    missionState, crsFactory.createCRS(mission,
				    newPlace));
		    container.init(resources);
		}
	    }
	} else {
	    if (dialogWizard.isDone()) {
		inDialog = false;
	    }
	    dialogWizard.update(gc);
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

    @Override
    public void onTriggerDialog(Dialog dialog) {
	inDialog = true;
	dialogWizard = new DialogWizard(dialog);
	dialogWizard.init(resources);
    }

}
