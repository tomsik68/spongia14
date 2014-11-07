package sk.wolfi.spongia;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Extremity {
	private int width;
	private int height;
	private Point extrJointPos;

	
	public Body body;
	private int bodyX, bodyY;
	private Point offsetBodyJointPos;

	
	public Extremity(int width, int height) {
		this.width = width;
		this.height = height;
		
	}
	
	
	
	public void setJoint(int offsetX, int offsetY) { 
		this.extrJointPos = new Point(offsetX, offsetY);
	}
	
	public Point getJointPosition() {
		return this.extrJointPos;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}	
	
	public int getHeight() {
		return this.height;
	}
	
	public void setOffsetBodyJointPos(Point pos) {
		this.offsetBodyJointPos = pos;
	}
	
	

}
