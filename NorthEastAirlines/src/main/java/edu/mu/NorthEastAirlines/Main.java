package edu.mu.NorthEastAirlines;

import accounts.UserAccounts;
import flights.PlanePsuedoFactory;
import flights.PlaneObject;
import seatSelection.SeatType;
public class Main {

	public static void main(String[] args) {	
		
		// Create singleton 
		AirlineManagerSingleton alm = new AirlineManagerSingleton();
		alm.getInstance();
		
		// Create first account
		UserAccounts acct1 = new UserAccounts();
		UserAccounts acct2 = new UserAccounts();
		UserAccounts acct3 = new UserAccounts();
		UserAccounts acct4 = new UserAccounts();
		
		
		
		
		// Create user accounts
		acct1 = alm.createAccount("Abcd", "abcd", "Bob", "Ross");
		acct2 = alm.createAccount("uwfd", "abcd", "John", "Billigo");
		acct3 = alm.createAccount("ctosm", "defg", "Hillbilly", "Jinglo");
		acct4 = alm.createAccount("abcd", "defg", "Mister", "Donnovan");
		
		
		System.out.println("**** PRINTING ACCOUNT INFORMATION ****");
		System.out.println(acct1.toString());
		System.out.println(acct2.toString());
		System.out.println(acct3.toString());
		System.out.println(acct4.toString());
		
		boolean loggedIn = alm.login("Abcd", "abcd");
		boolean invalidLogin = alm.login("a", "abcd");
		boolean doubleLog = alm.login("Abcd", "abcd");
		
		//alm.listBookedFlights("Abcd");
		alm.viewAccountInformation("Abcd");
		
		
		
		Flight flight1 = alm.generateRandomFlights();
		Flight flight2 = alm.generateRandomFlights();
		alm.bookFlight(acct1, flight1);
		System.out.println("User1 Points: " + acct1.getUserPoints());
		alm.bookFlight(acct2, flight2);
		System.out.println("User2 Points: " + acct2.getUserPoints());
	}

}
