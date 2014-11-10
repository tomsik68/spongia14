package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import sk.exceptional.spongia14.api.MissionState;
import sk.tomsik68.resourceslib.Resources;

public abstract class PersonModel {
    public abstract void init(Resources res);

    public abstract void render(Graphics gfx, int x, int y);

    public abstract void update(MissionState missionState, GameContainer gc);
}
