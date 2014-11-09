package sk.wolfi.modelengine;

import org.newdawn.slick.Image;

public class WalkingHumanBody extends HumanBody {
    private boolean walking = false;
    private boolean left = true;
    private Pendulum leftLegP = new Pendulum(20, 6d, 20);
    private Pendulum rightLegP = new Pendulum(20, 6d, -20);
    private Pendulum leftHandP = new Pendulum(20, 6d, -20);
    private Pendulum rightHandP = new Pendulum(20, 6d, 20);
    private Pendulum[] pendulums;

    public WalkingHumanBody(int x, int y, int width, int height, Image image,
	    Extremity[] extremities) {
	super(x, y, width, height, image, extremities);
	pendulums = new Pendulum[] { leftLegP, rightLegP, leftHandP, rightHandP };
    }

    @Override
    public void update() {
	super.update();
	if (walking) {
	    for (Pendulum p : pendulums) {
		p.update(Application.DELTA_TIME);
	    }
	    get("leftLeg").setRotation((float) leftLegP.getPos());
	    get("rightLeg").setRotation((float) rightLegP.getPos());
	    get("leftArm").setRotation((float) leftHandP.getPos());
	    get("rightArm").setRotation((float) rightHandP.getPos());
	}
    }

    public void setWalking(boolean walk) {
	this.walking = walk;
    }

    public boolean isWalking() {
	return walking;
    }

    public boolean isLeft() {
	return left;
    }

    public void setLeft(boolean left) {
	this.left = left;
    }
}
