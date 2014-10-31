package sk.tomsik68.resourceslib;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

class XmlReference extends RawResource {
	private static DocumentBuilder docBuilder;
	private Document doc;

	XmlReference(File file) {
		super(file);
		try {
			doc = docBuilder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Document getDocument() {
		return doc;
	}

	static {
		try {
			docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
