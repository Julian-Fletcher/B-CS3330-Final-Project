package edu.mu.NorthEastAirlines;

import java.util.List;
import java.util.Random;


public class AirlineManagerSingleton {
	String adminPassword;
	List<Account> allAccounts;
	List<Flight> allFlights;
	
	
	
	
	
	public PlaneObject generatePlane() {
		PlaneObject newplane = new PlaneObject();
		
	    Random random = new Random();
	    int nseats = random.nextInt(150 - 50) + 50;
	    
	    for(int i=0;i<nseats;i++) {
	    	
	    	Seat seat = new Seat(i, SeatType.values()[random.nextInt(2)]);
	    	newplane.addSeat(seat);
	    	
	    }
		System.out.println(newplane.getComfortSeats().toString());
		return new PlaneObject();
	}
	
	
	
	
}
