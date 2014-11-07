package sk.wolfi.spongia;

import org.newdawn.slick.geom.Point;

public class Extremity {
	private int width;
	private int height;
	private Point extrJointPos;
	public Body body;

	public Extremity(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	// getters and setters
	
	public void setJoint(int offsetX, int offsetY) { 
		this.extrJointPos = new Point(offsetX, offsetY);
	}
	
	public Point getJointPosition() {
		return this.extrJointPos;
	}
	
	public int getWidth() {
		return this.width;
	}	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
