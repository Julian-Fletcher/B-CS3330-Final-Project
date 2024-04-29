package seatSelection;

import java.util.ArrayList;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;


public interface SeatSelectionStrategy {
	public ArrayList<Seat> viewAvailableSeats(Flight flight);
	public int selectSeat(Flight flight, AccountStatus accountLevel);
	
}
