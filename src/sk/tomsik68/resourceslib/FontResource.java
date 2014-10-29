package sk.tomsik68.resourceslib;


import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import org.newdawn.slick.TrueTypeFont;

class FontResource extends RawResource {

    private TrueTypeFont ttf;

    FontResource(File file) throws Exception {
        super(file);
        Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(location));
        ttf = new TrueTypeFont(font, true);
    }

    TrueTypeFont getFont() {
        return ttf;
    }

}
