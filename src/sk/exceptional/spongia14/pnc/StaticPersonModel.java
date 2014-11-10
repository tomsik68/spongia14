package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import sk.exceptional.spongia14.api.MissionState;
import sk.tomsik68.resourceslib.Resources;

public class StaticPersonModel extends PersonModel {
    private Image image;
    private final String imgRes;

    public StaticPersonModel(String imgRes) {
	this.imgRes = imgRes;
    }

    @Override
    public void update(MissionState missionState, GameContainer gc) {

    }

    @Override
    public void render(Graphics gfx, int x, int y) {
	gfx.drawImage(image, x, y);
    }

    @Override
    public void init(Resources res) {
	image = res.getImage(imgRes);
    }

}
