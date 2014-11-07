package sk.exceptional.spongia14.engine;

import javax.naming.NameAlreadyBoundException;

import sk.exceptional.spongia14.api.AddMementoAction;
import sk.exceptional.spongia14.api.AllowAccessAction;
import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.DialogActor;
import sk.exceptional.spongia14.api.DialogStartAction;
import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.RemoveAction;
import sk.exceptional.spongia14.api.Replica;
import sk.exceptional.spongia14.api.Town;
import sk.exceptional.spongia14.pnc.ItemContainer;

public class SpongiaCampaign {

    public static void prepareState(MissionState missionState) {
	/**/
	missionState.allowEntrance("bytVraha");
	/**/
    }

    public static String getBeginningPlace() {
	return "bytVraha";
    }

    public static Mission createMission() {
	try {
	    Mission mission = new Mission();
	    Town town = new Town("planMesta");

	    ItemContainer ic;
	    Entrance e;

	    Place planMesta = new Place("planMesta", "buildings.out.planMesta");
	    e = new Entrance("domVraha", 295, 84, 140, 140);
	    planMesta.addEntrance(e);
	    town.addPlace(planMesta);

	    // place ma ID, resource background
	    Place domVraha = new Place("domVraha", "buildings.in.domVraha");
	    // entrance su dvere alebo vychod, ktory zmeni miesto
	    // entrancy sa pridavaju do Place
	    domVraha.addEntrance(new Entrance("bytVraha", 612, 234, 150, 300));
	    domVraha.addEntrance(e = new Entrance("planMesta", 300, 270, 200,
		    180));
	    e.cantEnterText = "Mal by som si najprv vziat svoju zasielku.\nNechcem predsa, aby niekto iny dostal moje prachy.";
	    // Item ma unikatne ID, meno a popis pre inventory a resource
	    // background
	    Item zasielka = new Item("zasielka1", "Vyplata",
		    "Vyplata od bossa", "items.kufrik");
	    mission.registerItem(zasielka);
	    // kazdu vec treba uzavriet v ItemContaineri so suradnicami a pridat
	    // do place
	    domVraha.addItem(ic = new ItemContainer(zasielka, 35, 450));
	    // ItemContaineru mozem pridavat akcie - ako RemoveAction,
	    // DialogStartAction, SwitchPlaceAction, GiveItemAction,
	    // TakeItemAction
	    ic.addAction(new RemoveAction());
	    ic.addAction(new AllowAccessAction("planMesta"));
	    // place treba po vsetkych upravach pridat do mesta
	    town.addPlace(domVraha);

	    Place bytVraha = new Place("bytVraha", "buildings.in.bytVraha");
	    // teraz mam entrance vo vlastnej premennej e typu Entrance
	    e = new Entrance("domVraha", 657, 125, 100, 400);
	    // tento text sa ukaze, ked sa este neda vstupit do tychto dveri
	    e.cantEnterText = "Mal by som najprv zodvihnut mobil.\nVola mi boss, takze ho nesmiem nechat dlho cakat.\nVyzera to tak, ze budem mat dalsiu pracu...";
	    bytVraha.addEntrance(e);

	    Item mobil = new Item("mobilVraha", "Tvoj mobil.",
		    "Tvoj mobil, lebo pevna linka ti nejde.", "items.mobil");
	    mission.registerItem(mobil);
	    bytVraha.addItem(ic = new ItemContainer(mobil, 300, 300));
	    // ked vezmem mobil, zacne sa rozhovor s bossom
	    ic.addAction(new DialogStartAction("dialogSBossom"));
	    // odomkne sa place s ID domVraha
	    ic.addAction(new AllowAccessAction("domVraha"));
	    // a mobil odtial zmizne
	    ic.addAction(new RemoveAction());
	    ic.addAction(new AddMementoAction("memento.memento1"));

	    town.addPlace(bytVraha);

	    // tu pridavam mesto do misie
	    mission.setTown(town);
	    // tu pridavam dialogy do misie

	    Dialog dialog = createBossDialog();
	    mission.registerDialog(dialog);
	    return mission;
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }

    private static Dialog createBossDialog() throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSBossom");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("boss", "TEN boss", "portrait.boss"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Halo? Michael Greenwich pri telefone.",
		"murd"));
	dialog.addReplica(new Replica(
		"Co ti tak dlho trvalo? Za co ta platim?", "boss"));
	dialog.addReplica(new Replica(
		"Prepacte sefe, mal som tazke rano. Som Vam plne k dispozicii.\nCo by ste potrebovali?",
		"murd"));
	dialog.addReplica(new Replica(
		"Mam pre teba pracu. Potreboval by som, aby si sa zbavil jedneho cloveka.\nSposobuje nam vazne problemy. Informacie o tvojom cieli\n ti uz urcite davno prisli v zasielke ty spachtos. \nV tej zasielke mas aj prvu polovicu vyplaty. \nDruhu ako obvykle dostanes po splneni prace.",
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
	dialog.addReplica(new Replica("Vykonam. Zatial.", "murd"));
	return dialog;
    }

}
