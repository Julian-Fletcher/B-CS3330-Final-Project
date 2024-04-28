package seatSelection;

import java.util.ArrayList;
import java.util.Scanner;

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
	public int selectSeat(Flight flight, AccountStatus accountLevel, int seatNumber) {
		try {
			int selectedSeat;
			// List seats on flight
			boolean seatList = viewAvailableSeats(flight);
			
			// Error handling
			if(seatList != true) {
				return -1;
			}
			
			// Scanner for seat num input
			Scanner scanner = new Scanner(System.in); 
			// Select seat, set as not available, something else
			
			// Get seat number
			System.out.println("Please select a seat: ");		
			selectedSeat = scanner.nextInt();
			
			// If seat picked isn't available, throw exception, prompt for new input
			if(flight.getSeatAvailability(seatNumber) == false) {
				scanner.close();
				throw new IllegalArgumentException("Seat not available.");	// Needs handling later
			}
			
			scanner.close();
			return selectedSeat;
			
			
		} catch (Exception e){	// Exception handling
			System.out.println("Error: " + e.getMessage());
			return -1;
		}
				
	}

}
