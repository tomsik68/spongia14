package sk.wolfi.spongia;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Body {
	private int width, height;
	private List<ContainedExtremity> extremities;
	
	private Shape shape;
	
	class ContainedExtremity extends Extremity {
		public Extremity extremity;
		private Shape shape;
		private Point bodyJointPos;
		public Shape bodyShape;
		public ContainedExtremity(Extremity extremity, int width, int height, Point extrJointPos, int bodyJointX, int bodyJointY, Shape bodyShape) {
			super(width, height);
			this.bodyJointPos = new Point(bodyJointX, bodyJointY);
			this.bodyShape = bodyShape;
			float x = bodyShape.getX() + bodyJointX - extremity.getJointPosition().getX();
			float y = bodyShape.getY() + bodyJointY - extremity.getJointPosition().getY();
			this.shape = new Rectangle(x, y, width, height);
			this.extremity = extremity;
		}
		
		public Shape getShape() {
			return this.shape;
		}
		
		public Point getBodyJointPos() {
			return this.bodyJointPos;
		}
		
		public void rotateBy(int degrees) {
			float jointX = this.bodyJointPos.getX() + this.bodyShape.getX();
			float jointY = this.bodyJointPos.getY() + this.bodyShape.getY();
			this.shape = this.shape.transform(Transform.createRotateTransform((float) Math.toRadians(degrees), jointX, jointY));
		}
				
	}
	
	public Body(int x, int y, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		this.shape = new Rectangle(x, y, width, height);
		this.extremities = new ArrayList<ContainedExtremity>();
		
	}
	
	public void attach(Extremity extremity, int bodyJointX, int bodyJointY) {
		ContainedExtremity contEx = new ContainedExtremity(extremity, extremity.getWidth(), extremity.getHeight(),
				extremity.getJointPosition(), bodyJointX, bodyJointY, this.shape);
		
		extremities.add(contEx);
	}
	
	public ContainedExtremity find(Extremity extremity) {
		for(ContainedExtremity ex : this.extremities) {
			if(ex.extremity.equals(extremity)) {
				return ex;
			}
		}
		return null;
	}
	
	public void update() {
		for(ContainedExtremity ex : this.extremities) {
			float x = this.shape.getX() + ex.getBodyJointPos().getX() - ex.extremity.getJointPosition().getX();
			float y = this.shape.getY() + ex.getBodyJointPos().getY() - ex.extremity.getJointPosition().getY();
			ex.getShape().setLocation(x, y);
			
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Shape getShape() {
		return this.shape;
	}
}
