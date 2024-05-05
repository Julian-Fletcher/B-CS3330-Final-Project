package seatSelection;

import java.util.ArrayList;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;

/**
 * Interface for SeatSelectionStrategy. 
 * <p>
 * Contains the methods used within the different selection levels.
 */
public interface SeatSelectionStrategy {
	/**
	 * Lists all seats available at a certain membership level.
	 * 
	 * @param flight	Flight to be viewed
	 * @return			An ArrayList of available seats
	 */
	
	
	public ArrayList<Seat> viewAvailableSeats(Flight flight);
	/**
	 * Allows the user to select a seat after being provided a list of seats available to them.
	 * <p>
	 * First calls viewAvailableSeats to display what seats the user can pick from.
	 * Prompts the user to select a seat, sets marks that seat as unavailable, and returns
	 * the seat number to the BookFlight method.
	 * 
	 * @see	viewAvailableSeats
	 * @see BookFlight
	 * 
	 * @param flight		The flight being booked
	 * @param accountLevel	The user account level
	 * @return				The integer of the seat the user has selected
	 */
	public int selectSeat(Flight flight, AccountStatus accountLevel);
	
	/**
	 * Sets a seat availability to false.
	 * 
	 * @param flight	The flight object
	 * @param index		The seat number
	 */
	public void changeAvailablityToFalse(Flight flight, int index);
}
