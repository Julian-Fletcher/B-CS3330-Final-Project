package edu.mu.NorthEastAirlines;

import accounts.AccountStatus;
import accounts.UserAccounts;
import junit.framework.TestCase;
import seatSelection.Seat;
import seatSelection.SeatType;

public class SeatTest extends TestCase {

	public void testSeatCreation() {
		// Testing here
		
		Seat seat = new Seat(24, SeatType.COMFORT);
		
		assertNotNull("Should be a seat", seat);
		
	}
	
	public void testSeatAvailability() {
		Seat seat = new Seat(24, SeatType.COMFORT);
		
		assertTrue("Seat should start available",seat.isAvailable());
		seat.setAvailable(false);
		assertFalse("Seat should not be available",seat.isAvailable());
		seat.setAvailable(true);
		assertTrue("Seat should be available",seat.isAvailable());
	}

	
	
	

}
