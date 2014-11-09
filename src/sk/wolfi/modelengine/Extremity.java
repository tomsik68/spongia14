package sk.wolfi.modelengine;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Extremity {
    private int width, height;
    private Point position;
    private Point offsetJointPos;
    private Point offsetBodyJointPos;
    private String name;
    private Image image;
    private Point bodyPos; // the body position, and thus the extremity position
			   // is known only after the update loop.
    private boolean isHiddenBehindTorso;

    public Extremity(String name, int width, int height, String imgLocation,
	    int offsetExtrJointPosX, int offsetExtrJointPosY,
	    int offsetBodyJointPosX, int offsetBodyJointPosY,
	    boolean isHiddenBehindTorso) {
	this.name = name;
	this.width = width;
	this.height = height;
	this.offsetJointPos = new Point(offsetExtrJointPosX,
		offsetExtrJointPosY);
	this.offsetBodyJointPos = new Point(offsetBodyJointPosX,
		offsetBodyJointPosY);
	try {
	    this.image = new Image(imgLocation);
	} catch (SlickException e) {
	    e.printStackTrace();
	}
	this.position = new Point(0, 0);
	this.isHiddenBehindTorso = isHiddenBehindTorso;
    }

    public void rotateBy(float degrees) {
	this.image.rotate((float) degrees);
    }

    public void update(Point bodyPos) {
	this.bodyPos = bodyPos;
	float x = this.bodyPos.getX() + this.offsetBodyJointPos.getX()
		- this.offsetJointPos.getX();
	float y = this.bodyPos.getY() + this.offsetBodyJointPos.getY()
		- this.offsetJointPos.getY();
	this.position = new Point(x, y);
	this.image.setCenterOfRotation(offsetJointPos.getX(),
		offsetJointPos.getY());

	// TODO better way to set starting Extremity.position!

    }

    public void draw(Point bodyPos) {
	this.image.draw(this.position.getX(), this.position.getY());
    }

    // getters and setters

    public boolean isHiddenBehindTorso() {
	return this.isHiddenBehindTorso;
    }

    public String getName() {
	return this.name;
    }

    public void setRotation(float degrees) {
	this.image.setRotation((float) degrees);
    }
}