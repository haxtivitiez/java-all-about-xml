package xyz.un4ckn0wl3z.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class JavaSAXSchemaValidation {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		String targetFileName = "theXml.xml";
		SaxHandler saxHandler = new SaxHandler();
		Schema schema = null;
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(new File(targetFileName));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setSchema(schema);

		SAXParser parser = spf.newSAXParser();

		parser.parse(targetFileName,saxHandler);

	}

}
