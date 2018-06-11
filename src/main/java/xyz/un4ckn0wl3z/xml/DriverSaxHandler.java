package xyz.un4ckn0wl3z.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DriverSaxHandler extends DefaultHandler {

	public List<Driver> drivers = new ArrayList<Driver>();
	public Map<String, Vehicle> vehicles = new HashMap<String, Vehicle>();

	private Stack<String> elementStack = new Stack<String>();
	private Stack<Object> objectStack = new Stack<Object>();

	// start element
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		this.elementStack.push(qName);

		if ("driver".equals(qName)) {
			Driver driver = new Driver();
			this.objectStack.push(driver);
			this.drivers.add(driver);
		} else if ("vehicle".equals(qName)) {
			this.objectStack.push(new Vehicle());
		}
	}

	// end element
    public void endElement(String uri, String localName,
        String qName) throws SAXException {

        this.elementStack.pop();

        if("vehicle".equals(qName) || "driver".equals(qName)){
            Object object = this.objectStack.pop();
            if("vehicle".equals(qName)){
                Vehicle vehicle = (Vehicle) object;
                this.vehicles.put(vehicle.vehicleId, vehicle);
            }
        }
    }
    
    // characters
    
    public void characters(char ch[], int start, int length)
            throws SAXException {

            String value = new String(ch, start, length).trim();
            if(value.length() == 0) return; // ignore white space

            if("driverId".equals(currentElement())){
                Driver driver = (Driver) this.objectStack.peek();
                driver.driverId = (driver.driverId != null ?
                                   driver.driverId  : "") + value;
            } else if(("name"  .equals(currentElement())) &&
                      "driver".equals(currentElementParent())){

                Driver driver = (Driver) this.objectStack.peek();
                driver.name = (driver.name != null ?
                                    driver.name  : "") + value;

            } else if("vehicleId".equals(currentElement()) &&
                      "driver"   .equals(currentElementParent())){

                Driver driver = (Driver) this.objectStack.peek();
                Vehicle vehicle = this.vehicles.get(value);
                if(vehicle != null) driver.vehicles.add(vehicle);

            } else if("vehicleId".equals(currentElement()) &&
                      "vehicle"  .equals(currentElementParent())){

                Vehicle vehicle   = (Vehicle) this.objectStack.peek();
                vehicle.vehicleId = (vehicle.vehicleId != null ?
                                        vehicle.vehicleId  : "")  + value;

            } else if("name"   .equals(currentElement()) &&
                      "vehicle".equals(currentElementParent())){

                Vehicle vehicle = (Vehicle) this.objectStack.peek();
                vehicle.name = (vehicle.name != null ? vehicle.name  : "")
                                   + value;
            } 
        }

    
    //-------------------------------------------------- COMMON
    private String currentElement() {
        return this.elementStack.peek();
    }

    private String currentElementParent() {
        if(this.elementStack.size() < 2) return null;
        return this.elementStack.get(this.elementStack.size()-2);
    }

}
