package seatSelection;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;


public interface SeatSelectionStrategy {
	public boolean viewAvailableSeats(Flight flight);
	public boolean selectSeat(Flight flight, AccountStatus accountLevel, int seatNumber );
	
}
