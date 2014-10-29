package sk.tomsik68.resourceslib;


import java.io.File;
import java.util.regex.Pattern;

abstract class PatternBasedProcessor extends ResourceProcessor {
    private final Pattern pattern;

    PatternBasedProcessor(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    boolean processes(File file) {
        return pattern.matcher(file.getName()).matches();
    }

}
