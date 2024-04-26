package edu.mu.NorthEastAirlines;

import java.util.List;
import java.util.Random;

import accounts.UserAccounts;
import flights.PlaneObject;
import flights.PlaneType;
import seatSelection.Seat;
import seatSelection.SeatType;


public class AirlineManagerSingleton {
	private String adminPassword;
	private List<UserAccounts> allAccounts;
	public List<Flight> allFlights;
	public boolean isAdminLoggedIn;	// Check if admin account logged in
	public AirlineManagerSingleton() {
	
	}
	
	/* JULIAN - GET INSTANCE METHOD GOES HERE!!! */
	// Needs to instanticate an admin account
	
	public PlaneObject generatePlane() {
		PlaneObject newplane = new PlaneObject();
		Random random = new Random();
		newplane.setModel(PlaneType.values()[random.nextInt(3)]);
	    
	    int nseats = random.nextInt(150 - 50) + 50;
	    
	    for(int i=0;i<nseats;i++) {
	    	
	    	Seat seat = new Seat(i, SeatType.values()[random.nextInt(3)]);
	    	newplane.addSeat(seat);
	    	
	    }
	    
		return new PlaneObject();
	}
	
	/* *** User Account Management Begins Here *** */
	
	
	/* Account creation method */
	public UserAccounts createAccount() {
		
	}
	
	/* Method to log users in, will set login status to true if 
	 * account credentials match
	 */
	public boolean login(String username, String password) {
		// Need checking for if admin account
	}
	
	
	/* Allows a user to view their acccount information */
	public void viewAccountInformation(String username) {
		
	}
	
	/* User can view their flights */
	public boolean listBookedFlights(String username) {
		
	}
	
	// User can delete their accouont
	public boolean deleteAccount(String username, String password) {
		
	}
	
	/* Seat Selection Strategy goes here!*/
}
