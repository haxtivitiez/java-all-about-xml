package xyz.un4ckn0wl3z.xml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParsingExample {

	public static void main(String argv[]) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			InputStream xmlInput = new FileInputStream("driverVehicleInfo.xml");

			SAXParser saxParser = factory.newSAXParser();
			DriverSaxHandler handler = new DriverSaxHandler();
			saxParser.parse(xmlInput, handler);

			for (Driver driver : handler.drivers) {
				System.out.println(driver);
			}
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}
}
