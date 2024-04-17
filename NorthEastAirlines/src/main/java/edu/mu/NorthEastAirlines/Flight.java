package edu.mu.NorthEastAirlines;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
	Airport departureLocation;
	Airport arrivalLocation;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	List<Seat> availableSeats;
	int flightNumber;
	PlaneObject planeType;
}
