package edu.mu.NorthEastAirlines;

import accounts.AccountStatus;
import accounts.UserAccounts;
import seatSelection.Seat;
import seatSelection.SeatType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class SeatTest {

	public void testSeatCreation() {
		// Testing here
		
		Seat seat = new Seat(24, SeatType.COMFORT);
		
		assertNotNull(seat, "Should be a seat");
		
	}
	
	public void testSeatAvailability() {
		Seat seat = new Seat(24, SeatType.COMFORT);
		
		assertTrue(seat.isAvailable(),"Seat should start available");
		seat.setAvailable(false);
		assertFalse(seat.isAvailable(),"Seat should not be available");
		seat.setAvailable(true);
		assertTrue(seat.isAvailable(),"Seat should be available");
	}

	
	
	

}
