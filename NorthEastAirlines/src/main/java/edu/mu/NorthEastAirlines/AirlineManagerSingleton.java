package edu.mu.NorthEastAirlines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import flights.Airport;
import flights.AirportLocations;
import flights.PlaneFactory;
import flights.PlaneObject;
import accounts.AccountStatus;
import accounts.UserAccounts;
import flights.PlaneType;
import seatSelection.EmeraldSeatSelection;
import seatSelection.GoldSeatSelection;
import seatSelection.IronSeatSelection;
import seatSelection.Seat;
import seatSelection.SeatSelectionStrategy;
import seatSelection.SeatType;



public class AirlineManagerSingleton {
	private String adminPassword;
	private ArrayList<UserAccounts> allAccounts;
	public ArrayList<Flight> allFlights;
	public boolean isAdminLoggedIn;	// Check if admin account logged in
	private int accountIndex;
	
	private static AirlineManagerSingleton instance = null;		// Only one!
	private SeatSelectionStrategy strategy;	// SeatSelectionStrategy
	
	
//	public AirlineManagerSingleton() {
//	
//	}
	
	// Singleton initalizer. Only allows one!!
	// Does not yet make admin account!
	public static AirlineManagerSingleton getInstance() {
		if(instance == null) {
			instance = new AirlineManagerSingleton();
			instance.accountIndex = 0;
			instance.allAccounts = new ArrayList<UserAccounts>();
			// FLIGHT LIST NEED INIT HERE
			
			instance.isAdminLoggedIn = false;
			
		}
		return instance;
	}
	

	public boolean bookFlight(UserAccounts account, Flight flight, int seatNumber) {
		if(account.getMembershipLevel() == AccountStatus.EMERALD) {
			EmeraldSeatSelection select = new EmeraldSeatSelection();
			select.selectSeat(flight, account.getMembershipLevel(), seatNumber);
			return true;
		}
		if(account.getMembershipLevel() == AccountStatus.GOLD) {
			GoldSeatSelection select = new GoldSeatSelection();
			select.selectSeat(flight, account.getMembershipLevel(), seatNumber);
			return true;
		}
		if(account.getMembershipLevel() == AccountStatus.IRON) {
			IronSeatSelection select = new IronSeatSelection();
			select.selectSeat(flight, account.getMembershipLevel(), seatNumber);
			return true;
		}
		
		return false;
	}
	
	
	//*********************** GENERATE RANDOM FLIGHT STARTS HERE *************************************
	public Flight generateRandomFlights()
	{
		Airport arriveAirport = new Airport();
		Airport departAirport = new Airport();
		LocalDateTime departTime;
		LocalDateTime arriveTime;
		int flightNumber = -1;
		
		//Creates Plane Factory
		PlaneFactory planeFactory = new PlaneFactory();
		
		//Generates new plane
		PlaneObject plane = planeFactory.generaterRandomPlane();
		AirportLocations arriveLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		AirportLocations departLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		
		while(arriveLocal == departLocal)
		{
			departLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		}
		
		departTime = randomLocalDateTime();
		arriveTime = randomLocalDateTime();
		LocalDateTime tempDateTime = null;
		
		if(departTime.isAfter(arriveTime))
		{
			tempDateTime = arriveTime;
			arriveTime = departTime;
			departTime = tempDateTime;
		}
		
		arriveAirport.airportCode = randomAirportCode();
		arriveAirport.city = arriveLocal.toString();
		departAirport.airportCode = randomAirportCode();
		departAirport.city = departLocal.toString();
		flightNumber = randomFlightNumber();
		
		Flight newFlight = new Flight(departAirport, arriveAirport, departTime, arriveTime, flightNumber, plane);
		
		return newFlight;
	}

