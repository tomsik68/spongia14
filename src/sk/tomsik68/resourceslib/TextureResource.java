package sk.tomsik68.resourceslib;


import java.io.File;
import java.io.FileInputStream;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

class TextureResource extends RawResource {
    private final Texture texture;

    TextureResource(File file) throws Exception {
        super(file);
        texture = TextureLoader.getTexture("png", new FileInputStream(file));
    }

    Texture getTexture() {
        return texture;
    }

}
