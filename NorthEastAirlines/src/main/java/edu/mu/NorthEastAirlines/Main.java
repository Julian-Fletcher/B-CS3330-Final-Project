package edu.mu.NorthEastAirlines;

import accounts.UserAccounts;
import flights.PlanePsuedoFactory;
import flights.PlaneObject;
import seatSelection.SeatType;
public class Main {

	public static void main(String[] args) {	
		
		// Create singleton 
		AirlineManagerSingleton alm = AirlineManagerSingleton.getInstance();
		
		// Create first account
		UserAccounts acct1 = new UserAccounts();		
		
		// Create user accounts
		acct1 = alm.createAccount("test1", "password", "test", "test");
		boolean login = alm.login("test1", "password");
		if(!login) {
			System.out.println("Login failed!");
		}
		
		System.out.println("Viewing Account information");
		alm.viewAccountInformation("test1");
		
		alm.generateRandomFlights();
		alm.generateRandomFlights();
		alm.generateRandomFlights();
		
		System.out.println("*** Booking Flight *** \n\n");
		boolean book1 = alm.bookFlight(acct1);
		boolean book2 = alm.bookFlight(acct1);
	}

}
