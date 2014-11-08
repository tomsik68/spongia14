package sk.wolfi.spongia;

import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Body {
	private int width, height;
	private List<Extremity> extremities;
	private Shape shape;
	protected Image image;
	protected Point position;
	
	public Body(int x, int y, int width, int height, String imgLocation) {
		this.width = width;
		this.height = height;
		this.extremities = new ArrayList<Extremity>();
		this.position = new Point(x, y);
		try {
			this.image = new Image(imgLocation);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attach(Extremity extremity) {
		extremities.add(extremity);
	}
	
	
	
	
	
	
	/*public void draw(Graphics g) {
		this.image.draw(this.position.getX(), this.position.getY());
		for(Extremity e : this.extremities) {
			e.draw(g);
		}
	}*/
	
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
	
	public Point getPosition() {
		return this.position;
	}
}
