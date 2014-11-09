package sk.exceptional.spongia14.engine;

import javax.naming.NameAlreadyBoundException;

import sk.exceptional.spongia14.api.Dialog;
import sk.exceptional.spongia14.api.DialogActor;
import sk.exceptional.spongia14.api.Entrance;
import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.exceptional.spongia14.api.Person;
import sk.exceptional.spongia14.api.PersonState;
import sk.exceptional.spongia14.api.Place;
import sk.exceptional.spongia14.api.Replica;
import sk.exceptional.spongia14.api.Town;
import sk.exceptional.spongia14.api.actions.ActionSet;
import sk.exceptional.spongia14.api.actions.AddMementoAction;
import sk.exceptional.spongia14.api.actions.AllowAccessAction;
import sk.exceptional.spongia14.api.actions.CancelIfNoItemAction;
import sk.exceptional.spongia14.api.actions.ChangePersonState;
import sk.exceptional.spongia14.api.actions.DenyAccessAction;
import sk.exceptional.spongia14.api.actions.DialogStartAction;
import sk.exceptional.spongia14.api.actions.GiveItemAction;
import sk.exceptional.spongia14.api.actions.RemoveAction;
import sk.exceptional.spongia14.api.actions.TakeItemAction;
import sk.exceptional.spongia14.pnc.ItemContainer;
import sk.exceptional.spongia14.pnc.PersonContainer;

public class SpongiaCampaign {

