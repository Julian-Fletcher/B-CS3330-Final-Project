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
		
		
		alm.addFlightsToMasterList(4);
	
		
		System.out.println("*** Booking Flight *** \n\n");
		boolean book1 = alm.bookFlight(acct1);
		System.out.print("\nBooked Flight Details for: " + acct1.getFirstName() + "\n");
		
		alm.viewPlaneSeats(0);
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
		
		
		String username = "test1";
		String password = "password";
		String newPassword = "newPassword";
		//alm.changeMembershipLevel(username, password);
		alm.changePassword(username, password, newPassword);
		alm.logout(username, password);
		alm.logout(username, newPassword);
		
		alm.cancelFlightReservation(0, username);
		alm.viewPlaneSeats(0);
		System.out.println("User Points: " + acct1.getUserPoints());
		
		/*
		 * Demo psedocode
		 * 1) Init singleton
		 * 2) Create account
		 * - Could be multiple to demonstrate error cheecking
		 * 3) Login to account
		 * 4) Book flight(s)
		 * 5) View account information
		 * 6) Cancel a flight
		 * 7) View account information 
		 * 8) Delete account
		 * 9) Attempt to login to invalid account (show delete account works!)
		 * 10) YAY DONE!!
		 */
	}

}
