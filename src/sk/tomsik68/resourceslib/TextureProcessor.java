package sk.tomsik68.resourceslib;


import java.io.File;
import java.util.regex.Pattern;

class TextureProcessor extends PatternBasedProcessor {
    private static final Pattern pattern = Pattern.compile("\\w*.png");

    TextureProcessor() {
        super(pattern);
    }

    @Override
    RawResource process(File file, String key) throws Exception {
        TextureResource resource = new TextureResource(file);
        return resource;
    }


}
