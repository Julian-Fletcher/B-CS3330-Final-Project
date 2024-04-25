package edu.mu.NorthEastAirlines;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Random;

public class FlightManagement 
{
	public void generateRandomFlights()
	{
		Flight flight = new Flight();
		Airport arriveAirport = new Airport();
		Airport departAirport = new Airport();
		LocalDateTime departTime;
		LocalDateTime arriveTime;
		int flightNumber;
		PlaneObject plane = new PlaneObject();
		AirportLocations arriveLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		AirportLocations departLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		
		while(arriveLocal == departLocal)
		{
			departLocal = AirportLocations.getAirportLocations(randomAirportLocation());
		}
		arriveAirport.airportCode = randomAirportCode();
		arriveAirport.city = arriveLocal.toString();
		departAirport.airportCode = randomAirportCode();
		departAirport.city = departLocal.toString();
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
}
