package seatSelection;

import java.util.ArrayList;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;

public class GoldSeatSelection implements SeatSelectionStrategy{

	@Override
	public boolean viewAvailableSeats(Flight flight) {
		// TODO Auto-generated method stub
		// Create a list of all available seats
		ArrayList<Seat> availableSeats = flight.getAvailableSeats(SeatType.COMFORT);
		availableSeats.addAll(flight.getAvailableSeats(SeatType.ECONOMY));
		
		if(availableSeats.isEmpty()) {
			return false;
		}
		for(Seat seat : availableSeats) {
			System.out.println(seat.getSeatType() + " Seat Number: " + seat.getSeatNumber());
		}
		return true;
	}

	@Override
	public boolean selectSeat(Flight flight, AccountStatus accountLevel, int seatNumber) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
