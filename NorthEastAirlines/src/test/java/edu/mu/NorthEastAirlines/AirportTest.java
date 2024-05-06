package edu.mu.NorthEastAirlines;

import org.junit.jupiter.api.Test;

import accounts.AccountStatus;
import accounts.UserAccounts;
import flights.AirportLocations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


class AirportTest {
	
	@Test
	public void testGetLocation() {
		AirportLocations location = AirportLocations.Chicago;
		assertSame(location.getLocationVal(), 2, "Chicago location val should be 2");
		
		
		
	}
	
	@Test
	public void testGetLocationByVal() {
		AirportLocations location = AirportLocations.Chicago;
		assertSame(location.getAirportLocations(2), location, "Chicago location val should be 2");
		
		
		
	}
	
}