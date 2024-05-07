package main;

import java.util.Scanner;

import accounts.AccountStatus;
import accounts.UserAccounts;
import edu.mu.NorthEastAirlines.AirlineManagerSingleton;
import flights.PlanePsuedoFactory;
import flights.UserFlightData;
import flights.PlaneObject;
import seatSelection.SeatType;
public class Main {

	public static void main(String[] args) {	
			
		
//		// Creates Singleton
//		AirlineManagerSingleton NorthEastAirlines = AirlineManagerSingleton.getInstance();
//		
//		// Generate flights
//		NorthEastAirlines.addFlightsToMasterList(10);
//		
//		String baseUsername = "username";
//		String basePassword= "password";
//		String baseFirstName = "firstName";
//		String baseLastName = "lastName";
//				
//		// Create lots of accounts!
//		for(int i = 0; i < 20; i++) {
//			String usn = baseUsername.concat(Integer.toString(i));
//			String psw = basePassword.concat(Integer.toString(i));
//			String fsn = baseFirstName.concat(Integer.toString(i));
//			String lsn = baseLastName.concat(Integer.toString(i));
//			NorthEastAirlines.createAccount(usn, psw, fsn, lsn);
//		}
//		
//		System.out.println("**** PRINTING ALL ACCOUNTS ****");
//		NorthEastAirlines.printAccounts("adminPassword");
//		
//		System.out.println("Pausing...\n\n\n");
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("**** CREATING ACCOUNT AND LOGGING IN****");
//
//		// Create real user account
//		UserAccounts primaryAccount = NorthEastAirlines.createAccount("primaryAccount", "securePassword", "John", "Doe");
//		UserAccounts secondAccount = NorthEastAirlines.createAccount("secondAccount", "secure-erPassword", "Johnny", "Doer");
//		
//		System.out.println("Account with information: " + NorthEastAirlines.viewAccountInformation("primaryAccount"));
//		System.out.println("Account login status: " + primaryAccount.getLoginStatus());
//		
//
//		// Login
//		boolean login = NorthEastAirlines.login("primaryAccount", "securePassword");
//		boolean login2 = NorthEastAirlines.login("secondAccount", "secure-erPassword");
//		if(!login || !login2) {
//			System.out.println("Bad login!");
//		}
//		
//		
//		
//		// Pause and talk
//		System.out.println("Pausing...\n\n\n");
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("*** BOOKING FLIGHTS ***");
//		NorthEastAirlines.bookFlight(primaryAccount);
//		System.out.println("Printing user flight information" + NorthEastAirlines.listBookedFlights("primaryAccount"));
//		
//		// Book 4 more flights & print
//		NorthEastAirlines.bookFlight(primaryAccount);
//		NorthEastAirlines.bookFlight(primaryAccount);
//
//		
//		System.out.println("Printing user flight information;");
//		NorthEastAirlines.listBookedFlights("primaryAccount");
//		
//		// Pause to talk
//		System.out.println("Pausing...\n\n\n");
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		NorthEastAirlines.changePassword("primaryAccount", "securePassword", "password2");
//		NorthEastAirlines.logout("primaryAccount", "password2");
//		
//		NorthEastAirlines.deleteAccount("secondAccount", "secure-erPassword");
//		NorthEastAirlines.login("primaryAccount", "password2");
//		
//		NorthEastAirlines.cancelFlightReservation(0, "primaryAccount");
//		NorthEastAirlines.bookFlight(primaryAccount);
//		
//		NorthEastAirlines.logout("primaryAccount", "password2");
//		
//		
	}

}
