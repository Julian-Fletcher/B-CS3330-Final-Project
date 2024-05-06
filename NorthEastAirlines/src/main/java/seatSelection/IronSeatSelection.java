package seatSelection;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;

/**
 * Seat selection for Iron members.
 * @see AccountStatus
 */
public class IronSeatSelection implements SeatSelectionStrategy{
	
	/**
	 * Lists all seats available at a certain membership level.
	 * 
	 * @param flight	Flight to be viewed
	 * @return			An ArrayList of available seats
	 */
	@Override
	public ArrayList<Seat> viewAvailableSeats(Flight flight) {
		// Get economy seats
		ArrayList<Seat> availableSeats = flight.getAvailableSeats(SeatType.ECONOMY);

		if(availableSeats.isEmpty()) {
			return availableSeats;
		}
		// Print seat information
		for(Seat seat : availableSeats) {
			System.out.println(seat.getSeatType() + " Seat Number: " + seat.getSeatNumber());
		}
		return availableSeats;
	}

	
	/**
	 * Allows the user to select a seat after being provided a list of seats available to them.
	 * <p>
	 * First calls viewAvailableSeats to display what seats the user can pick from.
	 * Prompts the user to select a seat, sets marks that seat as unavailable, and returns
	 * the seat number to the BookFlight method.
	 * 
	 * @see	viewAvailableSeats
	 * 
	 * 
	 * @param flight		The flight being booked
	 * @param accountLevel	The user account level
	 * @return				The integer of the seat the user has selected
	 */
	@Override
	public int selectSeat(Flight flight, AccountStatus accountLevel) {		
		try {
			int selectedSeat;
			// List seats on flight
			ArrayList<Seat> seatList = viewAvailableSeats(flight);
			
			// Error handling
			if(seatList.isEmpty()) {
				System.out.println("No seat list!!");
				return -1;
			}
			
			// Scanner for seat num input
			Scanner scanner = new Scanner(System.in); 
			// Select seat, set as not available, something else
			
			// Get seat number
			System.out.print("Please select a seat: ");		
			selectedSeat = scanner.nextInt();
			
			// If seat picked isn't available, throw exception, prompt for new input
			for(Seat seat : seatList) {
				if(seat.getSeatNumber() == selectedSeat) {
					if(seat.isAvailable() == false) {
						//scanner.close();
						throw new IllegalArgumentException("Seat not available.");	// Needs handling later
					}
				}
			}
			
			//scanner.close();
			return selectedSeat;
			
			
		} catch (Exception e){	// Exception handling
			System.out.println("Error: " + e.getMessage());
			return -1;
		}
		
		
	}

	
	/**
	 * Sets a seat availability to false.
	 * 
	 * @param flight	The flight object
	 * @param index		The seat number
	 */
	@Override
	public void changeAvailablityToFalse(Flight flight, int index) 
	{
		flight.changeSeatAvailabilityToFalse(viewAvailableSeats(flight).get(index));
	}
}
