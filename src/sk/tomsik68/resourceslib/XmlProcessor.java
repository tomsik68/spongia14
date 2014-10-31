package sk.tomsik68.resourceslib;

import java.io.File;
import java.util.regex.Pattern;

class XmlProcessor extends PatternBasedProcessor {
	private static final Pattern pattern = Pattern.compile("\\w*.xml");

	XmlProcessor() {
		super(pattern);
	}

	@Override
	RawResource process(File file, String key) throws Exception {
		return new XmlReference(file);
	}

}
