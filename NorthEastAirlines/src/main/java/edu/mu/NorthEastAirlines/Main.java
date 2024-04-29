package edu.mu.NorthEastAirlines;

import flights.PlaneFactory;
import flights.PlaneObject;
import seatSelection.SeatType;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("THIS IS WOKRING");

		AirlineManagerSingleton manager = new AirlineManagerSingleton();
		
		Flight flight = manager.generateRandomFlights();
		
		System.out.println(flight.getAvailableSeats(SeatType.COMFORT));
		System.out.println(flight.getAllSeats(SeatType.COMFORT));
		
		manager.printAccounts("a");
		
		
	}

}
