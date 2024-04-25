package edu.mu.NorthEastAirlines;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
		int flightNumber = -1;
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
		flightNumber = randomFlightNumber();
		plane.setModel(PlaneType.gePlaneType(randomPlaneType()));
		
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
	
	public LocalDateTime randomArrivaLocalDateTime()
	{
		 Random random = new Random();
	     int year1 = random.nextInt(2025 - 2000) + 2000;
	     int month1 = random.nextInt(12) + 1;
	     Month monthEnum1 = Month.of(month1);
	     int day1 = random.nextInt(monthEnum1.length(year1 % 4 == 0)) + 1;
	     int hour1 = random.nextInt(24);
	     int minute1 = random.nextInt(60);
	     int second1 = random.nextInt(60);
	     LocalDateTime arrivaLocalDateTime = LocalDateTime.of(year1, month1, day1, hour1, minute1, second1);
	     return arrivaLocalDateTime;
	}
	
	public LocalDateTime randomDeparLocalDateTime()
	{
		Random random = new Random();
	     int year1 = random.nextInt(2025 - 2000) + 2000;
	     int month1 = random.nextInt(12) + 1;
	     Month monthEnum1 = Month.of(month1);
	     int day1 = random.nextInt(monthEnum1.length(year1 % 4 == 0)) + 1;
	     int hour1 = random.nextInt(24);
	     int minute1 = random.nextInt(60);
	     int second1 = random.nextInt(60);
	     LocalDateTime deparLocalDateTime = LocalDateTime.of(year1, month1, day1, hour1, minute1, second1);
	     return deparLocalDateTime;
	}
}