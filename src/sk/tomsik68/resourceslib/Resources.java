package sk.tomsik68.resourceslib;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;

public class Resources {
	private HashMap<String, RawResource> resourcesMap = new HashMap<String, RawResource>();
	private ArrayList<ResourceProcessor> processors = new ArrayList<ResourceProcessor>();

	public Resources() {
		processors.add(new FontProcessor());
		processors.add(new ImageProcessor());
	}

	public void load(File resDir) throws Exception {
		scanDir(resDir);
	}

	@SuppressWarnings("unchecked")
	public <T extends RawResource> T getResource(String key) {
		if (!resourcesMap.containsKey(key)) {
			System.out.println("Resource not found: " + key);
		}
		return (T) resourcesMap.get(key);
	}

	private void scanDir(File dir) throws Exception {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				scanDir(file);
			} else {
				addFile(file);
			}
		}
	}

	private void addFile(File file) throws Exception {
		String key = createResourceKeyForFile(file);

		RawResource res = process(file, key);
		if (res != null)
			resourcesMap.put(key, res);
	}

	private RawResource process(File file, String key) throws Exception {
		RawResource result = null;
		for (ResourceProcessor rp : processors) {
			if (rp.processes(file)) {
				result = rp.process(file, key);
			}
		}
		return result;
	}

	private String createResourceKeyForFile(File file) {
		String filePath = file.getPath();
		filePath = filePath.replace("*" + File.separator + "res"
				+ File.separator, "");
		filePath = filePath.replaceFirst("res.", "");
		if (filePath.contains(".")) {
			String[] split = filePath.split("\\.");
			String ext = split[split.length - 1];
			filePath = filePath.replace(".".concat(ext), "");
		}
		filePath = filePath.replace(File.separatorChar, '.');
		System.out.println(String.format("File '%s' ==> '%s'", file.getPath(),
				filePath));
		return filePath;
	}

	public Image getImage(String key) {
		return ((ImageResource) getResource(key)).getImage();
	}

	public Font getFont(String key) {
		return ((FontResource) getResource(key)).getFont();
	}
}
