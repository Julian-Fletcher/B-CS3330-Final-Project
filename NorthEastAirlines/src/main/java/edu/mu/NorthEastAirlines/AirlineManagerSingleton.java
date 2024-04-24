package edu.mu.NorthEastAirlines;

import java.util.List;
import java.util.Random;


public class AirlineManagerSingleton {
	String adminPassword;
	List<Account> allAccounts;
	List<Flight> allFlights;
	
	
	
	
	
	public PlaneObject generatePlane() {
		PlaneObject newplane = new PlaneObject();
		
		Random ran = new Random();
		System.out.println(ran);
		return new PlaneObject();
	}
	
	
	
	
}
