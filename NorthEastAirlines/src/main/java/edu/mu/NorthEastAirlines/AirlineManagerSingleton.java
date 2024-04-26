package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Random;
import flights.Airport;
import flights.AirportLocations;
import flights.PlaneFactory;
import flights.PlaneObject;
import accounts.UserAccounts;
import flights.PlaneType;
<<<<<<< Updated upstream
import seatSelection.Seat;
import seatSelection.SeatType;

=======
>>>>>>> Stashed changes

public class AirlineManagerSingleton {
	private String adminPassword;
	private List<UserAccounts> allAccounts;
	public List<Flight> allFlights;
	public boolean isAdminLoggedIn;	// Check if admin account logged in
	public AirlineManagerSingleton() {
	
	}
	
	/* JULIAN - GET INSTANCE METHOD GOES HERE!!! */
	// Needs to instanticate an admin account
	

	
<<<<<<< Updated upstream
	/* *** User Account Management Begins Here *** */
=======
	// Test
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
>>>>>>> Stashed changes
	
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
