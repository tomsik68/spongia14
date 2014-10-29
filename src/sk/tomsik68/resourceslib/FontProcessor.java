package sk.tomsik68.resourceslib;


import java.io.File;
import java.util.regex.Pattern;

class FontProcessor extends PatternBasedProcessor {
    private static Pattern pattern = Pattern.compile("\\w*.ttf");

    FontProcessor() {
        super(pattern);
    }

    @Override
    RawResource process(File file, String key) throws Exception {
        FontResource res = new FontResource(file);
        return res;
    }

}
