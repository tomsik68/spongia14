package sk.tomsik68.resourceslib;

import java.io.File;
import java.util.regex.Pattern;

class ImageProcessor extends PatternBasedProcessor {
	private final static Pattern pattern = Pattern.compile("\\w*.png");
	ImageProcessor() {
		super(pattern);
	}
	@Override
	RawResource process(File file, String key) throws Exception {
		return new ImageResource(file);
	}

}
