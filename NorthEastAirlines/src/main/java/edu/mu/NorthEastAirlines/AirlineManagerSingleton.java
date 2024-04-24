package edu.mu.NorthEastAirlines;

import java.util.List;
import java.util.Random;


public class AirlineManagerSingleton {
	String adminPassword;
	List<Account> allAccounts;
	List<Flight> allFlights;
	
	public AirlineManagerSingleton() {
	
	}
	
	
	
	public PlaneObject generatePlane() {
		PlaneObject newplane = new PlaneObject();
		
	    Random random = new Random();
	    int nseats = random.nextInt(150 - 50) + 50;
	    
	    for(int i=0;i<nseats;i++) {
	    	
	    	Seat seat = new Seat(i, SeatType.values()[random.nextInt(3)]);
	    	newplane.addSeat(seat);
	    	
	    }
		System.out.println(newplane.getComfortSeats().toString());
		System.out.println(newplane.getEconomySeats().toString());
		System.out.println(newplane.getFirstClassSeats().toString());
		return new PlaneObject();
	}
	
	
	
	
}