	public String randomAirportCode()
	{
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 4; i++)
		{
			char randomChar = (char)(random.nextInt(26) + 'A');
			sb.append(randomChar);
		}
		return sb.toString();
	}
	
	public int randomAirportLocation()
	{
		int maxLocations = 10;
		Random random = new Random();
		return random.nextInt(maxLocations);
	}
	
	public int randomFlightNumber()
	{
		Random random = new Random();
		int id = random.nextInt(9000) + 1000;
		return id;
	}
	
	public int randomPlaneType()
	{
		Random random = new Random();
		return random.nextInt(3);
	}
	
	public LocalDateTime randomLocalDateTime()
	{
		 Random random = new Random();
	     int year1 = random.nextInt(2025 - 2000) + 2000;
	     int month1 = random.nextInt(12) + 1;
	     Month monthEnum1 = Month.of(month1);
	     int day1 = random.nextInt(monthEnum1.length(year1 % 4 == 0)) + 1;
	     int hour1 = random.nextInt(24);
	     int minute1 = random.nextInt(60);
	     int second1 = random.nextInt(60);
	     LocalDateTime localDateTime = LocalDateTime.of(year1, month1, day1, hour1, minute1, second1);
	     return localDateTime;
	}
	
	//*********************** GENERATE RANDOM FLIGHT ENDS HERE *************************************
	
	/* Little management methods~*/
	private boolean updateAccountIndex(int update) {
		switch (update){
		case 1:
			this.accountIndex++;
			break;
		case 2:
			this.accountIndex--;
			break;
		default:
			return false;
		}
		return true;
	}
	
	/* *************** ACCOUNT MANAGEMENT METHODS BEGIN HERE ***************  */
	
	
	/* Account creation method */	
	public UserAccounts createAccount(String username, String password, String firstName, String lastName) {
		// Check if the username is taken already
		if(this.locateByUsername(username) != null) {
			System.out.println("Username '" + username + "' is already taken!");
			return null;
		}
		
		// Instantiate account
		UserAccounts acct = new UserAccounts();
		
		// Basic acct information
		acct.setFirstName(firstName);
		acct.setLastName(lastName);
		acct.setMembershipLevel(AccountStatus.IRON);	// Set account level
		
		// Set acct index
		acct.setId(accountIndex);
		this.updateAccountIndex(1);
		
		// Username and password
		acct.setUsername(username);
		// Hash password & set
		try {
			acct.setPassword(hashPassword(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} // Hash
		acct.setLoginStatus(false);	// Set login status to false
		
		allAccounts.add(acct);	// Add new account to account list
		return acct;
		
	}
	
	// Will has the user password with SHA-256 algorithm, which reportedly isn't super secure
	// But we'll start with this
	private String hashPassword(String rawPassword) throws NoSuchAlgorithmException {
		// Creates singleton for the hashing 
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(rawPassword.getBytes());
		
		// Presumably converts bytes to hex string
		StringBuilder hexString = new StringBuilder();
		for(byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if(hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	// Verifies whether the provided password matches the one in the user account
	private boolean verifyPassword(String givenPassword, UserAccounts requestedAccount) throws NoSuchAlgorithmException {
		String hashedInput = hashPassword(givenPassword);
		return hashedInput.equals(requestedAccount.getPassword());	// This is not secure, password should PROBRABLY be private
	}
	
	/* Method to log users in, will set login status to true if 
	 * account credentials match
	 */
	public boolean login(String username, String password) {
		// Need checking for if admin account
		
		// Check if  account exists
		UserAccounts requestedAccount = this.locateByUsername(username);
		if(requestedAccount == null) {
			System.out.println("Account with username '" + username + "' exists!");
			return false;
		}
		
		// Check if already logged in
		if(requestedAccount.getLoginStatus() == true) {
			System.out.println("Already logged in! Did you mean to logout?");
			return true;	// True for logged in always
		}
		
		// If not already logged in
		try {
			if(this.verifyPassword(password, requestedAccount) == true) {
				System.out.println("Welome, " + requestedAccount.getFirstName() + "! Let's go somewhere.");
				return true;
			}
			else {
				System.out.println("Incorrect password!");
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	/* Locates a user account by username*/
	private UserAccounts locateByUsername(String username) {
		// Loop through accounts, find the one we're looking for
		for(UserAccounts account : this.allAccounts) {
			if(account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}
	
	
	/* Allows a user to view their account information */
	public boolean viewAccountInformation(String username) {
		UserAccounts account = this.locateByUsername(username);
		if(account == null) {
			return false;
		}
		
		// Get account information as variables
		String usr = account.getUsername();
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		int acctID = account.getId();
		AccountStatus memberLevel = account.getMembershipLevel();
		
		// Print it out!
		System.out.println("Username" + usr + "Name: " + firstName + lastName + "AccountID: " + acctID + "Membership Level: " + memberLevel + "Reserved Flights: ");
		return true;
		
	}
	
	/* User can view their flights */
	public boolean listBookedFlights(String username) { 
		
		// Get account
		UserAccounts account = this.locateByUsername(username);
		if(account == null) {
			return false;
		}
		
		// Print the users booked flights
		ArrayList<Flight> flights = (ArrayList<Flight>)account.getBookedFlights();
		for(Flight flight : flights) {
			System.out.println(flight.toString());
		}
		return true;
	}
	
	// User can delete their accouont
	public boolean deleteAccount(String username, String password) {
		// Logout & delete from acct list
		// Probrably delete flight reservations as well
		// Will be done afte flight booking is figured out
	}
	
	/* *************** ACCOUNT MANAGEMENT METHODS END HERE ***************  */
	
	
	
	/* *************** SEAT SELECTION METHOD ***************  */
	
	// Sets seat selection strategy 
	public void setSeatSelectionStrategy(SeatSelectionStrategy strategy) {
		this.strategy = strategy;
	}
	
	/* *************** END OF SEAT SELECTION METHOD ***************  */
	
	
	/* *************** FLIGHT METEHODS HERE ***************  */
	
}
