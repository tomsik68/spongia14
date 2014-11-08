package sk.wolfi.spongia;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class ContainedExtremity extends Extremity {
	public Extremity extremity; 
	protected Point parentJointPos;
	protected Point parentPos;
	protected String name;
	
	protected ContainedExtremity extention;
	
	public ContainedExtremity(Extremity extremity, int parentJointX, int parentJointY, Point parentPos, String name) {
		super(name, extremity.getWidth(), extremity.getHeight(), extremity.getImage().getName(), (int) extremity.getJointPos().getX(), (int) extremity.getJointPos().getY());
		/*float x = parentPos.getX() + parentJointX - extremity.getJointPos().getX();
		float y = parentPos.getY() + parentJointY - extremity.getJointPos().getY();*/
		
		
		this.name = name;
		this.parentJointPos = new Point(parentJointX, parentJointY);
		this.parentPos = parentPos;
		this.extremity = extremity;
	}
	
	public void rotateBy(int degrees) {
		float jointX = this.parentJointPos.getX() + this.parentPos.getX();
		float jointY = this.parentJointPos.getY() + this.parentPos.getY();
		/*this.shape = this.shape.transform(Transform.createRotateTransform((float) Math.toRadians(degrees), jointX, jointY));
		if(this.hasExtention()) {
			this.extention.shape = this.extention.shape.transform(Transform.createRotateTransform((float) Math.toRadians(degrees), jointX, jointY));
		}*/
	}
	
	/*public void extend(Extremity e, int extrJointX, int extrJointY, String name) {
		this.extention = new ContainedExtremity(e, extrJointX, extrJointY, this.parentPos, name);
	}*/
	
	public void draw(Graphics g) {
		/*g.draw(this.shape);
		this.image.draw(this.shape.getX(), this.shape.getY());
		
		if(this.extention != null) {
			g.draw(this.extention.shape);
		}*/
	}
	
	public void update(float xOffset, float yOffset) {
		/*this.shape = this.shape.transform(Transform.createTranslateTransform(xOffset, yOffset));
		if(this.hasExtention()) {
			this.extention.setShape(this.extention.getShape().transform(Transform.createTranslateTransform(xOffset, yOffset)));
		}*/
		
	}
	
	/*public boolean hasExtention() {
		if(this.extention != null) {
			return true;
		}
		
		else {
			return false;
		}
	}*/
	
	/*public void followMouse(Input input) {
		float a = this.extremity.getJointPosition().getX();
		float b = this.extremity.getJointPosition().getY();
		float mouseX = input.getMouseX() - this.shape.getX();
		float mouseY = input.getMouseY() - this.shape.getY();
		double angle = Math.toDegrees(Math.atan2(mouseY, mouseX));
		this.image.setCenterOfRotation(this.extremity.getJointPosition().getX(), this.extremity.getJointPosition().getY());
		this.image.setRotation((float) angle);
	}*/
	
	// getters and setters
	
	/*public Shape getShape() {
		return this.shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}*/
	
	public Point getParentJointPos() {
		return this.parentJointPos;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String s) {
		this.name = s;
	}	
	
	/*public ContainedExtremity getExtention() {
		return this.extention;
	}*/
}