package sk.wolfi.spongia;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class Extremity {
	private int width;
	private int height;
	
	private Point offsetExtrJointPos;
	private Point offsetBodyJointPos;
	
	protected Image image;
	private String name;
	private Point position;
	private Point bodyPos;

	public Extremity(String name, int width, int height, String imgLocation, 
			int offsetExtrJointPosX, int offsetExtrJointPosY, int offsetBodyJointPosX, int offsetBodyJointPosY) {
		this.width = width;
		this.height = height;
		this.offsetExtrJointPos = new Point(offsetExtrJointPosX, offsetExtrJointPosY);
		this.offsetBodyJointPos = new Point(offsetBodyJointPosX, offsetBodyJointPosY);			
		
		try {
			this.image = new Image(imgLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.image.setCenterOfRotation(offsetExtrJointPos.getX(), offsetExtrJointPos.getY());
	}
	
	public void update(float offsetX, float offsetY) {	
		Point oldPosition = this.position;
		this.position = new Point(oldPosition.getX() + offsetX,  oldPosition.getY() + offsetY);
	}
	
	public void draw() {
		this.image.draw(this.position.getX(), this.position.getY());
	}
	
	public void rotateBy(int degrees) {
		this.image.setRotation((float) degrees);
	}
	
	// getters and setters
	
	public void setJoint(int offsetX, int offsetY) { 
		this.offsetExtrJointPos = new Point(offsetX, offsetY);
	}
	
	public Point getJointPos() {
		return this.offsetExtrJointPos;
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
	
	public Image getImage() {
		return this.image;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setBodyPos(Point pos) {
		this.bodyPos = pos;
		this.position = new Point(
			pos.getX() + this.offsetBodyJointPos.getX() - offsetExtrJointPos.getX(),
			pos.getY() + this.offsetBodyJointPos.getY() - offsetExtrJointPos.getY()
		);
				
	}
}
