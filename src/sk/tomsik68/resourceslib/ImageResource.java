package sk.tomsik68.resourceslib;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

class ImageResource extends RawResource {
	private final Image image;

	ImageResource(File file) throws SlickException {
		super(file);
		this.image = new Image(file.getAbsolutePath());
	}

	public Image getImage() {
		return image;
	}

}
