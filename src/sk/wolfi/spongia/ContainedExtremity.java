package sk.wolfi.spongia;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class ContainedExtremity extends Extremity {
	public Extremity extremity;
	private Shape shape;
	private Point bodyJointPos;
	public Shape bodyShape;
	private String name;
	public ContainedExtremity(Extremity extremity, int bodyJointX, int bodyJointY, Shape bodyShape, String name) {
		super(extremity.getWidth(), extremity.getHeight());
		this.name = name;
		this.bodyJointPos = new Point(bodyJointX, bodyJointY);
		this.bodyShape = bodyShape;
		float x = bodyShape.getX() + bodyJointX - extremity.getJointPosition().getX();
		float y = bodyShape.getY() + bodyJointY - extremity.getJointPosition().getY();
		this.shape = new Rectangle(x, y, extremity.getWidth(), extremity.getHeight());
		this.extremity = extremity;
	}
	
	public void rotateBy(int degrees) {
		float jointX = this.bodyJointPos.getX() + this.bodyShape.getX();
		float jointY = this.bodyJointPos.getY() + this.bodyShape.getY();
		this.shape = this.shape.transform(Transform.createRotateTransform((float) Math.toRadians(degrees), jointX, jointY));
	}
	
	// getters and setters
	
	public Shape getShape() {
		return this.shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Point getBodyJointPos() {
		return this.bodyJointPos;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String s) {
		this.name = s;
	}	
}