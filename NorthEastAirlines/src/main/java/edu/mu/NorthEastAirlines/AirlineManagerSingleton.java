package edu.mu.NorthEastAirlines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.View;

import flights.Airport;
import flights.AirportLocations;
import flights.PlanePsuedoFactory;
import flights.PlaneObject;
import accounts.AccountStatus;
import accounts.UserAccounts;
import flights.PlaneType;
import flights.UserFlightData;
import seatSelection.EmeraldSeatSelection;
import seatSelection.GoldSeatSelection;
import seatSelection.IronSeatSelection;
import seatSelection.Seat;
import seatSelection.SeatSelectionStrategy;
import seatSelection.SeatType;



public class AirlineManagerSingleton {

	private String adminPassword;
	private ArrayList<UserAccounts> allAccounts = new ArrayList<>();
	private ArrayList<Flight> allFlights = new ArrayList<>();
	private boolean isAdminLoggedIn;	// Check if admin account logged in

	private int accountIndex;
	
	private static AirlineManagerSingleton instance = null;		// Only one!
	private SeatSelectionStrategy strategy;	// SeatSelectionStrategy
	
	
	public AirlineManagerSingleton() {
	
	}
	
	// Singleton initalizer. Only allows one!!
	// Does not yet make admin account!
	public static AirlineManagerSingleton getInstance() {
		if(instance == null) {
			instance = new AirlineManagerSingleton();
			
		}
		return instance;
	}
	
	//*********************** GENERATE RANDOM FLIGHT STARTS HERE *************************************
	public boolean generateRandomFlights()
	{
		Airport arriveAirport = new Airport();
		Airport departAirport = new Airport();
		LocalDateTime departTime;
		LocalDateTime arriveTime;
		int flightNumber = -999;
		
		//Creates Plane Factory
		PlanePsuedoFactory planeFactory = new PlanePsuedoFactory();
		
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
		flightNumber = allFlights.size();
		
		Flight newFlight = new Flight(departAirport, arriveAirport, departTime, arriveTime, flightNumber, plane);
		if(newFlight != null)
		{
			allFlights.add(newFlight);
		}
		return true;
	}

	private String randomAirportCode()
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
	
	private int randomAirportLocation()
	{
		int maxLocations = 10;
		Random random = new Random();
		return random.nextInt(maxLocations);
	}
	
