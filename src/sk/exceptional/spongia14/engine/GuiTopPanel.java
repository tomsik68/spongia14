package sk.exceptional.spongia14.engine;

import java.util.Collection;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import sk.exceptional.spongia14.api.Item;
import sk.exceptional.spongia14.api.MissionState;
import sk.tomsik68.resourceslib.Resources;

public class GuiTopPanel {
    private Image background;
    private Image excl;
    private boolean exclShown = false;
    private boolean openMemento;
    private MissionState missionState;
    private final Resources resources;

    public GuiTopPanel(Resources res) {
	this.resources = res;
	background = res.getImage("gui.top_panel");
	excl = res.getImage("gui.topPanel_excl");
    }

    public void render(Graphics gfx) {
	gfx.drawImage(background, 0, 0);
	Collection<Item> inventory = missionState.getInventory().getItems();
	int x = 20;
	int i = 0;
	Iterator<Item> itr = inventory.iterator();
	while (itr.hasNext() && i <= 8) {
	    Item item = itr.next();
	    gfx.drawImage(resources.getImage(item.getResourceImage()), x, 5);
	    x += 64;
	}

	if (isExclShown()) {
	    gfx.drawImage(excl, 732, 30);
	}
    }

    public void update(GameContainer gc) {
	if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
	    int mouseX = gc.getInput().getMouseX();
	    int mouseY = gc.getInput().getMouseY();
	    if (mouseX >= 732 && mouseX <= 760 && mouseY < 60) {
		setOpenMemento(true);
	    }
	}
    }

    public boolean isExclShown() {
	return exclShown;
    }

    public void setExclShown(boolean exclShown) {
	this.exclShown = exclShown;
    }

    public boolean isOpenMemento() {
	return openMemento;
    }

    public void setOpenMemento(boolean openMemento) {
	this.openMemento = openMemento;
    }

    public void setMissionState(MissionState missionState) {
	this.missionState = missionState;
    }
}
