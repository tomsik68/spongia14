package sk.wolfi.spongia;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Body {
	private int width, height;
	private List<ContainedExtremity> extremities;
	private Shape shape;
	
	public Body(int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		this.shape = new Rectangle(x, y, width, height);
		this.extremities = new ArrayList<ContainedExtremity>();
	}
	
	public void attach(Extremity extremity, int bodyJointX, int bodyJointY, String extrName) {
		ContainedExtremity contEx = new ContainedExtremity(extremity, bodyJointX, bodyJointY, this.shape, extrName);
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
			ex.setShape(ex.getShape().transform(Transform.createTranslateTransform(xOffset, yOffset)));
		}
	}
	
	public void moveBy(float x, float y) {
		this.update(x, y);
		float curXPos = this.shape.getX();
		float curYPos = this.shape.getY();
		this.shape.setLocation(curXPos + x, curYPos + y);
	}
	
	public void draw(Graphics g) {
		g.draw(this.shape);
		for(ContainedExtremity e : this.extremities) {
			g.draw(e.getShape());
		}
	}
	
	// getters and setters
	
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
