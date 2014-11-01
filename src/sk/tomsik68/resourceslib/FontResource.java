package sk.tomsik68.resourceslib;


import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

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
