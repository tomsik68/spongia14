package sk.wolfi.modelengine;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class HumanBody {
    private int width, height;
    private Point position;
    private Point offsetJointPos;
    private Image image;
    private List<Extremity> extremities;

    public HumanBody(int x, int y, int width, int height, Image image,
	    Extremity... extremities) {
	this.width = width;
	this.height = height;
	this.position = new Point(x, y);
	this.extremities = Arrays.asList(extremities);
	this.image = image;
    }

    public Extremity get(String name) {
	for (Extremity e : this.extremities) {
	    if (name.equals(e.getName())) {
		return e;
	    }
	}
	return null;
    }

    public void update() {
	for (Extremity e : this.extremities) {
	    e.update(this.position);
	}
    }

    public void draw() {
	for (Extremity e : this.extremities) {
	    if (e.isHiddenBehindTorso()) {
		e.draw(this.position);
	    }
	}

	this.image.draw(this.position.getX(), this.position.getY());
	for (Extremity e : this.extremities) {
	    if (!e.isHiddenBehindTorso()) {
		e.draw(this.position);
	    }
	}
    }

    public void moveBy(int x, int y) {
	this.position = new Point(this.position.getX() + x,
		this.position.getY() + y);
    }

    // getters and setters

    public int getWidth() {
	return this.width;
    }

    public int getHeight() {
	return this.height;
    }

    public Point getPosition() {
	return this.position;
    }
}
