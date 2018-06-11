package xyz.un4ckn0wl3z.xml;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public String driverId = null;
	public String name = null;

	public List<Vehicle> vehicles = new ArrayList<Vehicle>();

	@Override
	 public String toString() {
        return this.driverId + " : " +
               this.name     + " : " +
               this.vehicles;
    }
	

}
