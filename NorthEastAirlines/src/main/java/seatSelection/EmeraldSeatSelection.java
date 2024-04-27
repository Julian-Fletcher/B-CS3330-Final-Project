package seatSelection;

import java.util.ArrayList;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;

public class EmeraldSeatSelection implements SeatSelectionStrategy{

	@Override
	public boolean viewAvailableSeats(Flight flight) {
		// Create a list of all available seats
		ArrayList<Seat> availableSeats = flight.getAvailableSeats(SeatType.FIRST_CLASS);
		availableSeats.addAll(flight.getAvailableSeats(SeatType.COMFORT));
		availableSeats.addAll(flight.getAvailableSeats(SeatType.ECONOMY));
		
		if(availableSeats.isEmpty()) {
			return false;
		}
		// Print all seat information
		for(Seat seat : availableSeats) {
			System.out.println(seat.getSeatType() + " Seat Number: " + seat.getSeatNumber());
		}
		return true;
	}

	@Override
	public boolean selectSeat(Flight flight, AccountStatus accountLevel, int seatNumber) {
		// TODO Auto-generated method stub
		
		// Select seat, set as not available, something else
		
		return false;
	}

}