	private LocalDateTime randomLocalDateTime()
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
			System.out.println("Account with username '" + username + "' does not exist!");
			return false;
		}
		
		// Check if already logged in
		if(requestedAccount.getLoginStatus() == true) {
			System.out.println("Already logged in! Did you mean to logout?");
			return false;	// True for logged in always
		}
		
		// If not already logged in
		try {
			if(this.verifyPassword(password, requestedAccount) == true) {
				System.out.println("Welcome, " + requestedAccount.getFirstName() + "! Let's go somewhere.");
				requestedAccount.setLoginStatus(true);
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
	
	
	public boolean logout(String username, String password) {
		UserAccounts requestedAccount = this.locateByUsername(username);
		if(requestedAccount == null) {
			System.out.println("Account with username '" + username + "' does not exist!");
			return false;
		}
		
		// Check if not logged in
		if(requestedAccount.getLoginStatus() == false) {
			System.out.println("User not logged in! Did you mean to login?");
			return false;	// True for logged out always
		}
		
		// If currently logged in
		try {
			if(this.verifyPassword(password, requestedAccount) == true) {
				System.out.println("Goodbye, " + requestedAccount.getFirstName() + "! See you soon.");
				requestedAccount.setLoginStatus(false);
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
		System.out.println("Username " + usr + " Name: " + firstName + lastName + " AccountID: " + acctID + " Membership Level: " + memberLevel + " Reserved Flights: ");
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
		ArrayList<UserFlightData> flights = (ArrayList<UserFlightData>)account.getBookedFlights();
		for(UserFlightData flight : flights) {
			System.out.println("Flight: " + flight.getFlight() + " Seat: " +  flight.getSeat());
		}
		return true;
	}
	
	// User can delete their accouont
	public boolean deleteAccount(String username, String password) {
		// Find the account
		UserAccounts requestedAccount = this.locateByUsername(username);
		if(requestedAccount == null) {
			System.out.println("Account with username '" + username + "' does not exist!");
			return false;
		}
		
		// Log into account
		boolean logIn = this.login(username, password);
		
		if(logIn == false) {
			return false;
		}
		
		// Remove the account from the account list
		this.allAccounts.remove(requestedAccount);
		this.updateAccountIndex(2); // decrement account index
		return true;
	}
	
	public void printAccounts(String password) {
		System.out.println(password.equals(adminPassword));
		
		if(password.equals(adminPassword)) {
			
			System.out.println("ID, USERNAME, FIRST, LAST, MEMBERSHIP, # OF BOOKED FLIGHTS");
			if(allAccounts != null && allAccounts.size() > 0) {
				for(UserAccounts user : allAccounts) {
					System.out.println(user.getId() + ", " + user.getUsername() + ", " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getMembershipLevel() + ", " + user.getBookedFlights().size());
				}
			}else {
				System.out.println("No Accounts Currently Available");
			}
		}
	}
	
	public boolean changePassword(String username, String password)
	{
		boolean login = this.login(username, password);
		if(login == true)
		{
			for(UserAccounts acct : allAccounts)
			{
				if(acct.getUsername() == username)
				{
					acct.setUserPoints(acct.getUserPoints() - 10);
					try 
					{
						acct.setPassword(hashPassword(password));
					} catch (NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean changeMembershipLevel(String username, String password)
	{
		boolean login = this.login(username, password);
		if(login == true)
		{
			for(UserAccounts acct : allAccounts)
			{
				if(acct.getUsername() == username)
				{
					if(acct.getUserPoints() >= 500)
					{
						if(acct.getMembershipLevel() == AccountStatus.IRON)
						{
							System.out.println("You're already IRON status yah dummy.");
						}
						else 
						{
							acct.setMembershipLevel(AccountStatus.IRON);
						}
					}
					else if(acct.getUserPoints() >= 1000)
					{
						if(acct.getMembershipLevel() == AccountStatus.GOLD)
						{
							System.out.println("You're already GOLD status. You'll be fine.");
						}
						else 
						{
							acct.setMembershipLevel(AccountStatus.GOLD);
						}
					}
					else if(acct.getUserPoints() >= 2000)
					{
						if(acct.getMembershipLevel() == AccountStatus.EMERALD)
						{
							System.out.println("You're already EMERALD status. You're such a greedy person");
						}
						else 
						{
							acct.setMembershipLevel(AccountStatus.EMERALD);
						}
					}
				}
			}
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/* *************** ACCOUNT MANAGEMENT METHODS END HERE ***************  */
	
	
	
	/* *************** SEAT SELECTION METHOD ***************  */
	
	// Sets seat selection strategy 
	public void setSeatSelectionStrategy(SeatSelectionStrategy strategy) {
		this.strategy = strategy;
	}
	
	/* *************** END OF SEAT SELECTION METHOD ***************  */
	
	
	/* *************** FLIGHT METEHODS HERE ***************  */
	boolean cancelFlightReservation(int flightNumber, String username) {
		// Get account
		UserAccounts account = this.locateByUsername(username);
		if(account == null) {
			return false;
		}
		
//		for(Flight flight : this.allFlights) {
//			if(flight.getFlightNumber() == flightNumber) {
//				
//			}
//		}
		return true;
	}
	
	/* *************** BOOK FLIGHT METEHOD STARTS HERE ***************  */
	public boolean bookFlight(UserAccounts account) 
	{
		Flight flight = viewPotentialFlights();
		if(account.getMembershipLevel() == AccountStatus.EMERALD) 
		{
			EmeraldSeatSelection select = new EmeraldSeatSelection();
			int newSeat = select.selectSeat(flight, account.getMembershipLevel());
			for(Seat seat : flight.getAllSeats(SeatType.FIRST_CLASS))
			{
				if(seat.getSeatNumber() == newSeat)
				{
					flight.changeSeatAvailabilityToFalse(seat);
				}
			}
			System.out.println("First Class: " + newSeat + " is now reserved!!!");
			account.setUserPoints(account.getUserPoints() + 200);
			UserFlightData flightData = new UserFlightData(flight.flightNumber, newSeat, SeatType.FIRST_CLASS);
			ArrayList<UserFlightData> userDataList = new ArrayList<UserFlightData>();
			userDataList.add(flightData);
			account.setBookedFlights(userDataList);
			System.out.println("Updated points: " + account.getUserPoints() + " points");
			return true;
		}
		if(account.getMembershipLevel() == AccountStatus.GOLD) 
		{
			GoldSeatSelection select = new GoldSeatSelection();
			int newSeat = select.selectSeat(flight, account.getMembershipLevel());
			for(Seat seat : flight.getAllSeats(SeatType.COMFORT))
			{
				if(seat.getSeatNumber() == newSeat)
				{
					flight.changeSeatAvailabilityToFalse(seat);
				}
			}
			System.out.println("Comfort Class: " + newSeat + " is now reserved!!!");
			account.setUserPoints(account.getUserPoints() + 100);
			UserFlightData flightData = new UserFlightData(flight.flightNumber, newSeat, SeatType.COMFORT);
			ArrayList<UserFlightData> userDataList = new ArrayList<UserFlightData>();
			userDataList.add(flightData);
			account.setBookedFlights(userDataList);
			System.out.println("Updated points: " + account.getUserPoints() + " points");
			return true;
		}
		if(account.getMembershipLevel() == AccountStatus.IRON) 
		{
			IronSeatSelection select = new IronSeatSelection();
			int newSeat = select.selectSeat(flight, account.getMembershipLevel());
			for(Seat seat : flight.getAllSeats(SeatType.ECONOMY))
			{
				if(seat.getSeatNumber() == newSeat)
				{
					flight.changeSeatAvailabilityToFalse(seat);
				}
			}
			System.out.println("Economy Class: " + newSeat + " is now reserved!!!");
			account.setUserPoints(account.getUserPoints() + 50);
			UserFlightData flightData = new UserFlightData(flight.flightNumber, newSeat, SeatType.ECONOMY);
			ArrayList<UserFlightData> userDataList = new ArrayList<UserFlightData>();
			userDataList.add(flightData);
			account.setBookedFlights(userDataList);
			System.out.println("Updated points: " + account.getUserPoints() + " points");
			return true;
		}
		return false;
	}
	/* *************** BOOK FLIGHT METEHOD ENDS HERE ***************  */
	protected Flight viewPotentialFlights()
	{	
		Scanner scan = new Scanner(System.in);
		int selectedFlight;
		
		for(Flight flight : allFlights)
		{
			System.out.println("Flight Num: " + flight.flightNumber + ". To: " + flight.departureLocation.city);
		}
		
		System.out.print("Select a Flight Number: ");
		selectedFlight = scan.nextInt();
		//scan.close();
		return allFlights.get(selectedFlight);
	}
	
	public void viewAvailableFlights()
	{
		for(Flight flight : allFlights)
		{
			System.out.println(flight.toString());
		}
	}
	
	public void addFlightsToMasterList(int count)
	{
		for(int i = 0; i < count; i++)
		{
			generateRandomFlights();
		}
	}
	
	public void viewPlaneSeats(int searchFlightNumber)
	{
		for(Flight flight : allFlights)
		{
			if(flight.flightNumber  == searchFlightNumber)
			{
				for(Seat seat : flight.getAllSeats(SeatType.FIRST_CLASS))
				{
					System.out.println(seat.toString());
				}
				for(Seat seat : flight.getAllSeats(SeatType.COMFORT))
				{
					System.out.println(seat.toString());
				}
				for(Seat seat : flight.getAllSeats(SeatType.ECONOMY))
				{
					System.out.println(seat.toString());
				}
			}
		}
	}
}
