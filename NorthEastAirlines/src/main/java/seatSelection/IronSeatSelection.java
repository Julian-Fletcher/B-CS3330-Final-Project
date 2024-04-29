package seatSelection;

import java.util.ArrayList;
import java.util.Scanner;

import accounts.AccountStatus;
import edu.mu.NorthEastAirlines.Flight;

public class IronSeatSelection implements SeatSelectionStrategy{

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

	@Override
	public int selectSeat(Flight flight, AccountStatus accountLevel) {
		try {
			int selectedSeat;
			// List seats on flight
			ArrayList<Seat> seatList = viewAvailableSeats(flight);
			
			// Error handling
			if(seatList.isEmpty()) {
				return -1;
			}
			
			// Scanner for seat num input
			Scanner scanner = new Scanner(System.in); 
			// Select seat, set as not available, something else
			
			// Get seat number
			System.out.println("Please select a seat: ");		
			selectedSeat = scanner.nextInt();
			
			// If seat picked isn't available, throw exception, prompt for new input
			for(Seat seat : seatList) {
				if(seat.getSeatNumber() == selectedSeat) {
					if(seat.isAvailable() == false) {
						scanner.close();
						throw new IllegalArgumentException("Seat not available.");	// Needs handling later
					}
				}
			}
			
			scanner.close();
			return selectedSeat;
			
			
		} catch (Exception e){	// Exception handling
			System.out.println("Error: " + e.getMessage());
			return -1;
		}
	}

}
