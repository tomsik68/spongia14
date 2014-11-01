package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import sk.exceptional.spongia14.api.ClickableRegionSet;
import sk.tomsik68.resourceslib.Resources;

public class ClickableRegionSetContainer {
	private final ClickableRegionSet regionSet;
	private Image background;
	private boolean wasPressed = false;

	public ClickableRegionSetContainer(ClickableRegionSet set) {
		regionSet = set;
	}

	public void init(Resources resources) {
		background = resources.getImage(regionSet.getBackgroundResource());
	}

	public void render(Graphics gfx) {
		gfx.drawImage(background, 0, 0);
	}

	public void update(Input input) {
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && !wasPressed) {
			int x = input.getMouseX();
			int y = input.getMouseY();
			regionSet.onClick(x, y);
			wasPressed = true;
		} else
			wasPressed = false;
	}
}
