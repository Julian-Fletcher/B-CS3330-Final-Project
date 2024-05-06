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


/**
 * Primary class that contains all core project functionality. 
 * <p>
 * Class uses singleton strategy design to ensure no duplicates possible, as 
 * only one manager should exist at a time. It contains account and fight management methods.
 */
public class AirlineManagerSingleton {
	
	private String adminPassword;
	private ArrayList<UserAccounts> allAccounts = new ArrayList<>();
	private ArrayList<Flight> allFlights = new ArrayList<>();
	private boolean isAdminLoggedIn;	// Check if admin account logged in

	private int accountIndex;
	
	private static AirlineManagerSingleton instance = null;		// Only one!
	private SeatSelectionStrategy strategy;	// SeatSelectionStrategy
	
	/**
	 * Default constructor.
	 */
	public AirlineManagerSingleton() {
	
	}
	

	/**
	 * Initializes one instance of the AirlineManagerSingleton.
	 * <p>
	 * The singleton is returned to the user to access other core functionality. 
	 * Prevents multiple objects from being instantiated, if the user
	 * attempts to create multiple, the same object will be returned each time. 
	 * 
	 * @return An AirlineManagerSingleton object
	 */
	public static AirlineManagerSingleton getInstance() {
		if(instance == null) {
			instance = new AirlineManagerSingleton();
			
		}
		return instance;
	}
	
	//*********************** GENERATE RANDOM FLIGHT STARTS HERE *************************************
	/**
	 * Creates a random flight object and adds it to the master flight ArrayList.
	 * 
	 * @return	Boolean value of whether the flight was successfully added or not
	 */
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

	/**
	 * Generates a random string to be used as an airport code.
	 * @return	The generated string
	 */
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
	
	/**
	 * Generates a random integer to select an airport location.
	 * @return	The random integer
	 */
	private int randomAirportLocation()
	{
		int maxLocations = 10;
		Random random = new Random();
		return random.nextInt(maxLocations);
	}
	
	/**
	 * Generates a random LocalDateTime object to be used for flight departure and arrival time.
	 * @return	LocalDateTime object
	 */
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
	
	/**
	 * Increments or decrements the account index variable.
	 *  
	 * @param update	1 to increment. 2 to decrement
	 * @return			Returns true if inc or dec. Returns false if an invalid parameter passed
	 */
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
	
	/**
	 * Creates a user account object with the provided information and returns that object to them.
	 * <p>
	 * Account usernames must be unique, if a duplicate username is provided the account will not be created. 
	 * 
	 * @param username 	The username the user would like to use
	 * @param password 	The password for the account
	 * @param firstName The user's first name
	 * @param lastName 	The user's last name
	 * @return			A UserAccounts object
	 */
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
	

