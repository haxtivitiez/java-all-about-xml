package xyz.un4ckn0wl3z.xml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserxz {

	public static void main(String[] args) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SaxHandler saxHandler = new SaxHandler();
		try {
			InputStream xmlInput = new FileInputStream("theXml.xml");
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlInput,saxHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