    public static void prepareState(MissionState missionState) {
	missionState.allowEntrance("bytVraha");
	missionState.allowEntrance("restauraciaVstup");
	missionState.allowEntrance("restauraciaPivnica");
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

	    Place planMesta = new Place("planMesta", "buildings.out.planMesta",
		    false);
	    e = new Entrance("domVraha", 295, 84, 140, 140);
	    planMesta.addEntrance(e);
	    e = new Entrance("restauraciaVstup", 490, 70, 230, 180);
	    planMesta.addEntrance(e);
	    town.addPlace(planMesta);

	    // place ma ID, resource background
	    Place domVraha = new Place("domVraha", "buildings.in.domVraha",
		    true);
	    // entrance su dvere alebo vychod, ktory zmeni miesto
	    // entrancy sa pridavaju do Place
	    domVraha.addEntrance(new Entrance("bytVraha", 612, 234, 150, 300));
	    domVraha.addEntrance(e = new Entrance("planMesta", 300, 270, 200,
		    180));
	    e.cantEnterText = "Mal by som si najprv vziať zásielku. Nechcem predsa, aby moju výplatu dostal niekto iný.";
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
	    ic.addAction(new AddMementoAction("memento.memento1"));
	    // place treba po vsetkych upravach pridat do mesta
	    town.addPlace(domVraha);

	    Place bytVraha = new Place("bytVraha", "buildings.in.bytVraha",
		    true);
	    // teraz mam entrance vo vlastnej premennej e typu Entrance
	    e = new Entrance("domVraha", 657, 125, 100, 400);
	    // tento text sa ukaze, ked sa este neda vstupit do tychto dveri
	    e.cantEnterText = "Mal by som najprv zodvihnúť mobil. Volá mi boss, takže ho nesmiem nechat dlho čakat. Vyzerá to tak, že budem mať dalšiu prácu...";
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

	    Item stoEuro = new Item("stoEuro", "Sto Euro",
		    "Cast tvojej vyplaty", "items.cash");
	    mission.registerItem(stoEuro);
	    ic = new ItemContainer(stoEuro, 98, 540);
	    ic.addAction(new RemoveAction());
	    ic.addAction(new GiveItemAction(stoEuro.getUniqueID()));
	    bytVraha.addItem(ic);

	    town.addPlace(bytVraha);

	    Place restikaVstup = new Place("restauraciaVstup",
		    "buildings.in.restika_chodba", true);
	    restikaVstup.addEntrance(new Entrance("planMesta", 350, 110, 240,
		    205));
	    restikaVstup.addEntrance(new Entrance("restauraciaIn1", 655, 120,
		    128, 205));
	    restikaVstup.addEntrance(new Entrance("restauraciaPivnica", 530,
		    375, 150, 150));

	    Person uvitaciaServirka = new Person("waitress");
	    // rozpravam sa so servirkou 1. krat
	    PersonState state = new PersonState("stav1");
	    ActionSet actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSoServirkou0"));
	    actionSet.addAction(new AddMementoAction("memento.memento2"));
	    actionSet.addAction(new ChangePersonState("waitress", "stav2"));
	    state.setAction(actionSet);
	    uvitaciaServirka.addState(state);
	    // rozpravam sa s nou niekolky krat a mozno dostane stoEuro
	    state = new PersonState("stav2");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSoServirkou0"));
	    actionSet.addAction(new CancelIfNoItemAction("stoEuro"));
	    actionSet.addAction(new TakeItemAction("stoEuro"));
	    actionSet.addAction(new ChangePersonState("waitress", "stav3"));
	    actionSet.addAction(new DialogStartAction("dialogSoServirkou1"));
	    actionSet.addAction(new AllowAccessAction("restauraciaIn1"));
	    state.setAction(actionSet);
	    uvitaciaServirka.addState(state);
	    // dal som jej stoEuro a pytam sa, kde je ciel
	    state = new PersonState("stav3");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSoServirkou2"));
	    actionSet.addAction(new AddMementoAction("memento.memento3"));
	    actionSet.addAction(new DenyAccessAction("planMesta"));
	    actionSet.addAction(new ChangePersonState("waitress", "stav4"));
	    state.setAction(actionSet);
	    uvitaciaServirka.addState(state);
	    // aby mi nezakazovala vstup do mesta
	    state = new PersonState("stav4");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSoServirkou2"));
	    state.setAction(actionSet);
	    uvitaciaServirka.addState(state);

	    restikaVstup.addPerson(new PersonContainer(uvitaciaServirka,
		    "stav1", 300, 330));

	    town.addPlace(restikaVstup);

	    Place restika = new Place("restauraciaIn1", "buildings.in.restika",
		    true);
	    restika.addEntrance(e = new Entrance("restauraciaVstup", 680, 80,
		    100, 80));

	    Item jedlo = new Item("jedlo", "Steak s hranolkami",
		    "Jedlo pre Jasona Grahama", "items.jedlo");
	    mission.registerItem(jedlo);
	    ic = new ItemContainer(jedlo, 110, 250);
	    restika.addItem(ic);
	    ic.addAction(new GiveItemAction("jedlo"));
	    ic.addAction(new RemoveAction());
	    ic.addAction(new CancelIfNoItemAction("jed"));
	    ic.addAction(new TakeItemAction("jedlo"));
	    ic.addAction(new TakeItemAction("jed"));
	    ic.addAction(new GiveItemAction("otraveneJedlo"));

	    Item otraveneJedlo = new Item("otraveneJedlo",
		    "Otraveny steak s hranolkami", "Jedlo pre Jasona Graya",
		    "items.jedlo");
	    mission.registerItem(otraveneJedlo);

	    Person target1 = new Person("jasonGraham");
	    state = new PersonState("stratilaSaObjednavka");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSTargetom1"));
	    actionSet.addAction(new ChangePersonState("jasonGraham",
		    "kdeMamJedlo"));
	    state.setAction(actionSet);
	    target1.addState(state);
	    state = new PersonState("kdeMamJedlo");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSTargetom2"));
	    actionSet.addAction(new CancelIfNoItemAction("otraveneJedlo"));
	    actionSet.addAction(new TakeItemAction("otraveneJedlo"));
	    actionSet.addAction(new DialogStartAction("dialogSTargetom3"));
	    actionSet.addAction(new ChangePersonState("jasonGraham",
		    "dakujemChutiMi"));
	    actionSet.addAction(new AllowAccessAction("planMesta"));

	    state.setAction(actionSet);
	    target1.addState(state);

	    state = new PersonState("dakujemChutiMi");
	    actionSet = new ActionSet();
	    actionSet.addAction(new DialogStartAction("dialogSTargetom4"));
	    state.setAction(actionSet);
	    target1.addState(state);

	    restika.addPerson(new PersonContainer(target1,
		    "stratilaSaObjednavka", 333, 398));
	    town.addPlace(restika);

	    Place restikaPivnica = new Place("restauraciaPivnica",
		    "buildings.in.pivnica", true);
	    restikaPivnica.addEntrance(new Entrance("restauraciaVstup", 380,
		    200, 100, 160));
	    Item jedNaPotkany = new Item(
		    "jed",
		    "Jed",
		    "Jed na potkany, ktory si nasiel v pivnici. Je ho dost na to, aby zabil cloveka",
		    "items.jedNaPotkany");
	    restikaPivnica.addItem(ic = new ItemContainer(jedNaPotkany, 694,
		    519));
	    ic.addAction(new GiveItemAction("jed"));
	    ic.addAction(new RemoveAction());
	    ic.addAction(new CancelIfNoItemAction("jedlo"));
	    ic.addAction(new TakeItemAction("jed"));
	    ic.addAction(new TakeItemAction("jedlo"));
	    ic.addAction(new GiveItemAction("otraveneJedlo"));
	    mission.registerItem(jedNaPotkany);

	    town.addPlace(restikaPivnica);

	    // tu pridavam mesto do misie
	    mission.setTown(town);
	    // tu pridavam dialogy do misie
	    Dialog dialog = createBossDialog();
	    mission.registerDialog(dialog);
	    // dialogy s casnickou
	    dialog = createRestaurantWaitressDialog1();
	    mission.registerDialog(dialog);

	    dialog = createRestaurantWaitressDialog0();
	    mission.registerDialog(dialog);

	    dialog = createRestaurantWaitressDialog2();
	    mission.registerDialog(dialog);

	    // dialogy s targetom 1
	    dialog = createRestaurantTargetDialog1();
	    mission.registerDialog(dialog);

	    dialog = createRestaurantTargetDialog2();
	    mission.registerDialog(dialog);

	    dialog = createRestaurantTargetDialog3();
	    mission.registerDialog(dialog);

	    dialog = createRestaurantTargetDialog4();
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
	dialog.addReplica(new Replica("Haló? Michael Greenwich pri telefóne.",
		"murd"));
	dialog.addReplica(new Replica(
		"Čo ti tak dlho trvalo? Za čo ta platim?", "boss"));
	dialog.addReplica(new Replica(
		"Prepáčte šéfe, mal som ťažké ráno. Som Vám plne k dispozícii. Čo by ste potrebovali?",
		"murd"));
	dialog.addReplica(new Replica(
		"Mám pre teba pracu. Potreboval by som, aby si sa zbavil jedného človeka. Spôsobuje nám vážne problémy. Informácie o tvojom cieli ti už určite dávno prišli v zásielke ty spachtoš. V tej zásielke máš aj prvú polovicu výplaty. Druhú, ako obvykle, dostaneš po splnení práce.",
		"boss"));
	dialog.addReplica(new Replica(
		"Rozumiem, máte ešte nejake doplnujuce info ktoré nenájdem v zásielke?",
		"murd"));
	dialog.addReplica(new Replica(
		"Áno, tento cieľ je veľmi slizký a už dlho sa ho snažíme zbaviť. Ak sa ti to nepodarí bude trvať veľmi dlho, kým ho zas nájdeme tak ma nesklam.",
		"boss"));
	dialog.addReplica(new Replica("Ozvem sa keď to bude hotové.", "murd"));
	dialog.addReplica(new Replica(
		"Ešte jedna vec,jediná fotka cieľa, ktorú mame, je veľmi rozmazaná, takže sa najprv dobre uisti že máš správneho.",
		"boss"));
	dialog.addReplica(new Replica("Vykonám. Zatiaľ.", "murd"));
	return dialog;
    }