	/**
	 * Hashes the user password using the SHA-256 algorithm. 
	 * 
	 * 
	 * @param rawPassword	Plain text user password
	 * @return				Returns the hashed user password
	 * @throws NoSuchAlgorithmException	
	 */
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
	
	
	/**
	 * Verifies whether the provided password matches the one in the user account.
	 * <p>
	 * Hashes the provided plain text password and compares it to the hashed password in the user account. 
	 * Returns the result of the comparison -- true or false.
	 *  
	 * @param givenPassword	Plain text password provided by the user
	 * @param requestedAccount	The account whose password needs to be compared to
	 * @return					The result of the password comparison -- true or false
	 * @throws NoSuchAlgorithmException
	 */
	private boolean verifyPassword(String givenPassword, UserAccounts requestedAccount) throws NoSuchAlgorithmException {
		String hashedInput = hashPassword(givenPassword);
		return hashedInput.equals(requestedAccount.getPassword());	// This is not secure, password should PROBRABLY be private
	}
	
	
	/**
	 * Logs a user into their account after authenticating a provided username and password.
	 * <p>
	 * If an account does not exist or the user provides an incorrect password they will not
	 * be logged into the account. 
	 * 
	 * @param username	The account username
	 * @param password	The password to the account
	 * @return			Returns whether the account was logged into or not
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
			
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	/**
	 * Logs a user out of their account if provided credentials are correct.
	 * 
	 * @param username	The account username seeking to be logged out
	 * @param password	The password of the account
	 * @return			Returns whether the account has been logged out or not
	 */
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
	/**
	 * Locates a user account by their username.
	 * <p> Searches the master user account ArrayList for a username. Returns the UserAccounts 
	 * object with a matching username, or null if no such account is found.
	 * 
	 * @param username	The username being searched for
	 * @return			A UserAccounts object, or null
	 */
	private UserAccounts locateByUsername(String username) {
		// Loop through accounts, find the one we're looking for
		for(UserAccounts account : this.allAccounts) {
			if(account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}
	
	
	/**
	 * Prints relevant user account information.
	 * 
	 * @param username	The username of the requested account
	 * @return			Returns true if the account was found and information printed
	 */
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
	
	
	/**
	 * Lists the booked flights of a user. 
	 * 
	 * @param username	Requested account username
	 * @return			Returns true if the account was found and information was printed
	 */
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
	

	/**
	 * Deletes a user account by removing it from the account list.
	 * 
	 * 
	 * @param username	Username of account wanting to be deleted
	 * @param password	Password of account 
	 * @return			Returns true if the account has been deleted
	 */
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
	
	/**
	 * Prints all user accounts in the allAccounts ArrayList.
	 * <p> 
	 * Requires the admin password to be used. 
	 * @param password	The administrative password
	 */
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
	
	/**
	 * Updates a user password to a new password.
	 * 
	 * @param username		Username of account to update password for
	 * @param password		Existing password for authentication of account
	 * @param newPassword	New password for account
	 * @return				Result of update -- success (true) or fail (false)
	 */
	public boolean changePassword(String username, String password, String newPassword)
	{
		UserAccounts account = this.locateByUsername(username);//search for the account with the given username
		if(account.getLoginStatus() == true) //check if they are logged in
		{
			try 
			{
				account.setPassword(hashPassword(newPassword)); //set new password if already logged in
			} 
			catch (NoSuchAlgorithmException e) 
			{
				e.printStackTrace();
			}	
		}
		else 
		{
			login(username, newPassword); //try to log in if previously hadn't
			return false;
		}
		return true;
	}
	
	/**
	 * Updates an account membership level.
	 * 	
	 * @param username	Username of account to update
	 * @param password	Password of account
	 * @return			Result of update - true or false
	 */

	/* *************** ACCOUNT MANAGEMENT METHODS END HERE ***************  */
	
	
	
	/* *************** SEAT SELECTION METHOD ***************  */
	
	/**
	 * Changes the current seat selection strategy
	 * 
	 * @param strategy	The strategy to change to
	 */
	public void setSeatSelectionStrategy(SeatSelectionStrategy strategy) {
		this.strategy = strategy;
	}
	
	/* *************** END OF SEAT SELECTION METHOD ***************  */
	
	
	/* *************** FLIGHT METEHODS HERE ***************  */
	/**
	 * Cancels flight reservation.
	 * <p>
	 * Provided username and flight number will be used to cancel a reservation on a flight.
	 * The account-selected seat will be set to available and the flight information removed 
	 * from the user account.
	 * 
	 * @param flightNumber	Flight to cancel reservation on
	 * @param username		Username of account canceling flight
	 * @return				Success or failure of cancellation
	 */
	public boolean cancelFlightReservation(int flightNumber, String username) 
	{
		UserAccounts account = this.locateByUsername(username); //locate account based off username
		if(account == null || account.getBookedFlights().isEmpty())
		{
			return false;
		}
		
		for(Iterator<UserFlightData> iterator = account.getBookedFlights().iterator(); iterator.hasNext();) //create an iterator to search through the getBookedFlight list
		{
			UserFlightData data = iterator.next();
			if(data.getFlight() == flightNumber) //find the correct flight based off the given flightNumber
			{
				Flight flight = findFlightByNumber(flightNumber);
				if(flight != null)
				{
					Seat cancelSeat = flight.getSeatByNumber(data.getSeat(), data.getSeatType()); 
					if(cancelSeat != null)
					{
						flight.changeSeatAvailabilityToTrue(cancelSeat);//if the flight was found change seat availability to available (true)
					}
					System.out.println(data.getSeatType().name() + " seat: " + data.getSeat() + " has been canceled!!!"); //display message that a seat has been canceled
					int deduction = determinePointsToDeduct(account.getMembershipLevel()); //calculate point deduction
					account.setUserPoints(account.getUserPoints() - deduction); //deduct points for cancelling flight
				}
			}
			iterator.remove();
			return true;
		}
		return false;
	}
	
	/**
	 * Searches for a specific flight from the allFlights master ArrayList 
	 * and returns the flight with the matching flight number on success or null on failure
	 * 
	 * @param flightNumber Flight identification number
	 * @return Flight object that matches the given flight identification number
	 */
	public Flight findFlightByNumber(int flightNumber) 
	{
	    for (Flight flight : this.allFlights) //search the master flight list called allFlights
	    {
	        if (flight.getFlightNumber() == flightNumber) 
	        {
	            return flight; //on success return the flight with the matching flight id number
	        }
	    }
	    return null; // on failure return null
	}
	
	/**
	 * Calculates the number of points that will be lost after canceling a flight at specific membership levels
	 * 
	 * @param membershipLevel Takes an instance of the AccountStatus enum attached to a given UserAccount
	 * @return An integer to determine point deduction
	 */
	public int determinePointsToDeduct(AccountStatus membershipLevel)
	{
	    switch (membershipLevel) //based on the membership level of the UserAccount deduct points for canceling a flight
	    {
	        case EMERALD:
	        {
	            return 100;
	        }
	        case GOLD:
	        {
	            return 50;
	        }
	        default:
	        {
	            return 25;
	        }
	    }
	}
	
	/* *************** BOOK FLIGHT METEHOD STARTS HERE ***************  */
	/**
	 * Reserves a seat on a specified flight.
	 * <p>
	 * Reserves a seat on a flight for a specified user. Utilizes
	 * SeatSelectionStrategy to handle seat booking based on user
	 * account level. 
	 * 
	 * @see SeatSelectionStrategy
	 * @param account	Account to reserve a seat under
	 * @return			Result of booking (true or false)
	 */
	public boolean bookFlight(UserAccounts account) 
	{
		if(account == null)
		{
			return false;
		}
		Flight flight = viewPotentialFlights(); //choose a desired flight
		if(account.getMembershipLevel() == AccountStatus.EMERALD) //Based on membershiplevel invoke different seat selection strategies
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
		if(account.getMembershipLevel() == AccountStatus.GOLD) //Based on membershiplevel invoke different seat selection strategies
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
		if(account.getMembershipLevel() == AccountStatus.IRON) //Based on membershiplevel invoke different seat selection strategies
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
	/**
	 * Lists available flights for user to select from.
	 * 
	 * @return	The user's selected flight object.
	 */
	protected Flight viewPotentialFlights()
	{	
		Scanner scan = new Scanner(System.in); 
		int selectedFlight;
		
		for(Flight flight : allFlights)
		{
			System.out.println("Flight Num: " + flight.flightNumber + ".\nTo: " + flight.arrivalLocation.city + " From: " + flight.departureLocation.city); //display critical information about the flights available
		}
		
		System.out.print("Select a Flight Number: "); //ask for user input on what flight they want
		selectedFlight = scan.nextInt();
		return allFlights.get(selectedFlight);
	}
	
	/**
	 * Lists all flights.
	 */
	public boolean viewAvailableFlights()
	{
		if(allFlights != null)
		{
			for(Flight flight : allFlights)
			{
				System.out.println(flight.toString());
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Populates the master flight list iteratively.
	 * 
	 * @param count	The number of flights to add to the master flight list
	 */
	public void addFlightsToMasterList(int count)
	{
		for(int i = 0; i < count; i++)
		{
			generateRandomFlights();
		}
	}
	
	/**
	 * Lists seat information for a given flight
	 * @param searchFlightNumber	The flight to list seats for
	 */
	public boolean viewPlaneSeats(int searchFlightNumber)
	{
		if(allFlights != null)
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
			return true;
		}
		return false;
	}
	
	
	/**
	 * Updates user membership level 
	 * 
	 * @param user	User account to update membership level
	 * @param membership	New membership level for account	
	 * @return				Whether account membership level was updated successfully or not
	 */
	public boolean changeMembershipLevel(UserAccounts user, AccountStatus membership) {
		int points = user.getUserPoints();
		if(points >= 1000 && membership == AccountStatus.EMERALD) {
			user.setMembershipLevel(AccountStatus.EMERALD);
			user.setUserPoints(points-1000);
			return true;
		}else if(points >= 500 && membership == AccountStatus.GOLD) {
			user.setMembershipLevel(AccountStatus.GOLD);
			user.setUserPoints(points-500);
			return true;
		} else if (points <= 500 && membership == AccountStatus.IRON){
			user.setMembershipLevel(AccountStatus.IRON);
			user.setUserPoints(points);
			return true;
		}
		return false;
	}
}
