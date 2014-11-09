package sk.exceptional.spongia14.pnc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;
import sk.tomsik68.resourceslib.Resources;
import sk.wolfi.modelengine.WalkingHumanBody;
import sk.wolfi.modelengine.WalkingHumanBodyFactory;

public class ClickableRegionSetContainer {
    private final ClickableRegionSet regionSet;
    private Image background;
    private static boolean wasPressed = false;
    private final Mission mission;
    private final MissionState missionState;
    private int mouseX;
    private int mouseY;
    private WalkingHumanBody player;
    private Point playerMoveVector = new Point(0, 0);
    private Point clickPoint = new Point(0, 0);

    public ClickableRegionSetContainer(Mission mission,
	    MissionState missionState, ClickableRegionSet set) {
	regionSet = set;
	this.mission = mission;
	this.missionState = missionState;
    }

    public void init(Resources resources) {
	background = resources.getImage(regionSet.getBackgroundResource());
	player = WalkingHumanBodyFactory.create(
		resources.getImage("ppl.greenwich.torso"),
		resources.getImage("ppl.greenwich.head"),
		resources.getImage("ppl.greenwich.right_arm"),
		resources.getImage("ppl.greenwich.right_arm"),
		resources.getImage("ppl.greenwich.left_leg"),
		resources.getImage("ppl.greenwich.right_leg"));
	player.moveBy(400, 400);
    }

    public void render(Graphics gfx) {
	gfx.drawImage(background, 0, 0);
	regionSet.renderRegions(gfx, mouseX, mouseY);
	if (regionSet.isRenderPlayer())
	    player.draw();
    }

    private static Point getVector(Point point, Point to) {
	Point result = new Point(0, 0);
	if (point.getX() > to.getX())
	    result.setX(-1);
	else if (point.getX() < to.getX())
	    result.setX(1);
	else
	    result.setX(0);
	if (point.getY() > to.getY())
	    result.setY(-1);
	else if (point.getY() < to.getY())
	    result.setY(1);
	else
	    result.setY(0);
	return result;
    }

    public void update(Input input) {
	mouseX = input.getMouseX();
	mouseY = input.getMouseY();
	if (mouseY > 80 && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
		&& !wasPressed) {
	    clickPoint = new Point(mouseX, mouseY);
	    if (distance(clickPoint, player.getPosition()) <= 200
		    || !regionSet.isRenderPlayer() || mouseY <= 300) {
		regionSet.onClick(mission, missionState, mouseX, mouseY);
		playerMoveVector.setLocation(0, 0);
		player.setWalking(false);
	    } else {
		playerMoveVector = getVector(player.getPosition(), clickPoint);
		player.setWalking(true);
	    }
	    wasPressed = true;
	} else
	    wasPressed = false;
	// update move vector
	if (playerMoveVector.getX() != 0 || playerMoveVector.getY() != 0) {
	    playerMoveVector = getVector(player.getPosition(), clickPoint);
	} else {
	    player.setWalking(false);
	}
	player.moveBy((int) playerMoveVector.getX(),
		(int) playerMoveVector.getY());
	if (player.getPosition().getY() < 300) {
	    player.getPosition().setY(400);
	}
	if (regionSet.isRenderPlayer())
	    player.update();
    }

    private int distance(Point clickPoint, Point position) {
	int distance = (int) Math.sqrt(Math.pow(
		clickPoint.getX() - position.getX(), 2)
		+ Math.pow(clickPoint.getY() - position.getY(), 2));
	return distance;
    }
}