    private static Dialog createRestaurantWaitressDialog0()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSoServirkou0");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("wait", "Waitress", "portrait.waitress"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Dobrý večer pane, máte u nas rezerváciu?", "wait"));
	dialog.addReplica(new Replica(
		"Áno, mala by tu byť rezervácia na meno Nathan Grayson.",
		"murd"));
	dialog.addReplica(new Replica(
		"Prepáčte, na toto meno tu nemám rezerváciu", "wait"));
	return dialog;
    }

    private static Dialog createRestaurantWaitressDialog1()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSoServirkou1");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("wait", "Waitress", "portrait.waitress"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	/*
	 * dialog.addReplica(new Replica(
	 * "Dobrý večer pane, máte u nas rezerváciu?", "wait"));
	 * dialog.addReplica(new Replica(
	 * "Áno, mala by tu byť rezervácia na meno Nathan Grayson.", "murd"));
	 */
	dialog.addReplica(new Replica(
		"Ospravedlňujem sa pan Grayson, nech sa páči, usaďte sa.",
		"wait"));
	return dialog;
    }

    private static Dialog createRestaurantWaitressDialog2()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSoServirkou2");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("wait", "Waitress", "portrait.waitress"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Prepáčte ešte na minútku, ako som išiel k stolu tak sa mi zdalo, že som spatril starého priateľa. Má tu niekto stôl na meno Graham?",
		"murd"));
	dialog.addReplica(new Replica(
		"Ano má. Pán Graham sedí tamto v strede.", "wait"));
	return dialog;
    }

    private static Dialog createRestaurantTargetDialog1()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSTargetom1");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("targ", "Target1", "portrait.target1"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Je mi ľúto pane, ale pri neštastnej náhode sa nám stratila vaša objednávka. Mohli by ste mi ju prosim dať znova?",
		"murd"));
	dialog.addReplica(new Replica(
		"Zaiste, objednal som si steak s hranolkami.", "targ"));
	dialog.addReplica(new Replica(
		"Ďakujem, ešte raz sa ospravedlňujem za meškanie.", "murd"));
	return dialog;
    }

    private static Dialog createRestaurantTargetDialog3()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSTargetom3");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("targ", "Target1", "portrait.target1"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Vaše jedlo pane.", "murd"));
	dialog.addReplica(new Replica("Ďakujem.", "targ"));
	return dialog;
    }

    private static Dialog createRestaurantTargetDialog4()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSTargetom4");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("targ", "Target1", "portrait.target1"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Ďakujem, mozete ma nechat v klude jest? Z toho jedla ide nejaká divná vôňa. Rád skúšam nové veci.",
		"targ"));
	return dialog;
    }

    private static Dialog createRestaurantTargetDialog2()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSTargetom2");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("targ", "Target1", "portrait.target1"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Prinesiete mi už to jedlo? Začínam mať chuť... odísť bez platenia!",
		"targ"));
	dialog.addReplica(new Replica(
		"Zaiste. Poprosím Vas o chvíľu strpenia.", "murd"));

	return dialog;
    }

    private static Dialog createBossDialog2() throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSBossom2");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("boss", "TEN boss", "portrait.boss"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Čo som ti hovoril?", "boss"));
	dialog.addReplica(new Replica("Netušim.", "murd"));
	dialog.addReplica(new Replica(
		"Že nemáš zabiť nesprávny cieľ ty chumaj! Včera si zabil Marka Grahama a nie Jasona Grahama!Zabil si nevinného civilistu a ty sa teraz musíš postarať o to aby dôkazy zmizli! Dávam ti týždeň na to aby si ho našiel a zakryl po sebe stopy. Ak do týždňa nebude o všetko postarané tak sa mi neukazuj na oči a vypadni z mesta.",
		"boss"));
	dialog.addReplica(new Replica("Ok boss.", "murd"));
	return dialog;
    }

    private static Dialog createPoliceDialog() throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSPolicajtom");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("pol", "Policeman",
		"portrait.Policeman"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica(
		"Pane sem nesmiete, toto je miesto činu.", "pol"));
	dialog.addReplica(new Replica("Detektiv Eccleston, oddelenie vrážd.",
		"murd"));
	dialog.addReplica(new Replica("Ah, môžete prejsť.", "pol"));
	return dialog;
    }

    private static Dialog createTennant1() throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSTennantom");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("tent", "Tennant", "portrait.Tennant"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Dobrý deň detektív, čo tu máme?", "murd"));
	dialog.addReplica(new Replica(
		"Ah allons-y, konečne nejaké posily. Som detektív Tennant. Máme tu vraždu pomocou jedu. Veľmi kuriózne. Obeť sa volá Mark graham. Pracoval vo vydavateľstve LOŽ.",
		"tent"));
	dialog.addReplica(new Replica("Aké máte zatiaľ stopy?", "murd"));
	dialog.addReplica(new Replica(
		"Zatiaľ žiadne okrem toho aký jed bol použitý. Bol to jed na potkany.",
		"tent"));
	dialog.addReplica(new Replica(
		"Dobre. Pokúsim sa nájsť nejaké ďaľšie stopy.", "murd"));
	return dialog;
    }

    private static Dialog createRestaurantBarmanDialog1()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSBarmanom1");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("bar", "Barman", "portrait.Barman"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Kvitnú v zime ruže?", "murd"));
	dialog.addReplica(new Replica("Prosím poďte ďalej.", "bar"));
	return dialog;
    }

    private static Dialog createScratchDialog1()
	    throws NameAlreadyBoundException {
	Dialog dialog = new Dialog("dialogSoServirkou1");
	// actori/ucastnici dialogu
	// actor ma ID a Meno
	dialog.addActor(new DialogActor("murd", "Michael Greenwich",
		"portrait.greenwich"));
	dialog.addActor(new DialogActor("scat", "Scratch", "portrait.Scratch"));
	// repliky dialogov
	// replika ma text a ID actora, ktory ju hovori
	dialog.addReplica(new Replica("Čo máš pre mňa?", "scat"));
	dialog.addReplica(new Replica(
		"Hľadám svoj cieľ. Prepojený s hlavami podsvätia. Meno Jason Graham. Včera som zabil nespravny cieľ ktorý sa naňho podobá. Express.",
		"murd"));
	dialog.addReplica(new Replica("To ťa vyjde draho.", "Scat"));
	dialog.addReplica(new Replica("Nevadí. Tá odmena je väčšia.", "murd"));
	dialog.addReplica(new Replica(
		"Dobre. Ozvi sa zajtra, budem pre teba mať info .", "scat"));
	return dialog;
    }
}
