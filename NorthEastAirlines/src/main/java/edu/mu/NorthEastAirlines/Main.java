package edu.mu.NorthEastAirlines;

import accounts.UserAccounts;
import flights.PlanePsuedoFactory;
import flights.UserFlightData;
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
		
		/*
		alm.addFlightsToMasterList(4);
		//alm.viewPlaneSeats(1);
		
		System.out.println("*** Booking Flight *** \n\n");
		boolean book1 = alm.bookFlight(acct1);
		System.out.print("\nBooked Flight Details for: " + acct1.getFirstName() + "\n");
		for(UserFlightData data: acct1.getBookedFlights())
		{
			System.out.println("Flight Number: " + data.getFlight());
			System.out.println("Seat Number: " + data.getSeat());
			System.out.println("Seat Type: " + data.getSeatType().name());
		}
		System.out.println("User Points: " + acct1.getUserPoints());
		//alm.viewPlaneSeats(1);
		//alm.viewAvailableFlights();
		System.out.println("Membership Level: " + acct1.getMembershipLevel());
		*/
		
		String username = "test1";
		String password = "password";
		String newPassword = "newPassword";
		//alm.changeMembershipLevel(username, password);
		alm.changePassword(username, password, newPassword);
		alm.logout(username, password);
		alm.logout(username, newPassword);
	}

}
