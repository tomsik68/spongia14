package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.tomsik68.resourceslib.Resources;

public class ClickableRegionSetContainer {
    private final ClickableRegionSet regionSet;
    private Image background;
    private static boolean wasPressed = false;
    private final Mission mission;
    private final MissionState missionState;
    private int mouseX;
    private int mouseY;

    public ClickableRegionSetContainer(Mission mission,
	    MissionState missionState, ClickableRegionSet set) {
	regionSet = set;
	this.mission = mission;
	this.missionState = missionState;
    }

    public void init(Resources resources) {
	background = resources.getImage(regionSet.getBackgroundResource());
    }

    public void render(Graphics gfx) {
	gfx.drawImage(background, 0, 0);
	regionSet.renderRegions(gfx, mouseX, mouseY);
    }

    public void update(Input input) {
	mouseX = input.getMouseX();
	mouseY = input.getMouseY();
	if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !wasPressed) {
	    regionSet.onClick(mission, missionState, mouseX, mouseY);
	    wasPressed = true;
	} else
	    wasPressed = false;
    }
}
