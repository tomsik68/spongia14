package sk.wolfi.spongia;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
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
		private String name;
		public ContainedExtremity(Extremity extremity, int width, int height, Point extrJointPos, int bodyJointX, int bodyJointY, Shape bodyShape, String name) {
			super(width, height);
			this.name = name;
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
		
		public void setName(String s) {
			this.name = s;
		}
		
		public String getName() {
			return this.name;
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
	
	public void attach(Extremity extremity, int bodyJointX, int bodyJointY, String extrName) {
		ContainedExtremity contEx = new ContainedExtremity(extremity, extremity.getWidth(), extremity.getHeight(),
				extremity.getJointPosition(), bodyJointX, bodyJointY, this.shape, extrName);
		
		extremities.add(contEx);
	}
	
	public ContainedExtremity find(String name) {
		for(ContainedExtremity ex : this.extremities) {
			if(ex.getName().equals(name)) {
				return ex;
			}
		}
		return null;
	}
	
	public void update(float xOffset, float yOffset) {
		for(ContainedExtremity ex : this.extremities) {

			ex.shape = ex.shape.transform(Transform.createTranslateTransform(xOffset, yOffset));
			
		}
	}
	
	public void moveBy(float x, float y) {
		this.update(x, y);
		float curXPos = this.shape.getX();
		float curYPos = this.shape.getY();
		this.shape.setLocation(curXPos + x, curYPos + y);
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
	
	public void draw(Graphics g) {
		g.draw(this.shape);
		for(ContainedExtremity e : this.extremities) {
			g.draw(e.getShape());
		}
	}
}
