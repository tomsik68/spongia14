package sk.wolfi.spongia;

import java.util.List;
import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class HumanBody extends Body {
	private Extremity head, leftArm, rightArm, leftLeg, rightLeg;
	private List<Extremity> extremities;
	public HumanBody(int x, int y, int width, int height, String imgLocation, Extremity...extremities) {
		super(x, y, width, height, imgLocation);
		
		this.head = extremities[0];
		this.leftArm = extremities[1];
		this.rightArm = extremities[2];
		this.leftLeg = extremities[3];
		this.rightLeg = extremities[4];
		
		this.extremities = Arrays.asList(extremities);
		
		for(Extremity e : this.extremities) {
			e.setBodyPos(new Point(x, y));
		}
		
	}
	

	public Extremity find(String name) {
		for(Extremity ex : this.extremities) {
			if(ex.getName().equals(name)) {
				return ex;
			}
		}
		return null;
	}
	
	public void update(float offsetX, float offsetY) {
		for(Extremity ex : this.extremities) {
			ex.update(offsetX, offsetY);
			//ex.setShape(ex.getShape().transform(Transform.createTranslateTransform(xOffset, yOffset)));
		}
	}
	
	public void moveBy(float x, float y) {
		this.update(x, y);
		float curXPos = this.position.getX();
		float curYPos = this.position.getY();
		this.position = new Point(curXPos + x, curYPos + y);
	}
	
	public Extremity get(String extrName) {
		for(Extremity e : this.extremities) {
			if(extrName.equals(e.getName())) {
				return e;
			}
			
		}
		
		return null;
	}
	
	public void draw() {
		this.leftArm.draw();
		this.image.draw(this.position.getX(), this.position.getY());
		for(Extremity e : this.extremities) {
			if(!e.equals(this.leftArm)) {
				e.draw();
			}
		}
	}
	
	
}
